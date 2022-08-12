//
//   $Header: /home/asycuda/home/cvsroot/asybrk/Server/src/un/broker/serial/ast/server/S_Ast.java,v 1.11 2012-07-03 07:36:03 kazan Exp $

package un.asyeco.serial.ast.server;

import java.util.Enumeration;

import so.kernel.core.AbstractOperation;
import so.kernel.core.LockOperation;
import so.kernel.core.Operation;
import so.kernel.core.OperationClass;
import so.kernel.core.Operations;
import so.kernel.core.RuleInterface;
import so.kernel.core.UnlockOperation;
import so.kernel.core.events.EventConstants;
import so.kernel.core.events.ServerEvent;
import so.kernel.core.exceptions.ServerRuleException;
import so.kernel.server.ConnectionManager;
import so.kernel.server.DPP;
import so.kernel.server.GCFServerBinder;
import so.kernel.server.GCFServerEvent;
import so.kernel.server.OperationFactory;
import so.kernel.server.UserTransactionEnvironment;
import so.util.DebugOutput;
import un.adtcommons.server.rules.HistorizedServerRuleFactory;
import un.asyeco.eco.C_eCO;
import un.asyeco.eco.server.S_eCO;
import un.asyeco.serial.ast.C_Ast;
import un.asyeco.serverUtils.SR_SendServerDate;
import un.asyeco.serverUtils.SR_SendTimeZone;


/**
 * Server side class that manages the binding document/database
 */
public class S_Ast extends GCFServerBinder implements C_Ast {

	/**
	 * Name of the resource table identifier for Tariff section table
	 */
	public static final String SER_CO_SUB_TAB = S_eCO.class.getName() + "#SER_CO_SUB_TAB";

	/**
	 * Name of the database identifier for the database manager class. The
	 * actual name will be loaded from the resource file.
	 */
	//public static final String MANAGER_PROPERTY = S_eCO.class.getName() + "#SAD_DriverManager";

	/**
	 * Name of the identifier for the database link URL. The actual name will be
	 * loaded from the resource file.
	 */
	public static final String DB_URL_PROPERTY = S_eCO.class.getName() + "#eCO_DataBaseURL";

	/**
	 * Name of the database identifier for the user name. The actual name will
	 * be loaded from the resource file.
	 */
	public static final String DB_USER_PROPERTY = S_eCO.class.getName() + "#eCO_DataBaseUser";

	/**
	 * Name of the database identifier for the user password. The actual name
	 * will be loaded from the resource file.
	 */
	public static final String DB_PASSWD_PROPERTY = S_eCO.class.getName() + "#eCO_DataBasePassword";

	public void defineBinder() {

		addServerRule(new SR_ChkKeys(this), CHK_KEYS);

		// Regulation - National reference rules

		// Customs sites, offices
		// addServerRule(new ReferenceServerRule(this, new int[] {CUO_LOAD},
		// CUO_TAB));
		addServerRule(HistorizedServerRuleFactory.getServerRule(this, new int[] { CUO_LOAD }, CUO_TAB));
		// addServerRule(HistorizedServerRuleFactory.getServerRule(this, new int[] {CUO_LOAD_ar},
		// CUO_TAB_ar));
		addServerRule(HistorizedServerRuleFactory.getServerRule(this, new int[] { CTY_TAB_LOAD }, CTY_TAB));
		addServerRule(new SR_SendServerDate(this), C_eCO.REC_DAT);
		addServerRule(new SR_SendTimeZone(this), C_eCO.REC_TMZ);
		// addServerRule(new SR_SaveHistory(this),
		// EventConstants.UPDATE_REQUESTED);
		// Serial, declarant table connector
		TC_Ast tc_Ast = new TC_Ast(this, getConnectionManager(0));
		addServerRule(tc_Ast);

		// Define the Document processing path
		setDPP(createDPP());

		// Set status access constraint for additional Finders
		addAccessedStatusByOtherFinder(ST_REGISTERED);
	}

	// Define the Document processing path

	protected DPP createDPP() {

		DPP dpp = new DPP();

		dpp.add(null, OP_DESIGN, null);

		dpp.add(null, OP_CREATE, ST_REGISTERED);

		dpp.add(ST_REGISTERED, OP_AMEND, ST_REGISTERED);
		dpp.add(ST_REGISTERED, OP_DELETE, ST_DELETED);
		dpp.add(ST_REGISTERED, OP_VIEW, ST_REGISTERED);

		dpp.add(ST_REGISTERED, OP_LOCK, ST_REGISTERED);
		dpp.add(ST_REGISTERED, OP_UNLOCK, ST_REGISTERED);

		dpp.add(ST_DELETED, OP_VIEW, ST_DELETED);

		return dpp;
	}

	protected boolean hasChangedVersion(UserTransactionEnvironment environment) {

		Operation op = environment.getEndOperation();

		if (op != null) {
			String operation = op.getName();

			if (OP_AMEND.equals(operation))
				return false;
		}

		return true;
	}

	protected void addMundaneEvents(AbstractOperation abstractOperation) {

		// abstractOperation.addEventID(CUO_LOAD, CUO_TAB);
		abstractOperation.addEventID(CUO_LOAD, CUO_TAB_EVENT);
		abstractOperation.addEventID(CTY_TAB_LOAD, CTY_TAB_EVENT);
		// abstractOperation.addEventID(CUO_LOAD_ar, CUO_TAB_EVENT_ar);
		abstractOperation.addEventID(C_eCO.REC_DAT);
		abstractOperation.addEventID(C_eCO.REC_TMZ);

	}

