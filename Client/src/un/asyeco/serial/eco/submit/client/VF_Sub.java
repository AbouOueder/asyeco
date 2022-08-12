package un.asyeco.serial.eco.submit.client;

import so.kernel.client.VisualForm;

/**
 * Visual form used as a container for the visual page.
 * 
 * @author Aboubacar Ouedraogo
 */
public class VF_Sub extends VisualForm {

	private static final long serialVersionUID = 1L;

	/**
	 * Constructor - sets the name of the form.
	 */
	public VF_Sub() {
		super();
		setName(lng("CO submit series"));
	}

	/**
	 * Initialisation of the visual pages in the form.
	 */
	public void initializePages() {
		addPage(new VP_Sub());
	}

	public boolean isTabbedPaneAlwaysVisible() {
		return false;
	}

	private static String lng(String property) {
		return so.i18n.IntlObj.createMessage("un.asyeco", property);
	}

}
