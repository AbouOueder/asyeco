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
import un.asyeco.product.DS_ProductItm;
import un.asyeco.product.D_Product;
import un.kernel.core.KDocument;

/**
 * Visual Page for null of null <!-- begin-user-doc --> <!-- end-user-doc -->
 * 
 * @generated NON
 */
public class VP_ProductItem extends AWVisualTemplate implements C_Product {
    // ELF Declarative: Visual controls

	
	//Criteria goods wholly produced in the Community - Flag
	transient private JCheckBox Criteria_Op_Flg = new JCheckBox();
	
	//Criteria goods sufficiently processed or worked - Flag
	transient private JCheckBox Criteria_Sw_Flg = new JCheckBox();
	
	//Criteria goods sufficiently processed or worked with changing of tariff position - Flag
	transient private JCheckBox Criteria_Tar_Chg_Flg = new JCheckBox();
	
	//Criteria goods sufficiently processed or worked with value added - Flag
	transient private JCheckBox Criteria_Pva_Flg = new JCheckBox();
	
	//Criteria goods sufficiently processed or worked with value added rate
	transient private ElfField Criteria_Pva_Rat = elfFieldPool.getElfField();
	
	//Tariff position (10)
	transient private ElfField Tar_Cod = elfFieldPool.getElfField();

	//Tariff description (10)
	transient private ElfField Tar_Dsc = elfFieldPool.getElfField();

	//Goods commercial description 
	transient private ElfField Gds_Dsc = elfFieldPool.getElfField();
	
	//Product number
	transient private ElfField Product_Nbr = elfFieldPool.getElfField();
	
	//Producer company code
	transient private ElfField Cmp_Prod_Cod = elfFieldPool.getElfField();
	
	//Producer company name
	transient private ElfField Cmp_Prod_Nam = elfFieldPool.getElfField();


    /**
     * Parameterless constructor required by desktop persistence <!-- begin-user-doc --> <!-- end-user-doc -->
     * 
     * @generated NON
     */
    public VP_ProductItem() {
        super("asyeco.SCAN", 925, 670);
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

		// Title stripes
		addTitleStripeAndFlag(0, 0, 925, 25, lng("Products Page"));
		//addTitledBorder(0, 35, 920, 680, lng(""));		
		addTitledBorder(15, 155, 895, 530, lng("Product informations"));
		

        // Label
		
		addTitledBorder(15, 45, 895, 100, lng("Producer"));
		add(50, 85, 200, 20, lng("Producer Ref. and Name"));
		
		
		addTitledBorder(40, 190, 840, 200, lng("Product"));
		add(50, 220, 200, 20, lng("Approval no of product")); 
		add(50, 260, 300, 20, lng("Tariff & Statistical Nomenclature"));          		 
		add(50, 320, 300, 20, lng("Commercial description of goods"));    
		
       	// Criteria determining origin (1)         
		addTitledBorder(40, 410, 840, 260, lng("Criteria determining origin"));
        add(50, 440, 300, 20, lng("a. Goods wholly produced in the Community"));        
        add(50, 480, 300, 20, lng("b. Goods sufficiently processed or worked"));        
        add(60, 520, 400, 20, lng("b.1 According to the criterion of changing of tariff position"));            
        add(60, 560, 300, 20, lng("b.2 According to the criterion of value added"));     
        add(70, 600, 100, 20, lng("Rate"));  
        add(300, 600, 40, 20, lng("%"));  

        // Editable Visual controls
        
        add(50, 105, 140, 20, Cmp_Prod_Cod,  lng("Producer Reference"));
        add(200, 105, 650, 20, Cmp_Prod_Nam,  lng("Producer name"));
        
		add(50, 240, 160, 20, Product_Nbr, lng("Approval no of product")); 
		add(50, 280, 160, 20, Tar_Cod, lng("Tariff & Statistical Nomenclature")); 
		add(260, 280, 600, 40, Tar_Dsc, lng("Nomenclature description")); 
		add(50, 340, 810, 20, Gds_Dsc, lng("Commercial description of goods"));   
                              
        // Criteria determining origin (1)   
        add(400, 430, 40, 40, Criteria_Op_Flg, lng("")); 
        add(400, 470, 40, 40, Criteria_Sw_Flg , lng(""));
        add(400, 510, 40, 40, Criteria_Tar_Chg_Flg, lng(""));
        add(400, 550, 40, 40, Criteria_Pva_Flg , lng(""));
        add(190, 600, 100, 20, Criteria_Pva_Rat, lng(""));  
 

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
    	DataSet product = getAttachedDataSet();

        addFacetText(Cmp_Prod_Cod, doc.ds(CMP).ds(PROD).de(COD),"N7");      
        addFacetText(Cmp_Prod_Nam, doc.ds(CMP).ds(PROD).de(NAM), "UX135");   
    	
    	addFacetText(Product_Nbr, product.ds(PRODUCT).de(NBR), "N11");
    	addFacetText(Tar_Cod, product.ds(TAR).de(COD),"N10"); 
    	addFacetMemo( Tar_Dsc, product.ds(TAR).de(DSC), "X50*4*35"); 
    	addFacetText(Gds_Dsc, product.ds(GDS).de(DSC),"X250"); 
      
        
        // 3. Criteria determining origin (1)   
        addFacetCheck(Criteria_Op_Flg, product.ds(CRITERIA).ds(OP).de(FLG)); 
        addFacetCheck(Criteria_Sw_Flg, product.ds(CRITERIA).ds(SW).de(FLG) );
        addFacetCheck(Criteria_Tar_Chg_Flg, product.ds(CRITERIA).ds(TAR).ds(CHG).de(FLG) );
        addFacetCheck(Criteria_Pva_Flg, product.ds(CRITERIA).ds(PVA).de(FLG) );
              
        addFacetNumeric(Criteria_Pva_Rat, product.ds(CRITERIA).ds(PVA).de(RAT), "RAT");  

    }

    /**
     * Method for initialising VP_Certificate client rules <!-- begin-user-doc --> <!-- end-user-doc -->
     * 
     * @see so.kernel.client.KVisualPage#initRules()
     * @generated NO
     */
    @Override
    public void initRules() {
    	//Criteria_Pva_Rat.setEnabled(false);
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