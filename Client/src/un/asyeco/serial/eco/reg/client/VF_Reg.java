package un.asyeco.serial.eco.reg.client;

import so.kernel.client.VisualForm;

/**
 * Visual form used as a container for the visual page.
 * 
 * @author Aboubacar Ouedraogo
 */
public class VF_Reg extends VisualForm {

	private static final long serialVersionUID = 1L;

	/**
	 * Constructor - sets the name of the form.
	 */
	public VF_Reg() {
		super();
		setName(lng("CO registration series"));
	}

	/**
	 * Initialisation of the visual pages in the form.
	 */
	public void initializePages() {
		addPage(new VP_Reg());
	}

	public boolean isTabbedPaneAlwaysVisible() {
		return false;
	}

	private static String lng(String property) {
		return so.i18n.IntlObj.createMessage("un.asyeco", property);
	}

}
