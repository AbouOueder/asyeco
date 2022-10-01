package un.asyeco.product.server;

import java.rmi.RemoteException;
import java.util.Enumeration;
import java.util.Vector;

import so.kernel.Global;
import so.kernel.core.AbstractOperation;
import so.kernel.core.DataSet;
import so.kernel.core.DocumentInfo;
import so.kernel.core.FindItemStructure;
import so.kernel.core.FindOperation;
import so.kernel.core.FinderInfo;
import so.kernel.core.GCFConstants;
import so.kernel.core.LockOperation;
import so.kernel.core.Operation;
import so.kernel.core.OperationClass;
import so.kernel.core.Operations;
import so.kernel.core.RuleInterface;
import so.kernel.core.UnlockOperation;
import so.kernel.core.UserId;
import so.kernel.core.events.EventConstants;
import so.kernel.core.events.ServerEvent;
import so.kernel.core.exceptions.ServerRuleException;
import so.kernel.core.interfaces.DocumentInterface;
import so.kernel.core.interfaces.KDocumentInterface;
import so.kernel.core.search.ResultItem;
import so.kernel.core.search.ResultPage;
import so.kernel.core.search.SearchCriteria;
import so.kernel.core.search.SearchCriterion;
import so.kernel.server.AccessOperationsContainer;
import so.kernel.server.ConnectionManager;
import so.kernel.server.DPP;
import so.kernel.server.GCFServerBinder;
import so.kernel.server.GCFServerEvent;
import so.kernel.server.OperationFactory;
import so.kernel.server.ServerBinder;
import so.kernel.server.UserTransactionEnvironment;
import so.kernel.server.scheduler.TaskEvent;
import so.kernel.server.search.AccessSearchable;
import so.provider.usr.server.S_Usr;
import so.util.DebugOutput;
import un.adtcommons.server.rules.HistorizedServerRuleFactory;
import un.asyeco.product.C_Product;



/**
 * Server side class that manages the GCF binding document/database
 */
public class S_Product extends GCFServerBinder implements C_Product {

	public static final String CO_PRODUCT_TAB = S_Product.class.getName()	+ "#CO_PRODUCT_TAB";
	public static final String CO_PRODUCT_ITM_TAB = S_Product.class.getName()	+ "#CO_PRODUCT_ITM_TAB";
//
	public static final String DB_URL_PROPERTY = S_Product.class.getName() + "#eCO_DataBaseURL";
	public static final String DB_USER_PROPERTY = S_Product.class.getName()	+ "#eCO_DataBaseUser";
	public static final String DB_PASSWD_PROPERTY = S_Product.class.getName() + "#eCO_DataBasePassword";

	/**
	 * Define the document server binder business logic
	 * 
	 */
	public void defineBinder() {
		
		// Table connectors
		addServerRule(new TC_Product(this, getConnectionManager(0)));
		
		// Country
	//	addServerRule(HistorizedServerRuleFactory.getServerRule(this, new int[] { CTY_TAB_LOAD }, CTY_TAB));
//		// Package
//		addServerRule(HistorizedServerRuleFactory.getServerRule(this, new int[] { PKG_TAB_LOAD }, PKG_TAB));
//		// Customs office
//		addServerRule(HistorizedServerRuleFactory.getServerRule(this, new int[] { CUO_TAB_LOAD }, CUO_TAB));
//		// Currency
//		addServerRule(HistorizedServerRuleFactory.getServerRule(this, new int[] { CUR_TAB_LOAD }, CUR_TAB));
//		// Unit of measurement
//		addServerRule(HistorizedServerRuleFactory.getServerRule(this, new int[] { UOM_TAB_LOAD }, UOM_TAB));		
//		// Unit of measurement
		addServerRule(HistorizedServerRuleFactory.getServerRule(this, new int[] { TAR_TAB_LOAD }, TAR_TAB));
//		// Attached documents
//		addServerRule(HistorizedServerRuleFactory.getServerRule(this, new int[] { ATD_TAB_LOAD }, ATD_TAB));
//		
		// Producers
		addServerRule(HistorizedServerRuleFactory.getServerRule(this, new int[] { PROD_TAB_LOAD }, PROD_TAB));
//
		addServerRule(new SR_ChkTar(this), CHK_TAR);
//		addServerRule(new SR_Register(this), GET_NEXT_SERIAL_NUMBER);
//		addServerRule(new SR_Submit(this), GET_NEXT_SUBMIT_NUMBER);
		
		setDPP(createDPP());

	}

