/**
 * @author Aboubacar OUEDRAOGO
 *
 */

package un.asyeco.eco.server.printing; 

import static un.asyeco.eco.C_eCO.*;
import so.i18n.IntlObj;
import so.kernel.core.DataSet;
import so.kernel.core.KernelEvent;
import so.kernel.core.events.ServerEvent;
import so.kernel.core.interfaces.KDocumentInterface;
import so.kernel.server.GCFServerEvent;
import so.kernel.server.ServerBinder;
import so.kernel.server.rules.KSR_DocumentRead;
import un.kernel.util.AWServerTranslator;
import un.kernel.util.JasperPrintingTool;
import un.kernel.util.ServerUtils;

import com.sun.jmx.snmp.ThreadContext;

//GVA <patch ID="#530 Release Order improvement" version="4.2.2" type="IMPROVEMENT" date="Feb 15, 2014" author="Leonardo Flores">
public class SR_Print extends KSR_DocumentRead {

    private static String SAVED_EVENT = "SavedEventInformationPage";
    
    public SR_Print(ServerBinder binder) {
        super(binder, KSR_DocumentRead.ERROR | KSR_DocumentRead.DOCUMENT_FOUND, "Certificate not found", new String[]{ST_CREATED, ST_SUBMITTED, ST_ACCEPTED, ST_CO_SENT, ST_VALIDATED_CUSTOMS});
    }
    protected void prepareBeforeRead(DataSet source, DataSet key) {
        key.add(INSTANCE_ID).tryToSetContent(source.de(INSTANCE_ID).getContent());
    }
    protected DataSet prepareAfterRead(DataSet source) {
        ServerEvent event = (ServerEvent) ThreadContext.get(SAVED_EVENT);
        byte[] report = null;
        if (event != null) report = getReport(source.ds(KDocumentInterface.NORMAL_ID), event);
        if (report != null) event.getDestination().add(PDF).tryToSetContent(report);
        return null;
    }
    @Override
    public void apply(KernelEvent kernelevent) {
        // 1. Event is through direct client connection (not active document). KSR_Rule will handle it
        if (!GCFServerEvent.class.isInstance(kernelevent)){
            ThreadContext.push(SAVED_EVENT, kernelevent);
            super.apply(kernelevent);
        }
        // 2. Event is through document middle event
        else{
            GCFServerEvent event = (GCFServerEvent) kernelevent;
            DataSet doc = event.getUserTransactionEnvironment().getFinalDocument();
            byte[] report = getReport(doc.ds(KDocumentInterface.NORMAL_ID), event);
            if (report != null) event.getDestination().add(PDF).tryToSetContent(report);
        }
    }

    private byte[] getReport(DataSet datas, KernelEvent event){
        AWServerTranslator.setLocale(event);
        byte[] zippedPDF = null;
        
		String reportName = ServerUtils.getIntlFileName("eCO", "jasper", this);
		//String styleTemplate = ServerUtils.getIntlFileName("PrintOutStyles", "jrtx", new PrintingLocator());
		try {
			JasperPrintingTool print = new JasperPrintingTool(this.getClass(), reportName);
			fillParam(print, datas);
			print.addDictionaryToParameters();
			//print.addObjectToParameters("SR_TEMPLATE", PrintingLocator.class.getResource(styleTemplate).openStream());
			zippedPDF = JasperPrintingTool.getPDFasZIP(print.fillReport(new EcoDataSource(datas)));
		}catch (Exception e) {
			e.printStackTrace();
		}finally{
            AWServerTranslator.leaveLocale();   
        }
		return zippedPDF;
	}

