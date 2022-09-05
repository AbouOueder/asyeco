/**
 *  ECOWAS VERSION="1.0.0" TYPE="MODULE eCO": NEW COMMENT (HM: 28-Jun-2022)
 *  <p>
 *  eCO - Visual Page
 *  <p>
 *  @author Aboubacar OUEDRAOGO <a href="mailto:ouederaboubakr@gmail.com">ouederaboubakr@gmail.com</a>
 */
package un.asyeco.eco.client;

import javax.swing.ButtonGroup;
import javax.swing.JCheckBox;

import so.kernel.client.elf.ElfField;
import so.kernel.core.DataSet;
import so.kernel.core.KernelEventConstants;
import so.kernel.core.rules.KR_DataMandatory;
import so.swing.KPanel;
import un.adtcommons.client.visual.AWVisualTemplate;
import un.asyeco.eco.C_eCO;
import un.asyeco.eco.D_eCO;
import un.asyeco.eco.client.rules.R_OpenTar;
import un.kernel.core.KDocument;

/**
 * Visual Page for null of null <!-- begin-user-doc --> <!-- end-user-doc -->
 * 
 * @generated NON
 */
public class VP_eCO extends AWVisualTemplate implements C_eCO {
    // ELF Declarative: Visual controls

	//CO country code
	transient private ElfField Co_Cty_Cod = elfFieldPool.getElfField();
	
	// CO country name
	transient private ElfField Co_Cty_Dsc = elfFieldPool.getElfField();
	
	//CO unique number
	transient private ElfField Co_Nbr = elfFieldPool.getElfField();
	
	//CO unique reference number CRN
	transient private ElfField crn = elfFieldPool.getElfField();
	
	//CO producer company code
	transient private ElfField Co_Cmp_Prod_Cod = elfFieldPool.getElfField();
	
	//CO producer company name
	transient private ElfField Co_Cmp_Prod_Nam = elfFieldPool.getElfField();
	
	//CO producer company cty
	transient private ElfField Co_Cmp_Prod_Cty = elfFieldPool.getElfField();
	
	//CO producer company adress
	transient private ElfField Co_Cmp_Prod_Adr = elfFieldPool.getElfField();
	
	//CO producer company email
		transient private ElfField Co_Cmp_Prod_Email = elfFieldPool.getElfField(); //New
	
	//CO producer company telephone number
	transient private ElfField Co_Cmp_Prod_Tel = elfFieldPool.getElfField();
	
	//CO consignee company name
	transient private ElfField Co_Cmp_Cons_Nam = elfFieldPool.getElfField();
	
	//CO consignee company country
	transient private ElfField Co_Cmp_Cons_Cty = elfFieldPool.getElfField();
	
	//CO consignee company adress
	transient private ElfField Co_Cmp_Cons_Adr = elfFieldPool.getElfField();
	
	//CO consignee company telephone number
	transient private ElfField Co_Cmp_Cons_Tel = elfFieldPool.getElfField();
	
	//CO consignee company email address
	transient private ElfField Co_Cmp_Cons_Email = elfFieldPool.getElfField(); //New
	
	//CO criteria goods wholly produced in the Community - Flag
	transient private JCheckBox Co_Criteria_Op_Flg = new JCheckBox();
	
	//CO criteria goods sufficiently processed or worked - Flag
	transient private JCheckBox Co_Criteria_Sw_Flg = new JCheckBox();
	
	//CO criteria goods sufficiently processed or worked with changing of tariff position - Flag
	transient private JCheckBox Co_Criteria_Tar_Chg_Flg = new JCheckBox();
	
	//CO criteria goods sufficiently processed or worked with value added - Flag
	transient private JCheckBox Co_Criteria_Pva_Flg = new JCheckBox();
	
	//CO Schemas - Flag
	transient private JCheckBox Co_Cedeao_Flg = new JCheckBox();
	
	transient private JCheckBox Co_Uemoa_Flg = new JCheckBox();
	
	//CO criteria goods sufficiently processed or worked with value added rate
	transient private ElfField Co_Criteria_Pva_Rat = elfFieldPool.getElfField();

	//CO package number
	transient private ElfField Co_Pck_Nbr = elfFieldPool.getElfField();
	
	//CO package code
	transient private ElfField Co_Pck_Cod = elfFieldPool.getElfField();

	//CO package description
	transient private ElfField Co_Pck_Dsc = elfFieldPool.getElfField();
	
