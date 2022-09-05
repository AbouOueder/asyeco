/**
 *  ECOWAS VERSION="1.0.0" TYPE="MODULE eCO": NEW COMMENT (HM: 28-Jun-2022)
 *  <p>
 *  eCO - Constants Interface
 *  <p>
 *  @author Aboubacar OUEDRAOGO <a href="mailto:ouederaboubakr@gmail.com">ouederaboubakr@gmail.com</a>
 */
package un.asyeco.eco;

import static so.kernel.core.events.EventConstants.INTERNAL_EVENTS_MAX;

import java.awt.Color;

import so.kernel.core.Operation;
import so.kernel.core.events.EventConstants;


public interface C_eCO {
	

		// Definition of the Constant data
	
	// Color Palette Section
	public static final Color Black = Color.BLACK; // Black
	public static final Color Yellow = Color.YELLOW; // Yellow
	public static final Color Pink = new Color(255, 185, 255); // Pink
	public static final Color Violet = new Color(184, 184, 220); // Violet

	public static final Color prelodgedColor = new Color(205, 212, 226); // status
																			// is
																			// not
																			// used
	public static final Color archivedColor = new Color(176, 194, 225); // status
																		// is
																		// not
																		// used
		
	public static final Color storedColor = new Color(237, 237, 237);
	public static final Color registeredColor = new Color(222, 226, 232);
	public static final Color validatedColor = new Color(210, 218, 232);
	public static final Color customsValidatedColor = new Color(197, 207, 226);
	public static final Color sentColor = new Color(186, 200, 226);
	public static final Color cancelledColor = new Color(159, 178, 215);
	public static final Color selectedColor = new Color(57, 74, 105);


		// Visual middle events
		public static final int ACT_ITM_NEW = EventConstants.INTERNAL_EVENTS_MAX + 2;


		// Status of the document
		// Data Model.

		// Key
		public static final String INSTANCE_ID = "INSTANCE_ID";

		// Segment Identity
		
		public static final String PDF 			= "PDF";
		public static final String PDF1 		= "PDF1";
		public static final String PDF2 		= "PDF2";
		public static final String PDF3 		= "PDF3";
		public static final String PTY 			= "PTY";
		public static final String WDE 			= "WDE";
		public static final String LNG			= "LNG";

		public static final String LCT 			= "LCT";
		
		public static final String IDE = "IDE";
		
		public static final String CO = "CO";
		
		public static final String CTY = "CTY";
		
		public static final String COD = "COD";
		
		public static final String DSC = "DSC";
		
		public static final String CMP = "CMP";
		
		public static final String PROD = "PROD";
		
		public static final String NAM = "NAM";
		
		public static final String ADR = "ADR";
		
		public static final String TEL = "TEL";
		
		public static final String EMAIL = "EMAIL";
		
		public static final String CONS = "CONS";
		
		public static final String CRITERIA = "CRITERIA";
		
		public static final String OP = "OP";
		
		public static final String SW = "SW";
		
		public static final String TAR = "TAR";
		
		public static final String CHG = "CHG";
		
		public static final String PVA = "PVA";
		
		public static final String FLG = "FLG";
		
		public static final String RAT = "RAT";
		
		public static final String PCK = "PCK";
		
		public static final String MRK = "MRK";
		
		public static final String GDS = "GDS";
		
		public static final String PRODUCT = "PRODUCT";
		
		public static final String NBR = "NBR";
		
		public static final String WGT = "WGT";
		
		public static final String GRS = "GRS";
		
		public static final String SUP = "SUP";
		
		public static final String INV = "INV";
		
		public static final String AMT = "AMT";
		
		public static final String CUR = "CUR";
		
		public static final String EXP = "EXP";
		
		public static final String SIGN = "SIGN";
		
		public static final String DAT = "DAT";
		
		public static final String AUTH = "AUTH";
		
		public static final String DAU = "DAU";
		
		public static final String MOD = "MOD";
		
		public static final String CUO = "CUO";
		
		public static final String REG = "REG";
		
		public static final String DPA = "DPA";
		
		public static final String EXA = "EXA";
		
		public static final String DEST = "DEST";
		
		public static final String CTL = "CTL";
		
		public static final String RSLT = "RSLT";
		
		public static final String CORR = "CORR";
		
		public static final String NOTCORRECT = "NOTCORRECT";
		
		public static final String EXPIRED = "EXPIRED";
		
		public static final String SCAN 		= "SCAN";
		