	private void fillParam(JasperPrintingTool print, DataSet data) {
		
		String crit_gds_w = "";
		String crit_gds_s = "";
		String crit_tar = "";
		String crit_va = "";
		String crit_ecowas = "";
		String crit_uemoa = "";
		String crit_ctlOK = "";
		String crit_ctlNOK = "";
		
		print.addStringToParameters("P_ECOWAS_LABEL", lng("ECOWAS/UEMOA"));
		print.addStringToParameters("P_REPUBLIC_LABEL", lng("REPUBLIC OF"));
		print.addStringToParameters("P_TITLE", lng("CERTIFICATE OF ORIGINE N°"));
		print.addStringToParameters("P_PROD_LABEL", lng("1. Producer (name or trade name and address) ") + "  ");
		print.addStringToParameters("P_PROD_COD_LABEL", lng("Registration N°"));
		print.addStringToParameters("P_CRN_LABEL", lng("CRN"));
		
		print.addStringToParameters("P_NAM_LABEL", lng("Name"));
		print.addStringToParameters("P_ADR_LABEL", lng("Address"));
		print.addStringToParameters("P_ZIP_COD_LABEL", lng("Postal code"));
		print.addStringToParameters("P_TEL_LABEL", lng("Telephone"));
		print.addStringToParameters("P_EMAIL_LABEL", lng("Email"));
		print.addStringToParameters("P_WSITE_LABEL", lng("Web site"));
		
		
		print.addStringToParameters("P_CONS_LABEL", lng("2. Consignee (Name or trade name and address"));
		print.addStringToParameters("P_CRITERIA_LABEL", lng("3. Criteria determining origin (1)"));
		print.addStringToParameters("P_CRIT_GDS_W_LABEL", lng("Goods wholly produced in the Community"));
		print.addStringToParameters("P_CRIT_GDS_S_LABEL", lng("Goods sufficiently processed or worked"));
		print.addStringToParameters("P_CRIT_TAR_LABEL", lng("According to the criterion of changing of tariff position"));		
		print.addStringToParameters("P_CRIT_VA_LABEL", lng("According to the criterion of value added"));
		print.addStringToParameters("P_RATE_LABEL", lng("Rate"));
		
        print.addStringToParameters("P_CRIT_ECOWAS_LABEL", lng("ECOWAS scheme (in compliance with the provision of Protocole A/P1/1/03, articles 2,3 & 4)"));
        print.addStringToParameters("P_CRIT_UEMOA_LABEL", lng("UEMOA scheme (in compliance with the provisions of Protocols N°  CEG/UEMOA/01)"));
		
        print.addStringToParameters("P_QTY_LABEL", lng("4. Quantity, nature of"
        		+ "\n"	+ "goods and number of packages"));
        print.addStringToParameters("P_NBR_QTY_LABEL", lng("Quantity"));
        print.addStringToParameters("P_KIND_LABEL", lng("Kind"));
        print.addStringToParameters("P_MRK_LABEL", lng("Mark"));
        
        print.addStringToParameters("P_TSN_LABEL", lng("5. Tariff & Statistical "
        		+ "\n"	+ "Nomenclature"));
        print.addStringToParameters("P_TAR_COD_LABEL", lng("Nomenclature(10)"));
        print.addStringToParameters("P_TAR_DSC_LABEL", lng("Description of goods"));
        
        print.addStringToParameters("P_GDS_NBR_LABEL", lng("6. Approval no of goods"));
        print.addStringToParameters("P_GRS_MASS_LABEL", lng("7. Gross weight or other measure"));
        print.addStringToParameters("P_GRS_W_LABEL", lng("Gross weight (Kgs)"));
        print.addStringToParameters("P_UOM_COD_LABEL", lng("U.O.M Code"));
        
        print.addStringToParameters("P_INV_VAL_LABEL", lng("8. Invoice value"));
        print.addStringToParameters("P_AMT_LABEL", lng("Amount"));
        print.addStringToParameters("P_CUR_COD_LABEL", lng("Currency"));
        
        print.addStringToParameters("P_EXP_LABEL", lng("9. Declaration by exporter"));
        print.addStringToParameters("P_EXP_UNDSIGN_LABEL", lng("I, the undersigned,"));
        print.addStringToParameters("P_EXP_OTH_LABEL", lng("declare that the above mentioned particulars are correct, and that"
        		+ "\n"	+ "the goods satisfy the requirements for the issue of this certificate"));
        print.addStringToParameters("P_AUTH_LABEL", lng("10. Certification by the appropriate Authority \n"
        		+ "Declared criterion of origin certified correct."));
        print.addStringToParameters("P_CUST_LABEL", lng("11. Certification by Customs \n"
        		+ "I, the undersigned Customs officer, declare that this certificate is authentic and accurate."));
        print.addStringToParameters("P_EXP_DOC_LABEL", lng("Export document"));
        print.addStringToParameters("P_SAD_MODEL_LABEL", lng("Model N°"));
        print.addStringToParameters("P_SAD_DAT_LABEL", lng("Dated"));
        print.addStringToParameters("P_EXAMINER_LABEL", lng("Examiner"));
        print.addStringToParameters("P_OFFICE_LABEL", lng("Custom Office"));
        
        print.addStringToParameters("P_REQ_VERIF_LABEL", lng("12. Request for Verification"));
        print.addStringToParameters("P_REQ_TO_LABEL", lng("To"));
        print.addStringToParameters("P_REQ_ADR_LABEL", lng("address of issuing Customs office"));
        print.addStringToParameters("P_REQ_OTH_LABEL", lng("Verification of authenticity and accuracy of this certificaye is required."));
        print.addStringToParameters("P_RESP_VERIF_LABEL", lng("13. Verification"));
        print.addStringToParameters("P_RESP_VERIF1_LABEL", lng("Verification establishes that this certificate (1)"));
        print.addStringToParameters("P_RESP_CRIT1_LABEL", lng("Has been issued by this office and that the particulars are correct"));
        print.addStringToParameters("P_RESP_CRIT2_LABEL", lng("Does not satisfy the conditions of authenticity and accuracy."));
        
        print.addStringToParameters("P_SIGN_STAMP_LABEL", lng("Signature & stamp"));
		print.addStringToParameters("P_PLACE_DATE_LABEL", lng("Place & date"));
		
		crit_gds_w = (data.ds(CO).ds(CRITERIA).de(OP).getBoolean(false) ) ? "X" : "";
		crit_gds_s = (data.ds(CO).ds(CRITERIA).de(SW).getBoolean(false) ) ? "X" : "";
		crit_tar = (data.ds(CO).ds(CRITERIA).ds(TAR).de(CHG).getBoolean(false) ) ? "X" : "";
		crit_va = (data.ds(CO).ds(CRITERIA).ds(PVA).de(FLG).getBoolean(false) ) ? "X" : "";
		crit_ecowas = (data.ds(CO).ds(CRITERIA).ds(CEDEAO).de(FLG).getBoolean(false) ) ? "X" : "";
		crit_uemoa = (data.ds(CO).ds(CRITERIA).ds(UEMOA).de(FLG).getBoolean(false) ) ? "X" : "";
		crit_ctlOK = (data.ds(DPA).ds(CTL).ds(RSLT).de(CORR).getBoolean(false) ) ? "X" : "";
		crit_ctlNOK = (data.ds(DPA).ds(CTL).ds(RSLT).de(NOTCORRECT).getBoolean(false) ) ? "X" : "";
		
		print.addStringToParameters("P_CRIT_GDS_W", lng(crit_gds_w));
		print.addStringToParameters("P_CRIT_GDS_S", lng(crit_gds_s));
		print.addStringToParameters("P_CRIT_TAR", lng(crit_tar));		
		print.addStringToParameters("P_CRIT_VA", lng(crit_va));
        print.addStringToParameters("P_CRIT_ECOWAS", lng(crit_ecowas));
        print.addStringToParameters("P_CRIT_UEMOA", lng(crit_uemoa));
        print.addStringToParameters("P_CRIT_CTL_OK", lng(crit_ctlOK));
        print.addStringToParameters("P_CRIT_CTL_NOK", lng(crit_ctlNOK));

		
		//print.addStringToParameters("P_PRINTED_ON", lng("Printed on:"));
		//print.addStringToParameters("P_PAGE", lng("Page"));
		//print.addStringToParameters("P_IDE_BAR", data.ds(IDE).de(BAR));
	}

	private static String lng(String property) {
		return IntlObj.createMessage("un.asyeco", property);
	}
}