	// Define the Document processing path

	protected DPP createDPP() {
		DPP dpp = new DPP();
		
		dpp.add(null, OP_DESIGN, null);

		dpp.add(null, OP_CREATE, ST_CREATED);		
//		dpp.add(ST_CREATED, OP_SUBMIT, ST_SUBMITTED);
//		dpp.add(ST_SUBMITTED, OP_LOCK, ST_SUBMITTED);
//		dpp.add(ST_SUBMITTED, OP_UNLOCK, ST_SUBMITTED);
//				
//		dpp.add(ST_SUBMITTED, OP_ACCEPT, ST_ACCEPTED);
//		dpp.add(ST_SUBMITTED, OP_REJECT, ST_REJECTED);
//		dpp.add(ST_ACCEPTED, OP_LOCK, ST_ACCEPTED);
//		dpp.add(ST_ACCEPTED, OP_UNLOCK, ST_ACCEPTED);
//
//		
//		dpp.add(ST_REJECTED, OP_MODIFY_REJECT, ST_MODIFY_REJECT);
//		dpp.add(ST_REJECTED, OP_LOCK, ST_REJECTED);
//		dpp.add(ST_REJECTED, OP_UNLOCK, ST_REJECTED);
//		
//		dpp.add(ST_MODIFY_REJECT, OP_SUBMIT, ST_SUBMITTED);
//		
//				
//		dpp.add(ST_ACCEPTED, OP_VALIDATE, ST_VALIDATED_CUSTOMS);
//		dpp.add(ST_VALIDATED_CUSTOMS, OP_LOCK, ST_VALIDATED_CUSTOMS);
//		dpp.add(ST_VALIDATED_CUSTOMS, OP_UNLOCK, ST_VALIDATED_CUSTOMS);		
//		dpp.add(ST_VALIDATED_CUSTOMS, OP_SEND, ST_CO_SENT);
//		
//		
//		dpp.add(null, OP_RECEIVE, ST_CO_RECEIVED);
//		
		dpp.add(ST_CREATED, OP_VIEW, ST_CREATED);
//		dpp.add(ST_SUBMITTED, OP_VIEW, ST_SUBMITTED);
//		dpp.add(ST_ACCEPTED, OP_VIEW, ST_ACCEPTED);
//		dpp.add(ST_REJECTED, OP_VIEW, ST_REJECTED);
//		dpp.add(ST_VALIDATED_CUSTOMS, OP_VIEW, ST_VALIDATED_CUSTOMS);
//		dpp.add(ST_CO_RECEIVED, OP_VIEW, ST_CO_RECEIVED);
		
		return dpp;
	}

	protected void addReferenceModelEvents(AbstractOperation abstractOperation) {
//		abstractOperation.addEventID(CUO_TAB_LOAD, CUO_TAB_EVENT);
//		abstractOperation.addEventID(CUR_TAB_LOAD, CUR_TAB_EVENT);
		//abstractOperation.addEventID(CTY_TAB_LOAD, CTY_TAB_EVENT);
//		abstractOperation.addEventID(UOM_TAB_LOAD, UOM_TAB_EVENT);
//		abstractOperation.addEventID(PKG_TAB_LOAD, PKG_TAB_EVENT);
		abstractOperation.addEventID(TAR_TAB_LOAD, TAR_TAB_EVENT);
//		abstractOperation.addEventID(ATD_TAB_LOAD, ATD_TAB_EVENT);
		abstractOperation.addEventID(PROD_TAB_LOAD, PROD_TAB_EVENT);
		
	}