	//CO package mark
	transient private ElfField Co_Pck_Mrk = elfFieldPool.getElfField();
	
	//CO tariff position (10)
	transient private ElfField Co_Tar_Cod = elfFieldPool.getElfField();

	//CO tariff description (10)
	transient private ElfField Co_Tar_Dsc = elfFieldPool.getElfField();

	//CO goods commercial description 
	transient private ElfField Co_Gds_Dsc = elfFieldPool.getElfField();
	
	//CO product number
	transient private ElfField Co_Product_Nbr = elfFieldPool.getElfField();
	
	//CO weight gross mass
	transient private ElfField Co_Wgt_Grs = elfFieldPool.getElfField();
	
	//CO supplementary unit code
	transient private ElfField Co_Sup_Cod = elfFieldPool.getElfField();

	//CO supplementary unit description
	transient private ElfField Co_Sup_Dsc = elfFieldPool.getElfField();
	
	//CO invoice amount
	transient private ElfField Co_Inv_amt = elfFieldPool.getElfField();
	
	//CO currency code
	transient private ElfField Co_Cur_Cod = elfFieldPool.getElfField();
	
	//CO exporter name
	transient private ElfField Co_Cmp_Exp_Nam = elfFieldPool.getElfField();

	//CO exporter place
	transient private ElfField Co_Cmp_Exp_Cty = elfFieldPool.getElfField();

	//CO exporter date of signature 
	transient private ElfField Co_Cmp_Exp_Sign_Dat = elfFieldPool.getElfField();

	//CO authority name
	transient private ElfField Co_Auth_Nam = elfFieldPool.getElfField();

	//CO authority place
	transient private ElfField Co_Auth_Cty = elfFieldPool.getElfField();

	//CO authority date of signature 
	transient private ElfField Co_Auth_Sign_Dat = elfFieldPool.getElfField();
	
	//CO model of DAU
	transient private ElfField Co_Dau_Mod = elfFieldPool.getElfField();
	
	//CO register number of DAU
	transient private ElfField Co_Dau_Reg_Nbr = elfFieldPool.getElfField();

	//CO register date of DAU
	transient private ElfField Co_Dau_Reg_Dat = elfFieldPool.getElfField();

	//Departure office code
	transient private ElfField Co_Cuo_Cod = elfFieldPool.getElfField();
	
	//Departure office name
	transient private ElfField Co_Cuo_Nam = elfFieldPool.getElfField();
	
	//Name of examiner of departure office
	transient private ElfField Dpa_Exa_Nam = elfFieldPool.getElfField();

	//Reference of examiner of departure office
	transient private ElfField Dpa_Exa_Cod = elfFieldPool.getElfField();
	
	//Place of examiner of departure office
	transient private ElfField Dpa_Exa_Cty = elfFieldPool.getElfField();
	
	//Date of signature of examiner of departure office
	transient private ElfField Dpa_Exa_Sign_Dat = elfFieldPool.getElfField();

	//Departure office code to request by destination office
	transient private ElfField Dpa_Cuo_Cod = elfFieldPool.getElfField();
	
	//Departure office name to request by destination office
	transient private ElfField Dpa_Cuo_Nam = elfFieldPool.getElfField();
	
	//Departure office adress to request by destination office
	transient private ElfField Dpa_Cuo_Adr = elfFieldPool.getElfField();
	
	//Name of examiner of destination office
	transient private ElfField Dest_Exa_Nam = elfFieldPool.getElfField();

	//Reference of examiner of departure office
	transient private ElfField Dest_Exa_Cod = elfFieldPool.getElfField();
	
	//Place of examiner of destination office
	transient private ElfField Dest_Exa_Cty = elfFieldPool.getElfField();
	
	//Date of signature of examiner of destination office
	transient private ElfField Dest_Exa_Sign_Dat = elfFieldPool.getElfField();
	
	
	//Name of examiner of control office
	transient private ElfField Dpa_Ctl_Exa_Nam = elfFieldPool.getElfField();

	//Reference of examiner of control office
	transient private ElfField Dpa_Ctl_Exa_Cod = elfFieldPool.getElfField();
	
	//Place of examiner of control office
	transient private ElfField Dpa_Ctl_Exa_Cty = elfFieldPool.getElfField();
	
	//Date of signature of examiner of control office
	transient private ElfField Dpa_Ctl_Exa_Sign_Dat = elfFieldPool.getElfField();
	
	//CO verification of authenticity correct - Flag
	transient private JCheckBox Dpa_Ctl_Correct_Flg = new JCheckBox();
	
