/**
 *  ECOWAS VERSION="1.0.0" TYPE="MODULE eCO": NEW COMMENT (HM: 28-Jun-2022)
 *  <p>
 *  eCO - Visual Page
 *  <p>
 *  @author Aboubacar OUEDRAOGO <a href="mailto:ouederaboubakr@gmail.com">ouederaboubakr@gmail.com</a>
 */
package un.asyeco.product.client;

import javax.swing.ButtonGroup;
import javax.swing.JCheckBox;

import so.kernel.client.elf.ElfField;
import so.kernel.core.DataSet;
import so.kernel.core.KernelEventConstants;
import so.kernel.core.rules.KR_DataMandatory;
import so.swing.KPanel;
import un.adtcommons.client.visual.AWVisualTemplate;
import un.asyeco.eco.client.rules.R_OpenTar;
import un.asyeco.product.C_Product;
import un.asyeco.product.D_Product;
import un.kernel.core.KDocument;

/**
 * Visual Page for null of null <!-- begin-user-doc --> <!-- end-user-doc -->
 * 
 * @generated NON
 */
public class VP_Product extends AWVisualTemplate implements C_Product {
    // ELF Declarative: Visual controls
	
	//Producer company code
	transient private ElfField Cmp_Prod_Cod = elfFieldPool.getElfField();
	
	//Producer company name
	transient private ElfField Cmp_Prod_Nam = elfFieldPool.getElfField();
	
	//producer company cty
	transient private ElfField Cmp_Prod_Cty_Cod = elfFieldPool.getElfField();
	
	// producer company country name
	transient private ElfField Cmp_Prod_Cty_Dsc = elfFieldPool.getElfField();
		
	//Producer company adress
	transient private ElfField Cmp_Prod_Adr = elfFieldPool.getElfField();
	
	//Producer company adress 2
	transient private ElfField Cmp_Prod_Adr2 = elfFieldPool.getElfField();
	
	//Producer company email
		transient private ElfField Cmp_Prod_Email = elfFieldPool.getElfField(); //New
	
	//Producer company telephone number
	transient private ElfField Cmp_Prod_Tel = elfFieldPool.getElfField();
	
	//Producer company web site
	transient private ElfField Cmp_Prod_Wsite = elfFieldPool.getElfField();
	
	
	//Number total of products 
	transient private ElfField Product_Total = elfFieldPool.getElfField();
	
	

	

    /**
     * Parameterless constructor required by desktop persistence <!-- begin-user-doc --> <!-- end-user-doc -->
     * 
     * @generated NON
     */
    public VP_Product() {
        super("asyeco.SCAN", 625, 600);
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
		addTitleStripeAndFlag(0, 0, 625, 25, lng("Producer Page"));
		addTitledBorder(10, 35, 615, 600, lng("Poducer information"));

        
        // 1. Producer (registration number, name, address, ...)
        //add(5, 132, 160, 20, lng("Producer"));        
        add(20, 60, 160, 20, lng("Registration N°"));    
        add(360, 60, 160, 20, lng("Number total of products"));    
        add(20, 120, 70, 20, lng("Name"));        
        add(20, 180, 70, 20, lng("Address"));    
        add(20, 240, 70, 20, lng("Postal code"));    
        add(20, 300, 70, 20, lng("Telephone"));        
        add(20, 360, 70, 20, lng("Email"));
        add(20, 420, 70, 20, lng("Web site"));        
        add(20, 480, 70, 20, lng("Country"));
       // add(20, 380, 160, 20, lng("Number of products"));        
      
        
               

        // Editable Visual controls
        
        // Header
        
        // 1. Producer
        add(20, 80, 160, 20,  Cmp_Prod_Cod, lng("Registration N° of producer"));  
        add(360, 80, 160, 20, Product_Total, lng("Number total of products"));  
        add(20, 140, 500, 20, Cmp_Prod_Nam, lng("Name of producer"));   
        add(20, 200, 500, 20, Cmp_Prod_Adr, lng("Address of producer"));   
        add(20, 260, 500, 20, Cmp_Prod_Adr2, lng("Postal code"));        
        add(20, 320, 500, 20, Cmp_Prod_Tel, lng("Telephone of producer")); 
        add(20, 380, 500, 20, Cmp_Prod_Email, lng("Email"));        
        add(20, 440, 500, 20, Cmp_Prod_Wsite, lng("Web site"));
        add(20, 500, 40, 20,  Cmp_Prod_Cty_Cod,  lng("Country code of Certificate of producer"));
        add(70, 500, 450, 20, Cmp_Prod_Cty_Dsc,  lng("Country name of Certificate of producer"));

 
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
    	D_Product doc = (D_Product) getDocument();
        
        //Header
        

        // 1. Producer informations
        addFacetHistorize(Cmp_Prod_Cod, doc.ds(CMP).ds(PROD).de(COD), un.adtcommons.client.visual.DHMMapManager.getModel(doc, PROD_TAB, 0), "PROD_CODNAM");     
        //addFacetText(Cmp_Prod_Cod, doc.ds(CMP).ds(PROD).de(COD),"N7");      
        addFacetText(Cmp_Prod_Nam, doc.ds(CMP).ds(PROD).de(NAM), "UX135");   
        addFacetText(Cmp_Prod_Adr, doc.ds(CMP).ds(PROD).de(ADR), "UX135");    
        addFacetText(Cmp_Prod_Adr2, doc.ds(CMP).ds(PROD).de(ADR2), "UX135"); 
        addFacetText(Cmp_Prod_Tel, doc.ds(CMP).ds(PROD).de(TEL), "N17");        
        addFacetEmail(Cmp_Prod_Email, doc.ds(CMP).ds(PROD).de(EMAIL), "X135");
        addFacetEmail(Cmp_Prod_Wsite, doc.ds(CMP).ds(PROD).de(WSITE), "X135");
        addFacetText(Cmp_Prod_Cty_Cod, doc.ds(CMP).ds(PROD).ds(CTY).de(COD), "UN3");        
        addFacetText(Cmp_Prod_Cty_Dsc, doc.ds(CMP).ds(PROD).ds(CTY).de(DSC), "X135" ); 
        addFacetText(Product_Total, doc.ds(TOT).de(ITM),"N6");  

        //addFacetHistorize(Cmp_Prod_Cty_Cod, doc.ds(CMP).ds(CTY).de(COD), un.adtcommons.client.visual.DHMMapManager.getModel(doc, CTY_TAB, 0), "CTY_CODNAM");        
        //addFacetHistorize(Cmp_Prod_Cty_Dsc, doc.ds(CMP).ds(CTY).de(DSC), un.adtcommons.client.visual.DHMMapManager.getModel(doc, CTY_TAB, 3), "CTY_NAM" );      

        
    }

    /**
     * Method for initialising VP_Certificate client rules <!-- begin-user-doc --> <!-- end-user-doc -->
     * 
     * @see so.kernel.client.KVisualPage#initRules()
     * @generated NO
     */
    @Override
    public void initRules() {
    	
    	D_Product doc = (D_Product) getDocument();
    	Cmp_Prod_Nam.setEnabled(false);
    	Cmp_Prod_Adr.setEnabled(false);
    	Cmp_Prod_Adr2.setEnabled(false);
    	Cmp_Prod_Tel.setEnabled(false);
    	Cmp_Prod_Email.setEnabled(false);
    	Cmp_Prod_Wsite.setEnabled(false);
    	Cmp_Prod_Cty_Cod.setEnabled(false);
    	Cmp_Prod_Cty_Dsc.setEnabled(false);
    	Product_Total.setEnabled(false);
                    
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