		public static final String LST 			= "LST";

		public static final String FLP1 		= "FLP1";
		
		public static final String YER 		= "YER";
		
		public static final String SER 		= "SER";
		
		static public final String BEG = "BEG";

		static public final String END = "END";
		
		static public final String TMP = "TMP";
		
		static public final String DSCTMP = "DSCTMP";
		
		 static public final String PRE = "PRE"; // Precision
		
	    static public final String UM1 = "UM1"; // Unit of measurement code 1
	    
	    static public final String UM2 = "UM2"; // Unit of measurement code 2
	    
	    static public final String UM3 = "UM3"; // Unit of measurement code 3
	    
	    static public final String TMZ = "TMZ"; // Time zone
	    
	    static public final String CRN = "CRN"; // Certificate unique Reference Number
	    
	    static public final String SUB = "SUB"; // Submit
	    
	    static public final String CEDEAO = "CEDEAO"; // CEDEAO
	    
	    static public final String UEMOA = "UEMOA"; // UEMOA
	    
	    static public final String Warning_msg = "WMG";


		
		
		

		// GCF
		// Status
		
		public static final String ST_NULL = null;
		public static final String ST_CREATED = "Created";
		public static final String ST_SUBMITTED = "Submitted";
		public static final String ST_ACCEPTED = "Accepted";
		public static final String ST_REJECTED = "Rejected";
		public static final String ST_VALIDATED_CUSTOMS = "Validated Customs";
		public static final String ST_CO_SENT = "CO Sent";
		public static final String ST_CO_RECEIVED = "CO Received";
		public static final String ST_MODIFY_REJECT = "Modify Reject";

		// Operation name
		public static final String OP_CREATE = Operation.CREATE_OPERATION_NAME;
		public static final String OP_ACCEPT = "Accept";
		public static final String OP_REJECT = "Reject";
		public static final String OP_SUBMIT = "Submit";
		public static final String OP_SEND = "Send";
		public static final String OP_VALIDATE = "Validate";
		public static final String OP_RECEIVE = "Receive";
		public static final String OP_PRINT = "Print";
		public static final String OP_MODIFY_REJECT = "Modify Reject";
		
		public static final String OP_LOCK 	  = Operation.LOCK_OPERATION_NAME;
		public static final String OP_UNLOCK  = Operation.UNLOCK_OPERATION_NAME;
		public static final String OP_DESIGN  = Operation.DESIGN_OPERATION_NAME;
		public static final String OP_VIEW 	  = Operation.VIEW_OPERATION_NAME;
		

		// Operation class name
		public static final String OC_NEW = "New";
		public static final String OC_RETRIEVE = "Retrieve";
		public static final String OC_VALIDATE = "Validate";



		// Operation final action name
		public static final String OA_CREATE = "Create";

		// Operation identificator
		public static final int OI_DESIGN 	  =	Operation.DESIGN_OPERATION_ID;
		public static final int OI_VIEW 	  =	Operation.VIEW_OPERATION_ID;
		public static final int OI_CREATE     = Operation.INTERNAL_OPERATIONS_MAX +   1;
		public static final int OI_SUBMIT     = Operation.INTERNAL_OPERATIONS_MAX +   2;
		public static final int OI_ACCEPT     = Operation.INTERNAL_OPERATIONS_MAX +   3;
		public static final int OI_REJECT     = Operation.INTERNAL_OPERATIONS_MAX +   4;
		public static final int OI_SEND       = Operation.INTERNAL_OPERATIONS_MAX +   5;
		public static final int OI_VALIDATE     = Operation.INTERNAL_OPERATIONS_MAX + 6;
		public static final int OI_PRINT      = Operation.INTERNAL_OPERATIONS_MAX +   7;
		public static final int OI_MODIFY_REJECT      = Operation.INTERNAL_OPERATIONS_MAX +   8;
		public static final int OI_RECEIVE      = Operation.INTERNAL_OPERATIONS_MAX +   9;
		
		
		// Middle Event Identification Section