	//CO verification of authenticity not correct - Flag
	transient private JCheckBox Dpa_Ctl_Not_Correct_Flg = new JCheckBox();

	//Co expired date
	transient private ElfField Co_expired_Dat = elfFieldPool.getElfField();
	
	transient private ButtonGroup  grp_Goods   =  new ButtonGroup();
	transient private ButtonGroup  grp_TarPva   =  new ButtonGroup();
	transient private ButtonGroup  grp_ctl   =  new ButtonGroup();
	

	

    /**
     * Parameterless constructor required by desktop persistence <!-- begin-user-doc --> <!-- end-user-doc -->
     * 
     * @generated NON
     */
    public VP_eCO() {
        super("asyeco.GSG", 937, 1348);
        //initVisualPage();
        initVisualControlsI18n();
    }

   

	@Override
	protected Object clone() throws CloneNotSupportedException {
		// TODO Auto-generated method stub
		return super.clone();
	}



	/**
     * Method for initialising VP_eCO visual controls <!-- begin-user-doc --> <!-- end-user-doc -->
     * 
     * @generated NON
     */
    // Initialization: Visual controls

    public void initVisualControls() {

        // Labels and title stripes

        // Label
    	
    	
        // Header of certifacte
    	add(5, 69, 160, 20, lng("ECOWAS/UEMOA"));        
        add(470, 69, 90, 20, lng("REPUBLIC OF"));        
        add(300, 101, 300, 20, lng("CERTFICATE OF ORIGIN N°"));  
        
        // 1. Producer (registration number, name, address, ...)
        add(5, 132, 160, 20, lng("1. Producer"));        
        add(185, 132, 160, 20, lng("Registration N°"));        
        add(30, 162, 70, 20, lng("Name"));        
        add(30, 192, 70, 20, lng("Address"));        
        add(30, 222, 70, 20, lng("Telephone"));        
        add(30, 252, 70, 20, lng("Email"));
        

    	
        // 2. Consignee (name, address, ...)
        add(5, 300, 160, 20, lng("2. Consignee"));
        add(30, 330, 70, 20, lng("Name"));        
        add(30, 360, 70, 20, lng("Address"));        
        add(30, 390, 70, 20, lng("Telephone"));        
        add(30, 420, 70, 20, lng("Email"));
        
       	// 3. Criteria determining origin (1)       
        add(470, 132, 300, 20, lng("3. Criteria determining origin"));        
        add(490, 162, 300, 20, lng("a. Goods wholly produced in the Community"));        
        add(490, 192, 300, 20, lng("b. Goods sufficiently processed or worked"));        
        add(510, 222, 400, 20, lng("b.1 According to the criterion of changing of tariff position"));            
        add(510, 252, 300, 20, lng("b.2 According to the criterion of value added"));     
        add(510, 282, 100, 20, lng("Rate"));  
        add(720, 282, 40, 20, lng("%"));
        add(490, 332, 300, 20, lng("ECOWAS scheme (in compliance with the"));
        add(490, 347, 400, 20, lng("provision of Protocol A/.P1/1/03, articles 2,3 & 4)"));
        add(490, 377, 350, 20, lng("UEMOA scheme (in compliance with the provision of "));
        add(490, 392, 350, 20, lng("Protocols N°...CEG/UEMOA/01)"));

        
    	// 4. Quantity, nature, of goods and number of packages
        add(5, 467, 200, 20, lng("4. Quantity, nature of goods"));  
        add(5, 487, 200, 20, lng("    and mark of packages")); 
        add(5, 527, 100, 20, lng("Quantity")); 
        add(5, 557, 60, 20, lng("Kind"));
        add(5, 587, 100, 20, lng("Mark"));
        
    	// 5. Tariff and statistical nomenclature
        add(193, 467, 300, 20, lng("5. Tariff & Statistical Nomenclature"));          
        add(193, 527, 100, 20, lng("T.S.N")); 
        add(193, 587, 300, 20, lng("Commercial description of goods"));        
        
        
    	// 6. Approval no of goods
        add(470, 467, 200, 20, lng("6. Approval no of"));  
        add(470, 487, 200, 20, lng("    goods"));         
        
        
    	// 7. Gross weight or other measure
        add(603, 467, 200, 20, lng("7. Gross weight or"));  
        add(603, 487, 200, 20, lng("   other measure")); 
        add(603, 527, 150, 20, lng("Gross weight (Kgs)")); 
        add(603, 577, 70, 20, lng("U.O.M"));
        
        
    	// 8. Invoice value
        add(770, 467, 200, 20, lng("8. Invoice value"));          
        add(770, 527, 150, 20, lng("Amount")); 
        add(770, 567, 150, 20, lng("Currency"));
        
    	// 9. Declaration by exporter
        add(5, 654, 300, 20, lng("9. Declaration by exporter"));          
        add(5, 685, 150, 20, lng("Name")); 
        add(5, 715, 150, 20, lng("Place and date"));
        add(5, 745, 100, 20, lng("Signature"));
        //add(670, 715, 80, 20, lng("Email"));
        
        
    	// 10. Certification by the appropriate authority
        add(5, 784, 500, 20, lng("10. Certification by the appropriate Authority declared criterion"));  
        add(5, 804, 200, 20, lng("     of origin certified correct.")); 
        add(5, 834, 150, 20, lng("Name")); 
        add(5, 864, 150, 20, lng("Place"));
        add(5, 894, 80, 20, lng("Date"));
        add(5, 924, 100, 20, lng("Signature"));
        
    	// 11. Certification by the appropriate authority
        add(470, 784, 300, 20, lng("11. Certification by Customs"));       
        add(470, 804, 150, 20, lng("Customs office")); 
        add(470, 834, 150, 20, lng("Reference of DAU"));
        add(470, 864, 100, 20, lng("Examiner"));
        add(470, 894, 100, 20, lng("Place"));
        add(470, 924, 100, 20, lng("Date"));
        add(470, 954, 100, 20, lng("Signature"));
        
        
    	// 12. Request for verification
        add(5, 981, 300, 20, lng("12. Request for verification"));  
        add(5, 1014, 150, 20, lng("Customs office")); 
        add(5, 1044, 100, 20, lng("Examiner"));
        add(5, 1074, 100, 20, lng("Place"));
        add(5, 1104, 100, 20, lng("Date"));
        add(5, 1134, 100, 20, lng("Signature"));
        
    	// 13. Request for verification
        add(470, 981, 300, 20, lng("13. Result of verification"));  
        add(470, 1014, 500, 20, lng("Has been issued by this office and that the particulars are correct")); 
        add(470, 1044, 500, 20, lng("Does not satisfy the conditions of authenticity and accuracy.")); 
        add(470, 1074, 100, 20, lng("Examiner"));
        add(470, 1104, 100, 20, lng("Place and date"));
        add(470, 1134, 100, 20, lng("Signature"));
               

        // Editable Visual controls
        
        // Header
        add(560, 69, 40, 20, Co_Cty_Cod,  lng("Country code of Certificate of origin"));
        add(605, 69, 300, 20, Co_Cty_Dsc,  lng("Country name of Certificate of origin"));
        
        add(510, 101, 150, 20, Co_Nbr, lng("Number of Certificate of origin")); 
        add(665, 101, 150, 20, crn, lng("Certificate Reference Number")); 
        
        // 1. Producer
        add(305, 132, 100, 20, Co_Cmp_Prod_Cod, lng("Registration N° of producer"));      
        add(105, 162, 300, 20,Co_Cmp_Prod_Nam, lng("Name of producer"));   
        add(105, 192, 300, 20, Co_Cmp_Prod_Adr, lng("Address of producer"));        
        add(105, 222, 300, 20, Co_Cmp_Prod_Tel, lng("Telephone of producer"));        
        add(105, 252, 300, 20, Co_Cmp_Prod_Email, lng("Email"));
        
        // 2. Consignee (name, ...)
        add(105, 330, 300, 20, Co_Cmp_Cons_Nam, lng("Name"));        
        add(105, 360, 300, 20, Co_Cmp_Cons_Adr, lng("Address"));        
        add(105, 390, 300, 20, Co_Cmp_Cons_Tel,lng("Telephone"));        
        add(105, 420, 300, 20, Co_Cmp_Cons_Email,lng("Email"));
        
        // 3. Criteria determining origin (1)   
        add(890, 153, 30, 40, Co_Criteria_Op_Flg, lng("")); 
        //grp_Goods.add(Co_Criteria_Op_Flg);
        add(890, 183, 30, 40, Co_Criteria_Sw_Flg , lng(""));
        //grp_Goods.add(Co_Criteria_Sw_Flg);
        add(890, 213, 30, 40, Co_Criteria_Tar_Chg_Flg, lng(""));
        //grp_TarPva.add(Co_Criteria_Tar_Chg_Flg);
        add(890, 243, 30, 40, Co_Criteria_Pva_Flg , lng(""));
        //grp_TarPva.add(Co_Criteria_Pva_Flg);
        add(615, 282, 100, 20, Co_Criteria_Pva_Rat, lng(""));  
        
        add(890, 340, 30, 20, Co_Cedeao_Flg, lng(""));
        add(890, 380, 30, 20, Co_Uemoa_Flg,  lng(""));

        
    	// 4. Quantity, nature, of goods and number of packages
        add(65, 527, 123, 20, Co_Pck_Nbr, lng("Quantity of packages")); 
        add(65, 557, 30, 20, Co_Pck_Cod, lng("Kind of packages code"));
        add(105, 557, 83, 20, Co_Pck_Dsc, lng("Kind of packages name"));
        add(65, 587, 123, 60, Co_Pck_Mrk, lng("Mark of goods"));
        
    	// 5. Tariff and statistical nomenclature       
        add(293, 527, 150, 20, Co_Tar_Cod, lng("Tariff code on 10 digits")); 
        add(193, 607, 250, 40, Co_Tar_Dsc, lng("Commercial description of goods"));        
        
        
    	// 6. Approval no of goods
        add(470, 527, 115, 20, Co_Product_Nbr, lng("Number of approval goods"));         
        
        
    	// 7. Gross weight or other measure

        add(603, 547, 150, 20, Co_Wgt_Grs, lng("Gross mass (Kgs)")); 
        add(673, 577, 60, 20, Co_Sup_Cod, lng("Unit of measure code"));
        add(603, 607, 150, 20, Co_Sup_Dsc, lng("Unit of measure description"));
        
        
    	// 8. Invoice value      
        add(770, 547, 155, 20, Co_Inv_amt, lng("Invoice Amount")); 
        add(770, 587, 155, 20, Co_Cur_Cod, lng("Currency code"));        
        
    	// 9. Declaration by exporter      
        add(105, 685, 505, 20, Co_Cmp_Exp_Nam, lng("Name of exporter")); 
        add(105, 715, 400, 20, Co_Cmp_Exp_Cty, lng("Place of signature of exporter"));
        add(510, 715, 100, 20, Co_Cmp_Exp_Sign_Dat, lng("Date of signature of exporter"));
        //add(200, 745, 100, 20, lng("Sigature"));
        
        
    	// 10. Certification by the appropriate authority
        add(50, 834, 400, 20, Co_Auth_Nam, lng("Name of Appropriate Authority")); 
        add(50, 864, 400, 20, Co_Auth_Cty, lng("Place of Appropriate Authority"));
        add(50, 894, 100, 20, Co_Auth_Sign_Dat, lng("Date of signature"));
        //add(5, 924, 100, 20, lng("Signature"));
        
    	// 11. Certification by customs 
        add(600, 804, 100, 20, Co_Cuo_Cod, lng("Customs office code"));
        add(705, 804, 200, 20, Co_Cuo_Nam, lng("Customs office name"));
        add(600, 834, 70, 20,  Co_Dau_Mod, lng("Model of DAU"));
        add(675, 834, 148, 20,  Co_Dau_Reg_Nbr, lng("Register number of DAU"));
        add(827, 834, 98, 20, Co_Dau_Reg_Dat, lng("Register date of DAU"));
        add(575, 864, 100, 20, Dpa_Exa_Cod, lng("Examiner Code"));
        add(680, 864, 245, 20, Dpa_Exa_Nam, lng("Examiner Name"));
        add(575, 894, 350, 20, Dpa_Exa_Cty, lng("Place of Examiner"));
        add(575, 924, 100, 20, Dpa_Exa_Sign_Dat, lng("Date of signature"));
       
        
        
    	// 12. Request for verification
        add(105, 1014, 70, 20, Dpa_Cuo_Cod, lng("Code of departure office")); 
        add(177, 1014, 142, 20, Dpa_Cuo_Nam, lng("Name of departure office")); 
        add(321, 1014, 142, 20, Dpa_Cuo_Adr, lng("Address of departure office"));
        add(105, 1044, 100, 20, Dest_Exa_Cod, lng("Examiner code"));
        add(210, 1044, 253, 20, Dest_Exa_Nam, lng("Examiner name"));
        add(105, 1074, 358, 20, Dest_Exa_Cty, lng("Place of signature"));
        add(105, 1104, 100, 20, Dest_Exa_Sign_Dat, lng("Date of signature"));        
        
    	// 13. Verification
        add(575, 1074, 100, 20, Dpa_Ctl_Exa_Cod, lng("Examiner code"));
        add(680, 1074, 240, 20, Dpa_Ctl_Exa_Nam, lng("Examiner name"));
        add(575, 1104, 240, 20, Dpa_Ctl_Exa_Cty, lng("Place of signature"));
        add(820, 1104, 100, 20, Dpa_Ctl_Exa_Sign_Dat, lng("Date of signature"));
        
        add(890, 1005, 20, 40, Dpa_Ctl_Correct_Flg, lng("")); 
        //grp_ctl.add(Dpa_Ctl_Correct_Flg);
        add(890, 1035, 20, 40, Dpa_Ctl_Not_Correct_Flg, lng("")); 
        //grp_ctl.add(Dpa_Ctl_Not_Correct_Flg);
 
        
        


    }

