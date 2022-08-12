/**
 *  ECOWAS VERSION="1.0.0" TYPE="MODULE eCO": NEW COMMENT (HM: 28-Jun-2022)
 *  <p>
 *  eCO - Visual Form for Scanned Document
 *  <p>
 *  @author Aboubacar OUEDRAOGO <a href="mailto:ouederaboubakr@gmail.com">ouederaboubakr@gmail.com</a>
 */
package un.asyeco.eco.client;

import so.kernel.client.VisualForm;

/**
 * Visual form used as a container for the visual page.
 * 
 * @author MyName
 */
public class VF_ScannedDocs extends VisualForm {

	private static final long serialVersionUID = 1L;
	public static final String FORM_TITLE = lng("Scan Docs");
	/**
	 * Constructor - sets the name of the form.
	 */
	public VF_ScannedDocs() {
		super();
		setName(FORM_TITLE);
	}

	/**
	 * Initialisation of the visual pages in the form.
	 */
	public void initializePages() {
		//addPage(new VP_ScannedDocs());
		addPage(new VP_ScannedDocuments());
	}

	public boolean isTabbedPaneAlwaysVisible() {
		return true;
	}

	/**
	 * Retrieves a property string in the current working language.
	 */
	public static String lng(String property) {
		return so.i18n.IntlObj.createMessage("un.asyeco", property);
	}
}