	protected void addImportVisualEvents(AbstractOperation abstractOperation) {
	
	}
	protected void addExportVisualEvents(AbstractOperation abstractOperation) {
		
	}

	protected Operations createValidOperations() {
		setDefaultOperationFlag(FIND );
		Operations ops = super.createValidOperations();
		setupDefaultOperationFind(ops);
		// "Design" operation class
		OperationClass oc_Design = OperationFactory.makeDesignerOperation();
		addReferenceModelEvents(oc_Design);		
		addMiddleEvents(oc_Design);
		oc_Design.addEventID(CHK_TAR);
		ops.add(oc_Design);

		// View
		Operation op_View = OperationFactory.makeViewOperation(OI_VIEW, OP_VIEW,lng("View"),"img/View_Operation.gif");
		//op_View.addVisibleEventID(DO_PRINT_BAG, "Print",lng("Print decision"),	"img/Btn_Print_Normal.gif");
		addReferenceModelEvents(op_View);
		addMiddleEvents(op_View);
		addExportVisualEvents(op_View);
		ops.add(op_View);

		
		// "Lock" default operation
		Operation op_Lock = new LockOperation();
		op_Lock.setInLibrary(false);
		addReferenceModelEvents(op_Lock);
		ops.add(op_Lock);

		// "Unlock" default operation
		Operation op_Unlock = new UnlockOperation();
		op_Unlock.setInLibrary(false);
		addReferenceModelEvents(op_Unlock);
		ops.add(op_Unlock);
		
		
		// "New" operation class - main application menu
		OperationClass oc_New = new OperationClass(OC_NEW, "img/Btn_New_Normal.gif");
		oc_New.setKnownIED(false);
		oc_New.setInLibrary(true);
		addImportVisualEvents(oc_New);
		addExportVisualEvents(oc_New);
		addReferenceModelEvents(oc_New);
		addMiddleEvents(oc_New);
		oc_New.addVisibleEventID(MEN_ITM_NEW, ITM_NEW, lng(ITM_NEW), "img/Btn_New_Normal.gif");
		oc_New.addVisibleEventID(MEN_ITM_DEL, ITM_DEL, lng(ITM_DEL), "img/Btn_Delete_Normal.gif");
		
		oc_New.addEventID(CHK_TAR);

		// Register
		int oe_create_Before_Events[] = { };
		Operation op_Create = OperationFactory.makeCreateOperation(OI_CREATE,	
				OP_CREATE, lng("Create"), "img/Btn_Save_Normal.gif",oe_create_Before_Events,null);
		addReferenceModelEvents(op_Create);
		oc_New.add(op_Create);
		ops.add(oc_New);
		
		
		// Submit
//		Operation op_submit = OperationFactory.makeUpdateOperation(OI_SUBMIT, OP_SUBMIT,
//				 lng("Submit"), "img/Btn_OK_Green_Normal.gif",  new int[] { GET_NEXT_SUBMIT_NUMBER }, null);
//		op_submit.setRequireLock(true);			
//		op_submit.setKnownIED(true);
//		op_submit.setInLibrary(false);
//		addMiddleEvents(op_submit);		
//		addReferenceModelEvents(op_submit);	
//		op_submit.addEventID(CHK_TAR);
//		ops.add(op_submit);
//			
//		// Accept
//		Operation op_Accept = OperationFactory.makeUpdateOperation(OI_ACCEPT, OP_ACCEPT,
//				 lng("Accept"), "img/Btn_OK_Normal.gif", new int[] { GET_NEXT_SERIAL_NUMBER }, null);	
//		op_Accept.setRequireLock(true);	
//		op_Accept.setKnownIED(true);
//		op_Accept.setInLibrary(false);
//		addMiddleEvents(op_Accept);		
//		addReferenceModelEvents(op_Accept);	
//		op_Accept.addEventID(CHK_TAR);
//		ops.add(op_Accept);			
//			
//		// Reject
//		Operation op_Reject = OperationFactory.makeUpdateOperation(OI_REJECT, OP_REJECT,
//				lng("Reject"), "img/Btn_Stop_Process_Normal.gif", null, null);
//		op_Accept.setRequireLock(true);	
//		op_Reject.setKnownIED(true);
//		op_Reject.setInLibrary(false);
//		addMiddleEvents(op_Reject);		
//		addReferenceModelEvents(op_Reject);		
//		op_Reject.addEventID(CHK_TAR);
//		ops.add(op_Reject);		
//		
//		// Modify Reject
//		Operation op_ModifyReject = OperationFactory.makeUpdateOperation(OI_MODIFY_REJECT, OP_MODIFY_REJECT,
//				lng("Modify Reject"), "img/Btn_Open_Normal.gif", null, null);	
//		op_ModifyReject.setRequireLock(true);	
//		op_ModifyReject.setKnownIED(true);
//		op_ModifyReject.setInLibrary(false);
//		addMiddleEvents(op_ModifyReject);		
//		addReferenceModelEvents(op_ModifyReject);	
//		op_ModifyReject.addEventID(CHK_TAR);
//		ops.add(op_ModifyReject);	
//		
//		
//		// Customs Validate
//		Operation op_Validate = OperationFactory.makeUpdateOperation(OI_VALIDATE, OP_VALIDATE,
//				lng("Customs Validate"), "img/Btn_OK_Normal.gif", null, null);		
//		op_Validate.setRequireLock(true);
//		op_Validate.setKnownIED(true);
//		op_Validate.setInLibrary(false);
//		addMiddleEvents(op_Validate);		
//		addReferenceModelEvents(op_Validate);	
//		op_Validate.addEventID(CHK_TAR);
//		ops.add(op_Validate);	
//		
//		// Send CO
//		Operation op_Send = OperationFactory.makeUpdateOperation(OI_SEND, OP_SEND,
//				lng("Send CO"), "img/Btn_Back_Rollover_Blue.gif", null, null);
//		op_Send.setRequireLock(true);
//		op_Send.setKnownIED(true);
//		op_Send.setInLibrary(false);
//		op_Send.setAutoFinish(true);
//		addMiddleEvents(op_Send);		
//		addReferenceModelEvents(op_Send);			
//		ops.add(op_Send);	
//		
//		// Receive CO
//		Operation op_Receive = OperationFactory.makeCreateOperation(OI_RECEIVE, OP_RECEIVE,
//				lng("Receive CO"), "img/Btn_OK_Normal.gif", null, null);	
//		op_Receive.setKnownIED(false);
//		op_Receive.setInLibrary(false);
//		op_Receive.setAutoFinish(true);
//		addMiddleEvents(op_Receive);		
//		addReferenceModelEvents(op_Receive);			
//		ops.add(op_Receive);	
//			
			
		return ops;
		
		
	}



	/**
 	 * Add the middle events to the given operation 
 	 * <!-- begin-user-doc -->
 	 * <!-- end-user-doc -->
 	 * @generated
 	 */
	public void addMiddleEvents(AbstractOperation abstractOperation) {
		abstractOperation.addEventID(REC_DAT);		
	}
	
	/**
 	 * Sets attributes for the Operation Find Default Operation  
 	 * <!-- begin-user-doc -->
 	 * <!-- end-user-doc -->
 	 * @generated
 	 */
	private void setupDefaultOperationFind(Operations operations) {
		Operation operation = operations.getOperation(Operation.FIND_OPERATION_ID);
				
		addReferenceModelEvents(operation);	
	}	

	public void initializeDatabase() {
		ConnectionManager manager = createConnectionManager(DB_URL_PROPERTY,
				DB_USER_PROPERTY, DB_PASSWD_PROPERTY);
		if (manager == null) {
			System.err.println(lng("Certificate of origin database not found"));
		}
	}

	private static so.i18n.IntlObj lng(String key) {
		return new so.i18n.IntlObj("un.asyeco", key);
	}


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
