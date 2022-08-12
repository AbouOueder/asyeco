package un.asyeco.serial.eco.submit.client;

import so.kernel.client.elf.ElfField;
import un.adtcommons.client.visual.AWVisualTemplate;
import un.asyeco.serial.eco.submit.C_Sub;
import un.asyeco.serial.eco.submit.D_Sub;

public class VP_Sub extends AWVisualTemplate implements C_Sub {

	// Declare visual controls

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	transient ElfField Fld_Year = elfFieldPool.getElfField(); // Year
	
	transient ElfField Fcx_CountryCode = elfFieldPool.getElfField(); // Country, code

	transient ElfField Fcx_CountryName = elfFieldPool.getElfField(); // Country,name

	transient ElfField Fcx_SiteCode = elfFieldPool.getElfField(); // Customs site, code

	transient ElfField Fcx_SiteName = elfFieldPool.getElfField(); // Customs site, name

	transient ElfField Fld_Serial = elfFieldPool.getElfField(); // Serial char

	transient ElfField Fld_InitialNbr = elfFieldPool.getElfField(); // Initial serial number

	transient ElfField Fld_CurrentNbr = elfFieldPool.getElfField(); // Current serial number

	/*
	 * transient KTextField Fld_Year = textFieldPool.getKTextField(); // Year
	 * transient KComboBox Fcx_SiteCode = getComboBoxPool().getKComboBox(); //
	 * Customs site, code transient KComboBox Fcx_SiteName =
	 * getComboBoxPool().getKComboBox(); // Customs site, name transient KTextField
	 * Fld_Serial = textFieldPool.getKTextField(); // Serial char transient
	 * KTextField Fld_InitialNbr = textFieldPool.getKTextField(); // Initial
	 * serial number transient KTextField Fld_CurrentNbr =
	 * textFieldPool.getKTextField(); // Current serial number
	 */

	/**
	 * Constructor - initializes the visual components of the page. This is the
	 * first page of our "Identification" form.
	 * 
	 */

	public VP_Sub() {
		super("",450,450);
		initVisualControlsI18n();
	}

	/*	public void initVisualPage() {
		setSize(400, 280);
		setBackgroundFilter(new Color(51, 51, 255, 47), true);
		setBackgroundGradientFilter(0, 0, new Color(255, 255, 255, 0), -50, -50, new Color(102, 255, 255, 109), 1);
		setDefaultJTextFieldColor(new Color(0, 0, 51, 255), new Color(235, 233, 237, 255), true);
		setDefaultJTextFieldFont(new Font("Arial", Font.BOLD, 12));
		setDefaultJLabelFont(new Font("Arial", Font.BOLD, 11));
		setDefaultKTitleStripeFont(new Font("Arial", Font.PLAIN, 15));
	}*/

	public void initVisualControls() {

		addTitleStripeAndFlag(0, 0, 450, 25, lng("Certificate of Origin submission - serial data"));
		int titledBorderY=55;
		addTitledBorder(10, titledBorderY, 440, PADDING+300+BOTTOM_PADDING, lng("Serial Configuration"));
		add(20, titledBorderY+PADDING, 150, 18, lng("Year"));
		add(20, titledBorderY+PADDING+40, 150, 18, lng("Country"));
		add(20, titledBorderY+PADDING+80, 150, 18, lng("Customs office"));
		add(20, titledBorderY+PADDING+120, 220, 16, lng("Serial character, letter or digit"));
		add(20, titledBorderY+PADDING+160, 148, 20, lng("Serial - Initial number"));
		add(20, titledBorderY+PADDING+180, 148, 20, lng("Current number"));

		// adding visual controls

		add(20, titledBorderY+PADDING+20, 40, 20, Fld_Year, lng("Year"));
		add(20, titledBorderY+PADDING+60, 70, 20, Fcx_CountryCode, lng("Country - code"));
		add(100, titledBorderY+PADDING+60, 340, 20, Fcx_CountryName, lng("Country - name"));
		add(20, titledBorderY+PADDING+100, 70, 20, Fcx_SiteCode, lng("Customs office - code"));
		add(100, titledBorderY+PADDING+100, 340, 20, Fcx_SiteName, lng("Customs office - name"));
		add(20, titledBorderY+PADDING+140, 24, 20, Fld_Serial, lng("Serial letter or digit"));
		add(20, titledBorderY+PADDING+180, 100, 20, Fld_InitialNbr, lng("Serial - Initial number"));
		add(20, titledBorderY+PADDING+220, 100, 20, Fld_CurrentNbr, lng("Serial - Current number"));

	}

	public void initFacets() {

		D_Sub reg = (D_Sub) getDocument();

		// Format to review. need to develop specific year format
		addFacetText(Fld_Year, de(YER), "N4"); // Year

		addFacetHistorize(Fcx_CountryCode, ds(CTY).de(COD), reg.htModel(CTY_TAB, 0), "CTY_CODNAM"); // Country code
		addFacetHistorize(Fcx_CountryName, ds(CTY).de(DSC), reg.htModel(CTY_TAB, 3), "CTY_NAM"); // Country name
		
		// addFacetReference(Fcx_SiteCode, ds(SIT).de(COD), reg.ref(CUO_TAB),
		// "CUO_CODNAM"); // Customs site, code
		addFacetHistorize(Fcx_SiteCode, ds(SIT).de(COD), reg.htModel(CUO_TAB, 0), "CUO_CODNAM"); // Customs
		// site,
		// code
		// addFacetReference(Fcx_SiteName, ds(SIT).de(NAM), reg.ref(CUO_TAB),
		// "CUO_NAM"); // Customs site, name
		addFacetHistorize(Fcx_SiteName, ds(SIT).de(NAM), reg.htModel(CUO_TAB, 3), "CUO_NAM"); // Customs
		// site,
		// name

		addFacetText(Fld_Serial, de(CHR), "UN1"); // Serial char
		addFacetText(Fld_InitialNbr, ds(SER).de(INI), "N11"); // Initial
		// serial number
		addFacetText(Fld_CurrentNbr, ds(SER).de(CUR), "N11"); // Current
		// serial number

	}
	private static String lng(String property) {
		return so.i18n.IntlObj.createMessage("un.asyeco", property);
	}
}
