/**
 * UNCTAD
 * ASYCUDA PROJECT
 * 
 */ 
package un.asyeco.eco;

import java.awt.Color;

import so.kernel.Global;
import so.kernel.core.FinderInfo;

/**
 * Sub Document Object Model for Finder Certificate of Certificate Document
 * <!-- begin-user-doc -->
 * <!-- end-user-doc -->
 * @generated
 */
public class F_eCO extends FinderInfo implements C_eCO {
	/**
 	 * Parameterless constructor required by desktop persistence
 	 * <!-- begin-user-doc -->
 	 * <!-- end-user-doc -->
 	 * @generated
 	 */
	public F_eCO() {
		super();
		// Size
		setDefaultSize(750, 400);
		setDefaultCriteriaSize(500, 400);

		// Searchable data
		setStatusSearching(true);
		/*setDefaultStatus(ST_CREATED);
		setDefaultStatusOr(new String[] {ST_CREATED, ST_CHANGED, ST_EXPIRED, ST_ISSUABLE, ST_ISSUED, ST_REJECTED, ST_REVOKED, ST_SUBMITTED, ST_VALIDATED});*/		
	}
	
	public static final F_eCO finder = new F_eCO();

	/**
	 * <!-- begin-user-doc -->
 	 * <!-- end-user-doc -->
	 * @see so.kernel.core.FinderInfo#define_FinderModel()
	 * @generated
	 */
	@Override
	protected void define_FinderModel() {
		addQueryBuildingPanel();
		addResultPanel();
		setStatusSearching(true);
	}//define_finderModel
	
	private final static long serialVersionUID = 1L;

	/**
	 * This method defines the Query or Criteria panel for the Finder finder
	 * <!-- begin-user-doc -->
 	 * <!-- end-user-doc -->
	 * @generated
	 */
	private void addQueryBuildingPanel() {
		

		addFinder_SearchFld(ds(CO).de(NBR), lng("CO number"), Global.INTEGER_TYPE);
		addFinder_SearchFld(ds(CO).de(CRN), lng("CRN"), Global.STRING_TYPE, "AN17");
		addFinder_SearchFld(ds(CO).ds(SUB).de(NBR), lng("Submit number"), Global.INTEGER_TYPE);
		addFinder_SearchFld(ds(CO).ds(CMP).ds(PROD).de(COD), lng("Producer code"), Global.STRING_TYPE, "AN17");
		addFinder_SearchFld(ds(CO).ds(CMP).ds(PROD).de(NAM), lng("Producer name"), Global.STRING_TYPE, "AN75");
		addFinder_SearchFld(ds(CO).ds(CTY).de(COD), lng("Country code origin "), Global.STRING_TYPE, "AN3");
		addFinder_SearchFld(ds(CO).ds(PRODUCT).de(NBR), lng("Product number"), Global.STRING_TYPE, "AN11");
		addFinder_SearchFld(ds(CO).ds(TAR).de(COD), lng("Tariff code"), Global.STRING_TYPE, "N10");
		addFinder_SearchFld(ds(CO).ds(REG).de(DAT), lng("Reg. Date"),Global.DATE_TYPE, "dd/MM/yyyy");
		addFinder_SearchFld(ds(CO).ds(SUB).de(DAT), lng("Submit. Date"),Global.DATE_TYPE, "dd/MM/yyyy");
		

		setDefaultCriteriaSize(500, 350);	
	}

	/**
	 * This method defines the Results panel for the Finder finder
	 * <!-- begin-user-doc -->
 	 * <!-- end-user-doc -->
	 * @generated
	 */
	private void addResultPanel() {
		addFinder_ViewFld(ds(CO).de(NBR), lng("CO number"), "N10");
		addFinder_ViewFld(ds(CO).de(CRN), lng("CRN"), "AN17");
		addFinder_ViewFld(ds(CO).ds(PRODUCT).de(NBR), lng("Product number"), "N7");
		addFinder_ViewFld(ds(CO).ds(SUB).de(NBR), lng("Submit number"), "N7");
		addFinder_ViewFld(ds(CO).ds(CMP).ds(PROD).de(COD), lng("Producer code"), "AN17");
		addFinder_ViewFld(ds(CO).ds(CMP).ds(PROD).de(NAM), lng("Producer name"), "AN75");		
		addFinder_ViewFld(ds(CO).ds(CTY).de(COD), lng("Country code origin "), "AN3");
		addFinder_ViewFld(ds(CO).ds(TAR).de(COD), lng("Tariff code"),  "N10");
		addFinder_ViewFld(ds(CO).ds(REG).de(DAT), lng("Reg. Date"), "dd/MM/yyyy");
		addFinder_ViewFld(ds(CO).ds(SUB).de(DAT), lng("Submit. Date"), "dd/MM/yyyy");
		// Sort
		setDefaultSort(getList(ds(CO).ds(CTY).de(COD), ds(CO).de(CRN)), UP);

		setResultPanelProperties();		
	}

	/**
	 * This method defines the properties of Results panel for the Finder finder
	 * <!-- begin-user-doc -->
 	 * <!-- end-user-doc -->
	 * @generated
	 */
	private void setResultPanelProperties() {
		setResultBackground(Color.WHITE);
		setResultGridColor(Color.WHITE);
		setShowGrid(false);
		setShowHorizontalLines(false);
		setShowVerticalLines(true);
		setRowMargin(1);
		F_Color.INSTANCE.apply(this);
		setDefaultSize(600, 300);			
	}	
		
	/**
	 * Returns translated string
	 * <!-- begin-user-doc -->
	 * Returns translated string
 	 * <!-- end-user-doc -->
	 * @generated 
	 */

	
	private static so.i18n.IntlObj lng(String key) {
		return new so.i18n.IntlObj("un.asyeco", key);
	}


}