	protected Operations createValidOperations() {

		// Set the default operation(s)
		setDefaultOperationFlag(VIEW | FIND);

		Operations ops = super.createValidOperations();

		// "Design" operation class
		OperationClass oc_Design = OperationFactory.makeDesignerOperation();
		addMundaneEvents(oc_Design);

		ops.add(oc_Design);

		// "New" operation class
		OperationClass oc_New = new OperationClass(OC_NEW, "img/Btn_New_Normal.gif");
		oc_New.setKnownIED(false);
		oc_New.setInLibrary(true);
		oc_New.addEventID(CHK_KEYS);
		addMundaneEvents(oc_New);

		oc_New.addVisibleEventID(DO_PRINT, "Print", "img/Btn_Print_Normal.gif");

        Operation createOp = OperationFactory.makeCreateOperation(
            OE_CREATE, OP_CREATE, lng(OA_VALIDATE), "img/Btn_Yes_Normal.gif", null, null);
        oc_New.add(createOp);

		ops.add(oc_New);

		// "Amend" operation class
		OperationClass oc_Amend = new OperationClass(OC_AMEND, "img/Btn_Open_Normal.gif");
		oc_Amend.setRequireLock(true);
		oc_Amend.setKnownIED(true);
		oc_Amend.setInLibrary(false);
		oc_Amend.addEventID(EventConstants.UPDATE_REQUESTED);
		oc_Amend.setStartEvent(EventConstants.UPDATE_REQUESTED);

		addMundaneEvents(oc_Amend);

		oc_Amend.addVisibleEventID(DO_PRINT, "Print", "img/Btn_Print_Normal.gif");

		Operation op_Amend = OperationFactory.makeUpdateOperation(OE_AMEND, OP_AMEND, lng(OA_VALIDATE), "img/Btn_Yes_Normal.gif");
		oc_Amend.add(op_Amend);

		ops.add(oc_Amend);

		// "Delete" operation class
		OperationClass oc_Delete = new OperationClass(OC_DELETE, "img/Btn_Delete_Normal.gif");
		oc_Delete.setRequireLock(true);
		oc_Delete.setKnownIED(true);
		oc_Delete.setInLibrary(false);
		oc_Delete.setAutoFinish(true);

		addMundaneEvents(oc_Delete);

		oc_Delete.add(OperationFactory.makeStatusUpdateOperation(OE_DELETE, OP_DELETE));

		ops.add(oc_Delete);

		// "View" default operation

		Operation op_View = ops.getOperation(Operation.VIEW_OPERATION_ID);
		op_View.setDefault(1);

		addMundaneEvents(op_View);

		// "Lock" default operation

		Operation op_Lock = new LockOperation();
		op_Lock.setInLibrary(false);

		addMundaneEvents(op_Lock);

		ops.add(op_Lock);

		// "Unlock" default operation

		Operation op_Unlock = new UnlockOperation();
		op_Unlock.setInLibrary(false);

		addMundaneEvents(op_Unlock);

		ops.add(op_Unlock);

		// AWW - ULA
        Operation[] opers = {op_View, createOp, op_Amend, op_Lock, op_Unlock};
        for(int i = 0; i < opers.length; i++)
        {
            opers[i].setEnableULA(true);
        }
		return ops;
	}

	public void initializeDatabase() {
		ConnectionManager manager = createConnectionManager(DB_URL_PROPERTY, DB_USER_PROPERTY, DB_PASSWD_PROPERTY);

		if (manager == null) {
			System.err.println("Customs database not found");
		}

	}

	private static so.i18n.IntlObj lng(String key) {
		return new so.i18n.IntlObj("un.asyeco", key);
	}
	
    //AUDIT
    protected ServerEvent applyEvent(ServerEvent event)
    throws ServerRuleException
    {
    	if (audit ){
    		String operationStartedName = null;
    		String operationEndName = null;
    		if (event instanceof GCFServerEvent){
    			UserTransactionEnvironment env = ((GCFServerEvent)event).getUserTransactionEnvironment();
    			if (env !=null){
    				operationStartedName = env.getStartedOperation()!=null?env.getStartedOperation().getName():"";
    				operationEndName = env.getEndOperation()!=null?env.getEndOperation().getName():"";
    				
    			}
    		}
    		Enumeration rules;
    		DebugOutput.print("Operation started : " + operationStartedName + " / " + operationEndName + "  Started Event id : " + event.getID() + "  " + event.getEventName(event));
    		for(rules = serverRulesContainer.getApplyRules(event); rules.hasMoreElements(); ){
    			RuleInterface rule = (RuleInterface)rules.nextElement();
    			String className = 	rule.getClass().getName();
    			if (!className.startsWith("so")|| className.equals("so.kernel.server.DataVerifierServerRule")){
    				DebugOutput.print("Operation started : " + operationStartedName + " / " + operationEndName + "        Event id : " + event.getID() + "      Rule name : " +className );
    			}
    		}
    		 
    	}	
    	ServerEvent serverEvent =  super.applyEvent(event);
    	if (audit ){
    		DebugOutput.print("End Event id : " + event.getID() + "   " + event.getEventName(event));
    		DebugOutput.print("*************************************************************************" );
    		DebugOutput.print("                                                                         " );
    		DebugOutput.print("                                                                         " );
    	}
    	return serverEvent;
    }
    
    private static int logLevel = DebugOutput.parseLevel(System.getProperty("un.server.logLevel") );
	private boolean audit = logLevel>=20?true:false;
    
	//END AUDIT

}