    // ELF Page properties

    /**
     * Method for initialising VP_Certificate visual facets <!-- begin-user-doc --> <!-- end-user-doc -->
     * 
     * @see so.kernel.client.KVisualPage#initFacets()
     * @generated NO
     */

    @Override
    public void initFacets() {
       // DataSet doc = getDocument().getNormal();
        D_eCO doc = (D_eCO) getDocument();
        
        //Header
        addFacetHistorize(Co_Cty_Cod, doc.ds(CO).ds(CTY).de(COD), un.adtcommons.client.visual.DHMMapManager.getModel(doc, CTY_TAB, 0), "CTY_CODNAM");        
        addFacetHistorize(Co_Cty_Dsc, doc.ds(CO).ds(CTY).de(DSC), un.adtcommons.client.visual.DHMMapManager.getModel(doc, CTY_TAB, 3), "CTY_NAM" );      
        
        addFacetText(Co_Nbr, doc.ds(CO).de(NBR), "N11"); 
        addFacetText(crn, doc.ds(CO).de(CRN), "AN12"); 
        
        // 1. Producer informations
        addFacetHistorize(Co_Cmp_Prod_Cod, doc.ds(CO).ds(CMP).ds(PROD).de(COD), un.adtcommons.client.visual.DHMMapManager.getModel(doc, PROD_TAB, 0), "PROD_CODNAM");     
        //addFacetText(Co_Cmp_Prod_Cod, doc.ds(CO).ds(CMP).ds(PROD).de(COD),"N7");      
        addFacetText(Co_Cmp_Prod_Nam, doc.ds(CO).ds(CMP).ds(PROD).de(NAM), "UX135");   
        addFacetText(Co_Cmp_Prod_Adr, doc.ds(CO).ds(CMP).ds(PROD).de(ADR), "UX135");        
        addFacetText(Co_Cmp_Prod_Tel, doc.ds(CO).ds(CMP).ds(PROD).de(TEL), "N17");        
        addFacetEmail(Co_Cmp_Prod_Email, doc.ds(CO).ds(CMP).ds(PROD).de(EMAIL), "X135");
        
        // 2. Consignee
        addFacetText(Co_Cmp_Cons_Nam, doc.ds(CO).ds(CMP).ds(CONS).de(NAM), "UX135");        
        addFacetText(Co_Cmp_Cons_Adr, doc.ds(CO).ds(CMP).ds(CONS).de(ADR), "UX135");        
        addFacetText(Co_Cmp_Cons_Tel, doc.ds(CO).ds(CMP).ds(CONS).de(TEL), "N17");        
        addFacetEmail(Co_Cmp_Cons_Email, doc.ds(CO).ds(CMP).ds(CONS).de(EMAIL), "X135");
        
        
        // 3. Criteria determining origin (1)   
        addFacetCheck(Co_Criteria_Op_Flg, doc.ds(CO).ds(CRITERIA).de(OP) ); 
        addFacetCheck(Co_Criteria_Sw_Flg, doc.ds(CO).ds(CRITERIA).de(SW) );
        addFacetCheck(Co_Criteria_Tar_Chg_Flg, doc.ds(CO).ds(CRITERIA).ds(TAR).de(CHG) );
        addFacetCheck(Co_Criteria_Pva_Flg, doc.ds(CO).ds(CRITERIA).ds(PVA).de(FLG) );
              
        addFacetNumeric(Co_Criteria_Pva_Rat, doc.ds(CO).ds(CRITERIA).ds(PVA).de(RAT), "RAT");  
        
        addFacetCheck(Co_Cedeao_Flg, doc.ds(CO).ds(CRITERIA).ds(CEDEAO).de(FLG) );
        addFacetCheck(Co_Uemoa_Flg, doc.ds(CO).ds(CRITERIA).ds(UEMOA).de(FLG) );

        
    	// 4. Quantity, nature, of goods and number of packages
        addFacetNumericPositive(Co_Pck_Nbr, doc.ds(CO).ds(PCK).de(NBR), "Pack_Nbr"); 
        //addFacetText( Co_Pck_Cod, doc.ds(CO).ds(PCK).de(COD),  "UX2");
        addFacetHistorize(Co_Pck_Cod, doc.ds(CO).ds(PCK).de(COD), un.adtcommons.client.visual.DHMMapManager.getModel(doc, PKG_TAB, 0), "PCK_CODNAM" );
        addFacetText(Co_Pck_Dsc, doc.ds(CO).ds(PCK).de(DSC),"UX35");
        //addFacetHistorize( Co_Pck_Cod, doc.ds(CO).ds(PCK).de(COD), doc.htModel(PCK_TAB, 0), "PCK_CODNAM");
        addFacetMemo( Co_Pck_Mrk, doc.ds(CO).ds(PCK).de(MRK),"X50*4*35");
        
    	// 5. Tariff and statistical nomenclature       
        addFacetText(Co_Tar_Cod, doc.ds(CO).ds(TAR).de(COD),"N10"); 
        addFacetMemo( Co_Tar_Dsc, doc.ds(CO).ds(TAR).de(DSC), "X50*4*35");        
        
        
    	// 6. Approval no of goods
        addFacetText(Co_Product_Nbr, doc.ds(CO).ds(PRODUCT).de(NBR), "N11");         
        
        
    	// 7. Gross weight or other measure

        addFacetNumericPositive(Co_Wgt_Grs, doc.ds(CO).ds(WGT).de(GRS), "WGT"); 
        //addFacetText(Co_Sup_Cod, doc.ds(CO).ds(SUP).de(COD), "N2");
        addFacetHistorize(Co_Sup_Cod, doc.ds(CO).ds(SUP).de(COD), un.adtcommons.client.visual.DHMMapManager.getModel(doc, UOM_TAB, 0), "UOM_CODNAM" );
        addFacetText(Co_Sup_Dsc, doc.ds(CO).ds(SUP).de(DSC), "AN35");
        
        
    	// 8. Invoice value      
        addFacetNumericPositive(Co_Inv_amt, doc.ds(CO).ds(INV).de(AMT), "NMU"); 
        //addFacetText(Co_Cur_Cod, doc.ds(CO).ds(CUR).de(COD), "AN3");    
        addFacetHistorize(Co_Cur_Cod, doc.ds(CO).ds(CUR).de(COD), un.adtcommons.client.visual.DHMMapManager.getModel(doc, CUR_TAB, 0), "CUR_CODNAM" );
        
    	// 9. Declaration by exporter      
        addFacetText(Co_Cmp_Exp_Nam, doc.ds(CO).ds(CMP).ds(EXP).de(NAM), "X200"); 
        addFacetText(Co_Cmp_Exp_Cty, doc.ds(CO).ds(CMP).ds(EXP).de(CTY), "X200");
        addFacetDate(Co_Cmp_Exp_Sign_Dat, doc.ds(CO).ds(CMP).ds(EXP).ds(SIGN).de(DAT), "Date");
        //add(200, 745, 100, 20, lng("Sigature"));
        
        
    	// 10. Certification by the appropriate authority
        addFacetText(Co_Auth_Nam, doc.ds(CO).ds(AUTH).de(NAM), "X200"); 
        addFacetText(Co_Auth_Cty, doc.ds(CO).ds(AUTH).de(CTY), "X200");
        addFacetDate(Co_Auth_Sign_Dat, doc.ds(CO).ds(AUTH).ds(SIGN).de(DAT), "Date");

    	// 11. Certification by customs 
        //addFacetText(Co_Cuo_Cod, doc.ds(CO).ds(CUO).de(COD), "UN5");
        addFacetHistorize(Co_Cuo_Cod, doc.ds(CO).ds(CUO).de(COD), un.adtcommons.client.visual.DHMMapManager.getModel(doc, CUO_TAB, 0), "CUO_CODNAM" );
        addFacetText(Co_Cuo_Nam, doc.ds(CO).ds(CUO).de(NAM), "UN75");
        addFacetText(Co_Dau_Mod, doc.ds(CO).ds(DAU).de(MOD), "UN3");
        addFacetText(Co_Dau_Reg_Nbr, doc.ds(CO).ds(DAU).ds(REG).de(NBR), "N10"); 
        addFacetDate(Co_Dau_Reg_Dat, doc.ds(CO).ds(DAU).ds(REG).de(DAT), "Date");      
        addFacetText(Dpa_Exa_Cod, doc.ds(DPA).ds(EXA).de(COD), "X30");  
        addFacetText(Dpa_Exa_Nam, doc.ds(DPA).ds(EXA).de(NAM), "UN70");  
        addFacetText(Dpa_Exa_Cty, doc.ds(DPA).ds(EXA).de(CTY), "UN70");   
        addFacetDate(Dpa_Exa_Sign_Dat, doc.ds(DPA).ds(EXA).ds(SIGN).de(DAT), "Date"); 
        
        
    	// 12. Request for verification
        addFacetText(Dpa_Cuo_Cod, doc.ds(DPA).ds(CUO).de(COD), "UN5"); 
        addFacetText(Dpa_Cuo_Nam, doc.ds(DPA).ds(CUO).de(NAM), "UN75"); 
        addFacetText(Dpa_Cuo_Adr, doc.ds(DPA).ds(CUO).de(ADR), "UN75");
        addFacetText(Dest_Exa_Cod, doc.ds(DEST).ds(EXA).de(COD), "UN17");        
        addFacetText(Dest_Exa_Nam, doc.ds(DEST).ds(EXA).de(NAM), "UN80");
        addFacetText(Dest_Exa_Cty, doc.ds(DEST).ds(EXA).de(CTY), "UN75");
        addFacetDate(Dest_Exa_Sign_Dat, doc.ds(DEST).ds(EXA).ds(SIGN).de(DAT), "Date");        
        
    	// 13. Verification
        addFacetText(Dpa_Ctl_Exa_Cod, doc.ds(DPA).ds(CTL).ds(EXA).de(COD), "UN17");
        addFacetText(Dpa_Ctl_Exa_Nam, doc.ds(DPA).ds(CTL).ds(EXA).de(NAM), "UN80");
        addFacetText(Dpa_Ctl_Exa_Cty, doc.ds(DPA).ds(CTL).ds(EXA).de(CTY), "UN75");
        addFacetDate(Dpa_Ctl_Exa_Sign_Dat, doc.ds(DPA).ds(CTL).ds(EXA).ds(SIGN).de(DAT), "Date");
        
        addFacetCheck(Dpa_Ctl_Correct_Flg, doc.ds(DPA).ds(CTL).ds(RSLT).de(CORR)); 
        addFacetCheck(Dpa_Ctl_Not_Correct_Flg, doc.ds(DPA).ds(CTL).ds(RSLT).de(NOTCORRECT)); 

    }

