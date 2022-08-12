//   $Header: /home/asycuda/home/cvsroot/asybrk/Shared/src/un/broker/serial/ast/C_Ast.java,v 1.7 2012-07-03 07:37:24 kazan Exp $

package un.asyeco.serial.ast;

import static so.kernel.core.events.EventConstants.INTERNAL_EVENTS_MAX;
import so.kernel.core.Operation;
/**
 */
public interface C_Ast {

	/**
	 * DPP Model.
	 * 
	 */
	public final static Integer FIRST_VALUE = new Integer(0);

	// Status
	public static final String ST_REGISTERED = "Registered";

	public static final String ST_DELETED = "Deleted";

	// Operation class name
	public static final String OC_NEW = "New";

	public static final String OC_AMEND = "Amend";

	public static final String OC_DELETE = "Delete";

	// Operation name
	public static final String OP_DESIGN = Operation.DESIGN_OPERATION_NAME;

	public static final String OP_VIEW = Operation.VIEW_OPERATION_NAME;

	public static final String OP_CREATE = "Create";

	public static final String OP_AMEND = "Amend";

	public static final String OP_DELETE = "Delete";

	public static final String OP_LOCK = Operation.LOCK_OPERATION_NAME;

	public static final String OP_UNLOCK = Operation.UNLOCK_OPERATION_NAME;

	// Operation final action name
	public static final String OA_VALIDATE = "Validate";

	public static final String OA_DELETE = "Delete";

	// Operation event
	public static final int OE_CREATE = INTERNAL_EVENTS_MAX + 1001;

	public static final int OE_AMEND = INTERNAL_EVENTS_MAX + 1002;

	public static final int OE_DELETE = INTERNAL_EVENTS_MAX + 1003;

	// Visual middle events
	public static final int DO_PRINT = INTERNAL_EVENTS_MAX + 1050;

	public static final int CHK_KEYS = INTERNAL_EVENTS_MAX + 1060;

	/**
	 * Reference Model.
	 * 
	 */

	// Customs sites, offices
	public static final String CUO_TAB = "UN_CUO_TAB";

	public static final String CUO_TAB_ar = "UN_CUO_TAB_ar";

	public static final String CUO_COD = "CUO_COD";

	public static final String CUO_NAM = "CUO_NAM";

	static public final String CUO_TAB_EVENT = "CUO_TAB_EVENT";

	static public final String CUO_TAB_EVENT_ar = "CUO_TAB_EVENT_ar";

	public static final int CUO_LOAD = INTERNAL_EVENTS_MAX + 141;

	public static final int CUO_LOAD_ar = INTERNAL_EVENTS_MAX + 142;
	
	
	public static final String CTY_TAB = "UN_CTY_TAB"; // Country
	
	public static final String CTY_COD = "CTY_COD";
	
	public static final String CTY_DSC = "CTY_DSC";
	
	public static final String CTY_TAB_EVENT = "CTY_TAB_EVENT"; // Cty_tab_event
	
	public static final int CTY_TAB_LOAD = INTERNAL_EVENTS_MAX + 145;	// Event fired when table is loaded
	


	/**
	 * Data Model.
	 * 
	 */

	// GCF
	static public final String INSTANCE_ID = "instanceId"; // instance Id

	// Data name
	static public final String CHR = "CHR"; // Char serial (letter or digit)

	static public final String COD = "COD"; // Code

	static public final String CUR = "CUR"; // Current

	static public final String INI = "INI"; // Initial

	static public final String NAM = "NAM"; // Name

	static public final String DTI = "DTI"; // Direct trade Input

	static public final String SER = "SER"; // Serial number

	static public final String SIT = "SIT"; // Customs site

	static public final String TMZ = "TMZ"; // Time zone

	static public final String YER = "YER"; // Year

	static public final String WDE = "WDE"; // Working date

	public static final String LNG = "LNG";
	
	static public final String CTY = "CTY"; // Country
	
	static public final String DSC = "DSC"; // Country

}
