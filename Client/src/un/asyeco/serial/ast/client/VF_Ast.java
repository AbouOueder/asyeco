//
//   $Header: /home/asycuda/home/cvsroot/asybrk/Client/src/un/broker/serial/ast/client/VF_Ast.java,v 1.7 2010-07-30 14:10:38 john Exp $

package un.asyeco.serial.ast.client;

import so.kernel.client.VisualForm;


/**
 * Visual form used as a container for the visual page.
 * 
 * @author Elya Bozhinoff
 */
public class VF_Ast extends VisualForm {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	/**
	 * Constructor - sets the name of the form.
	 */
	public VF_Ast() {
		super();
		setName(lng("Submit series"));
	}

	/**
	 * Initialisation of the visual pages in the form.
	 */
	public void initializePages() {

		VP_Ast vp_Ast = new VP_Ast();
		addPage(vp_Ast);
	}

	@Override
	public boolean isTabbedPaneAlwaysVisible() {
		return false;
	}

	public static String lng(String property) {
		return so.i18n.IntlObj.createMessage("un.asyeco", property);
	}

}
