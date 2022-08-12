// $Header: /home/asycuda/home/cvsroot/asybrk/Server/src/un/broker/asysad/server/SR_ChkTar.java,v 1.16 2013-01-30 16:15:11 jedidi Exp $

package un.asyeco.eco.server; 



import java.util.Date;
import java.util.Iterator;

import so.kernel.core.DataField;
import so.kernel.core.DataSet;
import so.kernel.core.HTVerifier.Row;
import so.kernel.core.KernelEvent;
import so.kernel.server.GCFServerEvent;
import so.kernel.server.ServerRule;
import so.kernel.server.UserTransactionEnvironment;
import so.util.DebugOutput;
import un.asyeco.eco.C_eCO;
import un.asyref.UNTARTABView.C_UNTARTABView;
import un.globalConfig.util.GlobalServerConfigUtilities;
import un.kernel.core.ArefHTCompatible;
import un.kernel.server.ServerVerifierDelegator;

public class SR_ChkTar extends ServerRule implements C_eCO {

	// GVA <patch ID="TUNING" version="4.2.1" type="modification" date="DEC 12, 2012" author="ahmed">
	private static String np = GlobalServerConfigUtilities.getProperty("Client.tar.config.NP");
	// GVA <patch ID="TUNING" version="4.2.1" type="modification" date="DEC 12, 2012" author="ahmed"/>

	public SR_ChkTar(S_eCO binder) {
		super(binder);
	}