		public static final int UOM_TAB_LOAD = INTERNAL_EVENTS_MAX + 100;	// Event fired when table is loaded
		public static final int TAR_TAB_LOAD = INTERNAL_EVENTS_MAX + 101;	// Event fired when table is loaded
		public static final int PKG_TAB_LOAD = INTERNAL_EVENTS_MAX + 102;	// Event fired when table is loaded
		public static final int CTY_TAB_LOAD = INTERNAL_EVENTS_MAX + 103;	// Event fired when table is loaded
		public static final int CUO_TAB_LOAD = INTERNAL_EVENTS_MAX + 104;   // Event fired when table is loaded
		public static final int CUR_TAB_LOAD = INTERNAL_EVENTS_MAX + 105;   // Event fired when table is loaded
		public static final int ATD_TAB_LOAD = INTERNAL_EVENTS_MAX + 106;
		public static final int PROD_TAB_LOAD = INTERNAL_EVENTS_MAX + 109;

		public static final int REC_DAT 	 = INTERNAL_EVENTS_MAX + 107;
		
		public static final int REC_TMZ = INTERNAL_EVENTS_MAX + 8223; // Recovering from server part

		public static final int REC_DAT_TIM = INTERNAL_EVENTS_MAX + 8224; // Recovering date and time from server part
		
		public static final int CHK_TAR = INTERNAL_EVENTS_MAX + 108;
		
		public static final int GET_NEXT_SERIAL_NUMBER = INTERNAL_EVENTS_MAX + 2014;
		
		public static final int GET_NEXT_SUBMIT_NUMBER = INTERNAL_EVENTS_MAX + 2015;
		
		public static final int NOTIFY_CLIENT_PRINT_MANAGER = INTERNAL_EVENTS_MAX + 10070;
		
		
		// Regulation - National Reference Database Section

		public static final String CTY_TAB = "CTY_TAB"; // Country
		public static final String CTY_COD = "CTY_COD";
		public static final String CTY_DSC = "CTY_DSC";
		public static final String CTY_TAB_EVENT = "CTY_TAB_EVENT"; // Cty_tab_event

		public static final String PKG_TAB = "PKG_TAB"; // Package
		public static final String PKG_COD = "PKG_COD";
		public static final String PKG_DSC = "PKG_DSC";
		public static final String PKG_TAB_EVENT = "PKG_TAB_EVENT"; // Pkg_tab_event
		
		public static final String TAR_TAB = "TAR_TAB"; // Tariff
	    public static final String HS6_COD = "HS6_COD";
	    public static final String TAR_PR1 = "TAR_PR1";
	    public static final String TAR_PR2 = "TAR_PR2";
		public static final String TAR_TAB_EVENT = "TAR_TAB_EVENT"; // Tar_tab_event

		public static final String UOM_TAB = "UOM_TAB"; // Unit of measurement
		public static final String UOM_COD = "UOM_COD";
		public static final String UOM_DSC = "UOM_DSC";
		public static final String UOM_TAB_EVENT = "UOM_TAB_EVENT"; // Uom_tab_event
		
		public static final String CUO_TAB = "CUO_TAB"; // customs office
		public static final String CUO_COD = "CUO_COD";
		public static final String CUO_NAM = "CUO_NAM";
		public static final String CUO_TAB_EVENT = "CUO_TAB_EVENT"; // Office_tab_event
		
		public static final String CUR_TAB = "CUR_TAB"; // customs office
		public static final String CUR_COD = "CUR_COD";
		public static final String CUR_DSC = "CUO_DSC";
		public static final String CUR_TAB_EVENT = "CUR_TAB_EVENT"; // Office_tab_event
		
		// Attached documents
		public static final String ATD_TAB = "ATD_TAB";
		public static final String ATD_COD = "ATD_COD";
		public static final String ATD_DSC = "ATD_DSC";
		public static final String ATD_TAB_EVENT = "ATD_TAB_EVENT";
		
		// Producers table
		public static final String PROD_TAB = "PROD_TAB";
		public static final String PROD_COD = "PRODUCER_COD";
		public static final String PROD_NAM = "PRODUCER_NAM";
		public static final String PROD_TEL = "PRODUCER_TEL";
		public static final String PROD_ADR = "PRODUCER_ADR";
		public static final String PROD_EMAIL = "PRODUCER_EMAIL";
		public static final String PROD_TAB_EVENT = "PROD_TAB_EVENT";


		
		// Results fields
		static public final String RESULT_REGISTERED_SERIAL_NBR = "CO result registered serial number";

		static public final String RESULT_REGISTERED_SERIAL_CHAR = "CO result registered serial character";
		
		static public final String RESULT_REGISTERED_CRN = "CO result registered unique reference";
		
	    static public final String RESULT_SUBMIT_SERIAL_NBR = "CO result submitted serial number";
	    static public final String RESULT_SUBMIT_SERIAL_CHAR = "CO result submitted serial character";





}
