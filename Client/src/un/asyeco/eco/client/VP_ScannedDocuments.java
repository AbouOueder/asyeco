/**
 *  ECOWAS VERSION="1.0.0" TYPE="MODULE eCO": NEW COMMENT (HM: 28-Jun-2022)
 *  <p>
 *  eCO - Visual Page for scanned documments
 *  <p>
 *  @author Aboubacar OUEDRAOGO <a href="mailto:ouederaboubakr@gmail.com">ouederaboubakr@gmail.com</a>
 */
package un.asyeco.eco.client;

import java.awt.Dimension;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;

import javax.swing.ComboBoxModel;
import javax.swing.DefaultComboBoxModel;

import so.ems.client.facet.DefaultPropertyColumn;
import so.ems.client.facet.DefaultPropertyModel;
import so.ems.client.facet.PropertyColumnWithVisualFacet;
import so.ems.client.facet.VisualFacetBuilder;
import so.ems.client.facet.VisualFacetEMS;
import so.ems.client.facet.property.convertor.StringConvertor;
import so.ems.convertor.BigInt2BytesConvertor;
import so.kernel.client.KLookupModel;
import so.kernel.client.elf.ElfField;
import so.kernel.core.Data;
import so.kernel.core.DataField;
import so.kernel.core.client.facets.AbstractVisualFacet;
import so.kernel.core.client.facets.VisualFacetJEditableComboBoxAdvanced;
import so.kernel.core.events.DoCloseEvent;
import so.swing.KComboBox;
import so.swing.filelist.KFileFilter;
import so.swing.filelist.KFileList;
import un.adtcommons.client.ems.ScanFileFilter;
import un.adtcommons.client.ems.ScanFileList;
import un.adtcommons.client.swing.models.ComboBoxTableViewModelFactory;
import un.adtcommons.client.visual.AWVisualTemplate;
import un.asyeco.eco.C_eCO;
import un.asyeco.eco.D_eCO;
import un.kernel.client.FormatDictionary;
import un.kernel.core.HistorizedModelDelegator;
import un.kernel.core.KDocument;


public class VP_ScannedDocuments extends AWVisualTemplate implements C_eCO {

	private static final long serialVersionUID = 1L;
	
	
	//CO country code
	transient private ElfField Co_Cty_Cod = elfFieldPool.getElfField();
	
	// CO country name
	transient private ElfField Co_Cty_Dsc = elfFieldPool.getElfField();
	
	//CO unique number
	transient private ElfField Co_Nbr = elfFieldPool.getElfField();
	
	//CO producer company code
	transient private ElfField Co_Cmp_Prod_Cod = elfFieldPool.getElfField();
	
	//CO producer company name
	transient private ElfField Co_Cmp_Prod_Nam = elfFieldPool.getElfField();

	//CO product number
	transient private ElfField Co_Product_Nbr = elfFieldPool.getElfField();
	
	//Departure office code
	transient private ElfField Co_Cuo_Cod = elfFieldPool.getElfField();
	
	//Departure office name
	transient private ElfField Co_Cuo_Nam = elfFieldPool.getElfField();
	
	transient private KFileList scanFileList = new ScanFileList();
	boolean loaded;

	/**
	 * Constructor - initializes the visual components of the page. This is the only page in our main form.
	 */

	public VP_ScannedDocuments() {
		super("asyeco.SCAN", 925, 800);
		initVisualControlsI18n();
	}

	protected boolean isBackgroundPaneUsed() {
		return false;
	}
	
	public void initVisualControls() { // y -62 / x - 2

		// Labels and title stripes
		addTitleStripeAndFlag(0, 0, 925, 25, lng("Attached Scanned Documents Page"));
		addTitledBorder(10, 35, 915, 170, lng("Certificate of origin Doc. Informations"));
		addTitledBorder(10,240,915,520,lng("List of Documents"));
		
		add(  20,  60, 150,  20, lng("Country of Certificate"));
		add( 455,  60, 150,  20, lng("Customs Office"));
		add(  20, 100, 150,  20, lng("Reference of Certificate"));
		add( 455, 100, 150,  20, lng("Reference of Product"));
		add(  20, 140, 150,  20, lng("Producer informations"));
		
		
		// Editable Visual controls
		add(  20,  80, 60,   20, Co_Cty_Cod, lng("Country Code"));
		add(  85,  80, 120,  20, Co_Cty_Dsc, lng("Country Name"));
		add( 455,  80, 60,   20, Co_Cuo_Cod, lng("Customs Office Code"));
		add( 520,  80, 120,  20, Co_Cuo_Nam, lng("Customs Office Name"));
		add(  20, 120, 150,  20, Co_Nbr, lng("Reference of Certificate"));
		add( 455, 120, 150,  20, Co_Product_Nbr, lng("Reference of Product"));
		add(  20, 160, 100,  20, Co_Cmp_Prod_Cod, lng("Producer Code"));
		add( 125, 160, 200,  20, Co_Cmp_Prod_Nam, lng("Producer Name"));
		
		scanFileList.setBounds(20, 340, 900, 400); //EMS
		add(scanFileList);		
		
	}


	// Initialization: Facets