	protected void apply(KernelEvent e) {

		/*
		 // GVA <patch ID="TUNING" version="4.2.1" type="modification" date="DEC 12, 2012" author="ahmed">
		Data[] docs;
		String bName = "TarTab";
		GCFServerEvent event = (GCFServerEvent) e;
		DataSet result = event.getDestination();
		UserTransactionEnvironment environment = event.getUserTransactionEnvironment();

		DataSet source = (DataSet) event.getSource();
		Date workingDate = (Date) source.de(DAT).getContent();

		DataSet criteria = new DataSet("normal");

		criteria.add(HS6_COD).tryToSetContent(source.de(HS6_COD).getContentString());
		criteria.add(TAR_PR1).tryToSetContent(source.de(TAR_PR1).getContentString());

		// If source.de(PRE).getContent() is null it means that ITM.TAR.HSC.NB2
		// is not needed
		if (source.de(TAR_PR2).getContent() != null) {
			criteria.add(TAR_PR2);
			criteria.de(TAR_PR2).copyFrom(source.de(TAR_PR2));
		}
		String np = GlobalServerConfigUtilities.getProperty("Client.tar.config.NP");
		ArefHTCompatible verifier = ServerVerifierDelegator.getVerifier(getServerBinder());

		boolean exist = false;
//		 Commodity codes on 8 digits
		if ("0".equals(np)) {
			exist = verifier.contains(TAR_TAB, (Date) source.de(DAT).getContent(), new String[] { "HS6_COD", "TAR_PR1" },
					new Object[] { source.de(HS6_COD).getContentString(), source.de(TAR_PR1).getContentString() });

			// Commodity codes on 10 digits
		} else if ("2".equals(np)) {
			if (source.de(TAR_PR2).getContentString().length() < 2) {
				return;
			}
			exist = verifier.contains(TAR_TAB, (Date) source.de(DAT).getContent(),
					new String[] { "HS6_COD", "TAR_PR1", "TAR_PR2" }, new Object[] { source.de(HS6_COD).getContentString(), source.de(TAR_PR1).getContentString(),
				source.de(TAR_PR2).getContentString() });

			// Commodity codes on 11 digits
		} else if ("3".equals(np)) {
			if (source.de(TAR_PR2).getContentString().length() < 3) {
				return;
			}
			exist = verifier.contains(TAR_TAB, (Date) source.de(DAT).getContent(),
					new String[] { "HS6_COD", "TAR_PR1", "TAR_PR2" }, new Object[] { source.de(HS6_COD).getContentString(), source.de(TAR_PR1).getContentString(),
				source.de(TAR_PR2).getContentString() });
		}

		if (!exist) {
			return;
		}

		try {
			docs = environment.loadDocumentsWithNonKeysData(getServerBinder().getProperty(bName), criteria);
		} catch (Exception _) {
			DebugOutput.print(_);
			return;
		}

		if (docs == null || docs.length == 0) {
			return;
		} else {
			// DebugOutput.print("Number of documents found = " + docs.length);
			for (int i = 0; i < docs.length; i++) {
				DataSet doc = (DataSet) docs[i];
				DataSet doc_normal = doc.ds("normal");
				Date validFromDate = (Date) doc_normal.ds(DAT).de(BEG).getContent();
				Date validToDate = (Date) doc_normal.ds(DAT).de(END).getContent();

				if ((!validFromDate.after(workingDate) && validToDate == null)
						|| (!validFromDate.after(workingDate) && validToDate.after(workingDate))) {
					// DebugOutput.print("Record is valid");
					String s = doc_normal.de(C_UNTARTABView.TAR_DSC).getString("").trim();
					DataField d = result.add(DSC);
					d.tryToSetContent(s);

					s = doc_normal.de(TAR_PR2).getString("").trim();
					DataField p = result.add(PRE);
					p.tryToSetContent(s);

					s = doc_normal.de(C_UNTARTABView.UOM_COD1).getString("").trim();
					p = result.add(UM1);
					p.tryToSetContent(s);

					s = doc_normal.de(C_UNTARTABView.UOM_COD2).getString("").trim();
					p = result.add(UM2);
					p.tryToSetContent(s);

					s = doc_normal.de(C_UNTARTABView.UOM_COD3).getString("").trim();
					p = result.add(UM3);
					p.tryToSetContent(s);
					return;
				} else {
					// DebugOutput.print("Record not valid");
				}
			}
		}

		 */
		// GVA <patch ID="TUNING" version="4.2.1" type="modification" date="DEC 12, 2012" author="ahmed"/>

		// GVA <patch ID="TUNING" version="4.2.1" type="modification" date="DEC 12, 2012" author="ahmed">
		DebugOutput.print("Remote tartab");
		GCFServerEvent event = (GCFServerEvent) e;
		DataSet result = event.getDestination();
		UserTransactionEnvironment environment = event.getUserTransactionEnvironment();
		DataSet source = (DataSet) event.getSource();
		ArefHTCompatible verifier = ServerVerifierDelegator.getVerifier(getServerBinder());
		//Commodity codes on 8 digits
		if (     ("2".equals(np) && source.de(TAR_PR2).getString("").length() < 2)   ||
				("3".equals(np) && source.de(TAR_PR2).getString("").length() < 3)   ) {
			return;
		}
		String[] colName= null;
		Object[] colValue= null;


		String hs6 = source.de(HS6_COD).getString("");
		String pr1 = source.de(TAR_PR1).getString("");
		String pr2 = source.de(TAR_PR2).getString("");
		Date wde = (Date) source.de(DAT).getContent();

		if (!"0".equals(np)){
			colName = new String[]{HS6_COD, TAR_PR1, TAR_PR2};
			colValue= new Object[]{hs6, pr1,pr2};
		}else{
			colName = new String[]{HS6_COD, TAR_PR1};
			colValue= new Object[]{hs6, pr1};

		}

		Iterator it = verifier.find(TAR_TAB, wde, colName, colValue);
		String dsc   = null ;
		String uom1  = null;
		String uom2  = null;
		String uom3  = null;

		if (it!= null && it.hasNext())
		{
			Row rw = (Row)it.next();
			dsc   = (String)rw.get(C_UNTARTABView.TAR_DSC);
			uom1  = (String)rw.get(C_UNTARTABView.UOM_COD1);
			uom2  = (String)rw.get(C_UNTARTABView.UOM_COD2);
			uom3  = (String)rw.get(C_UNTARTABView.UOM_COD3);
			// DebugOutput.print("Record is valid");

			DataField d = result.add(DSC);
			d.tryToSetContent(dsc);


			DataField p = result.add(PRE);
			p.tryToSetContent(pr2);


			p = result.add(UM1);
			p.tryToSetContent(uom1);


			p = result.add(UM2);
			p.tryToSetContent(uom2);


			p = result.add(UM3);
			p.tryToSetContent(uom3);
			return;



		}//else{
		// DebugOutput.print("Record not valid");
		//}
	}
}