    /**
     * Method for initialising VP_Certificate client rules <!-- begin-user-doc --> <!-- end-user-doc -->
     * 
     * @see so.kernel.client.KVisualPage#initRules()
     * @generated NO
     */
    @Override
    public void initRules() {
    	
    	D_eCO doc = (D_eCO) getDocument();
    	
        Co_Cty_Dsc.setEnabled(false);              
        //Co_Nbr.setEnabled(false);                
    	// 4. Quantity, nature, of goods and number of packages
        Co_Pck_Dsc.setEnabled(false);      
    	// 5. Tariff and statistical nomenclature  
        //addRule(doc.ds(CO).ds(TAR).de(COD), new R_OpenTar(doc),  so.kernel.core.events.EventConstants.PREPARE_ATTACHED_FINDER_ID);
        //Co_Tar_Dsc.setEnabled(false);                 
    	// 6. Approval no of goods
        //Co_Product_Nbr.setEnabled(false);          
    	// 7. Gross weight or other measure
        Co_Sup_Dsc.setEnabled(false);                
    	// 11. Certification by customs 
        Co_Cuo_Nam.setEnabled(false); 
      	// 12. Request for verification
        Dpa_Cuo_Nam.setEnabled(false);  


    }

    private static final long serialVersionUID = 1L;

    /**
     * Returns translated string <!-- begin-user-doc --> Returns translated string <!-- end-user-doc -->
     * 
     * @generated
     */
    @SuppressWarnings("unused")
    private static String lng(String property) {
        return so.i18n.IntlObj.createMessage("un.asyeco", property);
    }

}