	@Override
	public void initFacets() {
		D_eCO doc = (D_eCO) getDocument();
		
		addFacetText(Co_Cty_Cod, doc.ds(CO).ds(CTY).de(COD), "UN3");
		addFacetText(Co_Cty_Dsc, doc.ds(CO).ds(CTY).de(DSC), "UN75");
		addFacetText(Co_Cuo_Cod, doc.ds(CO).ds(CUO).de(COD), "UN5");
		addFacetText(Co_Cuo_Nam, doc.ds(CO).ds(CUO).de(NAM), "UN75");
		addFacetText(Co_Nbr,doc.ds(CO).de(NBR), "N11");
		addFacetText(Co_Product_Nbr, doc.ds(CO).ds(PRODUCT).de(NBR), "N11");
		addFacetText(Co_Cmp_Prod_Cod, doc.ds(CO).ds(CMP).ds(PROD).de(COD),"N7");
		addFacetText(Co_Cmp_Prod_Nam, doc.ds(CO).ds(CMP).ds(PROD).de(NAM), "UX135");

		addPropEMSFacet(de(FLP1),scanFileList);
		
	}
	protected void addPropEMSFacet(DataField field, KFileList fileList) {
		DefaultPropertyModel propertyModel = createPModel();

		KFileFilter filter = new ScanFileFilter();
		VisualFacetEMS facet = new VisualFacetEMS(this, field, fileList, BigInt2BytesConvertor.INSTANCE,
				filter, false, false, propertyModel);

		addVisualFacet(facet);

	}

	protected DefaultPropertyModel createPModel() {
		DefaultPropertyModel propertyModel = new DefaultPropertyModel();
		initPModel(propertyModel);
		return propertyModel;
	}

	protected void initPModel(DefaultPropertyModel propertyModel) {
		DefaultPropertyColumn column = new PropertyColumnWithVisualFacet(
				"Code", "Code", null, StringConvertor.getInstance(),
				new ATDFacetBuilder(this));
		propertyModel.addColumn(column);
		propertyModel.addFileNameColumn();
		propertyModel.addProgressColumn();
	}
	private  class ATDFacetBuilder implements VisualFacetBuilder<KComboBox> {
		private final HistorizedModelDelegator reference;
		private final KLookupModel lookupModel;

		ATDFacetBuilder(VP_ScannedDocuments vp) {
			D_eCO doc = (D_eCO) vp.getDocument();
			reference = un.adtcommons.client.visual.DHMMapManager.getModel(doc,ATD_TAB, 0);
			lookupModel = FormatDictionary.getInstance().getLookupFormat("ATD_CODNAM");
		}

		@Override
		public KComboBox makeComponent() {
			return new KComboBox();
		}

		@Override
		public AbstractVisualFacet<KComboBox> makeFacet(Data data, KComboBox comboBox) {
			int maxLen = lookupModel.getFieldLength();
			//GVA <patch ID="NPE when opening tarcking SAD with Doc" version="4.2.2" type="modification" date="Jun 19, 2013" author="Jaouhar"/>
			if (KDocument.isUsingAref(reference.getDocument()))
				loaded =  (reference.getDocument().getArefHistorizeSO(ATD_TAB) == null);
			else
				loaded =  (reference.getDocument().getHistorizeSnapshot(ATD_TAB) == null);

			VisualFacetJEditableComboBoxAdvanced<KComboBox> facet;
			if (lookupModel.size() > 1) {
				ComboBoxModel model;
				if (reference == null || loaded) {
					//GVA </patch ID="NPE when opening tarcking SAD with Doc"/>
					String str[] = new String[1];
					if (data.getContent() != null) {
						str[0] = data.getContent().toString();
					} else {
						str[0] = "n/a";
					}
					model = new DefaultComboBoxModel(str);
					comboBox.setModel(model);
				} else {

					model = ComboBoxTableViewModelFactory.getViewModel(reference, lookupModel);
					comboBox.setModel(model);
					comboBox.setPreferredPopupSize(new Dimension(lookupModel.getWidth(), 100));
					for(int i=0; i<lookupModel.size(); i++) {
						comboBox.setRangeAt(i, lookupModel.getColWidth(i), lookupModel.getColWidth(i));
					}
					comboBox.setTableViewInPopup(true);
				}
				facet = new VisualFacetJEditableComboBoxAdvanced<KComboBox>(data, comboBox);

			} else {
				if (reference == null) {
					String str[] = new String[1];
					if (data.getContent() != null) {
						str[0] = data.getContent().toString();
					} else {
						str[0] = "n/a";
					}
					comboBox.setModel(new DefaultComboBoxModel(str));
				} else {
					ComboBoxModel model =
						ComboBoxTableViewModelFactory.getViewModel(reference, lookupModel);
					comboBox.setModel(model);
				}
				facet = new VisualFacetJEditableComboBoxAdvanced<KComboBox>(data, comboBox);
			}

			facet.setMaxLen(maxLen);
			return facet;

		}

	}
	@Override
	public void initRules() {
		Co_Cty_Cod.setEnabled(false);
		Co_Cty_Dsc.setEnabled(false);
		Co_Cuo_Cod.setEnabled(false);
		Co_Cuo_Nam.setEnabled(false);
		Co_Nbr.setEnabled(false);
		Co_Product_Nbr.setEnabled(false);
		Co_Cmp_Prod_Cod.setEnabled(false);
		Co_Cmp_Prod_Nam.setEnabled(false);

		this.addMouseListener(new MouseAdapter(){
			public void mouseClicked(MouseEvent event){
				if (event.getClickCount()==2){
					if (getDocument().getStartedOperation().equals(OP_VIEW)){
						getDocument().fire(new DoCloseEvent(getDocument()));
					}

				}
			}
		});
		
	}

	


	private static String lng(String property) {
		return so.i18n.IntlObj.createMessage("un.asyeco", property);
	}
}
