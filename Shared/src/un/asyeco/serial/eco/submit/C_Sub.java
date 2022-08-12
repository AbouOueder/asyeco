package un.asyeco.serial.eco.submit;

import static so.kernel.core.events.EventConstants.INTERNAL_EVENTS_MAX;
import so.kernel.core.Operation;

public interface C_Sub {

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
	public static final int OI_CREATE = Operation.INTERNAL_OPERATIONS_MAX + 2004;

	public static final int OI_AMEND = Operation.INTERNAL_OPERATIONS_MAX + 2005;

	public static final int OI_DELETE = Operation.INTERNAL_OPERATIONS_MAX + 2006;

	// Visual middle events
	public static final int DO_PRINT = INTERNAL_EVENTS_MAX + 1070;

	public static final int CHK_KEYS = INTERNAL_EVENTS_MAX + 1080;

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

	public static final int CUO_LOAD = INTERNAL_EVENTS_MAX + 144;

	public static final int CUO_LOAD_ar = INTERNAL_EVENTS_MAX + 145;
	
	
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

	static public final String SER = "SER"; // Serial number

	static public final String SIT = "SIT"; // Customs site

	static public final String TMZ = "TMZ"; // Time zone

	static public final String YER = "YER"; // Year

	public static final String LNG = "LNG";

	static public final String WDE = "WDE"; // Working date
	
	static public final String CTY = "CTY"; // Country
	
	static public final String DSC = "DSC"; // Country

}
