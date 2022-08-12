//   $Header: /home/asycuda/home/cvsroot/nattrs/Server/src/un/nattrs/serial/t1/reg/server/S_Reg.java,v 1.21 2012-07-03 15:21:43 kazan Exp $

package un.asyeco.serial.eco.reg.server;

import so.kernel.core.AbstractOperation;
import so.kernel.core.LockOperation;
import so.kernel.core.Operation;
import so.kernel.core.OperationClass;
import so.kernel.core.Operations;
import so.kernel.core.UnlockOperation;
import so.kernel.core.events.EventConstants;
import so.kernel.server.ConnectionManager;
import so.kernel.server.DPP;
import so.kernel.server.GCFServerBinder;
import so.kernel.server.OperationFactory;
import so.kernel.server.UserTransactionEnvironment;
import un.adtcommons.server.rules.HistorizedServerRuleFactory;
import un.asyeco.eco.C_eCO;
import un.asyeco.eco.server.S_eCO;
import un.asyeco.serial.eco.reg.C_Reg;
import un.asyeco.serverUtils.SR_SendServerDate;
import un.asyeco.serverUtils.SR_SendTimeZone;


public class S_Reg extends GCFServerBinder implements C_Reg  {

	/**
	 * Name of the resource table identifier for Tariff section table
	 */
	public static final String SER_CO_REG_TAB = S_eCO.class.getName() + "#SER_CO_REG_TAB";

	/**
	 * Name of the database identifier for the database manager class. The
	 * actual name will be loaded from the resource file.
	 */
	//public static final String MANAGER_PROPERTY = S_eCO.class.getName() + "#T1_DriverManager";

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
		// CTY_TAB));
		addServerRule(HistorizedServerRuleFactory.getServerRule(this, new int[] { CTY_TAB_LOAD }, CTY_TAB));
		// addServerRule(HistorizedServerRuleFactory.getServerRule(this, new int[] {CUO_LOAD_ar},
		// CUO_TAB_ar));
		addServerRule(new SR_SendServerDate(this), C_eCO.REC_DAT);
		addServerRule(new SR_SendTimeZone(this), C_eCO.REC_TMZ);
		// addServerRule(new SR_SaveHistory(this), OI_AMEND);
		// Serial, declarant table connector
		TC_Reg tc_Reg = new TC_Reg(this, getConnectionManager(0));
		addServerRule(tc_Reg);

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

		// --20090818--not needed-- dpp.add(ST_DELETED, OP_VIEW, ST_DELETED);

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
		oc_Design.addEventID(CHK_KEYS);
		addMundaneEvents(oc_Design);

		ops.add(oc_Design);

		// "New" operation class
		OperationClass oc_New = new OperationClass(OC_NEW, "img/Btn_New_Normal.gif");
		oc_New.setKnownIED(false);
		oc_New.setInLibrary(true);
		oc_New.addEventID(CHK_KEYS);
		addMundaneEvents(oc_New);

		oc_New.addVisibleEventID(DO_PRINT, "Print", "img/Btn_Print_Normal.gif");

		Operation op_create = OperationFactory.makeCreateOperation(OI_CREATE, OP_CREATE, lng(OA_VALIDATE), "img/Btn_Yes_Normal.gif", null, null);
		// createOp.setEnableULA(true);
		oc_New.add(op_create);

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

		Operation op_Amend = OperationFactory.makeUpdateOperation(OI_AMEND, OP_AMEND, lng(OA_VALIDATE), "img/Btn_Yes_Normal.gif");
		// op_Amend.setEnableULA(true);
		oc_Amend.add(op_Amend);

		ops.add(oc_Amend);

		// "Delete" operation class
		OperationClass oc_Delete = new OperationClass(OC_DELETE, "img/Btn_Delete_Normal.gif");
		oc_Delete.setRequireLock(true);
		oc_Delete.setKnownIED(true);
		oc_Delete.setInLibrary(false);
		oc_Delete.setAutoFinish(true);

		addMundaneEvents(oc_Delete);

		Operation op_delete = OperationFactory.makeStatusUpdateOperation(OI_DELETE, OP_DELETE);
		oc_Delete.add(op_delete);

		ops.add(oc_Delete);

		// "View" default operation

		Operation op_View = ops.getOperation(Operation.VIEW_OPERATION_ID);
		op_View.setDefault(1);
		// op_View.setEnableULA(true);

		addMundaneEvents(op_View);

		// ULA Find operation always needs access to the reference tables.
		Operation op_Find = ops.getOperation(Operation.FIND_OPERATION_ID);
		addMundaneEvents(op_Find);

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
		Operation[] opers = {op_View, op_create, op_Amend, op_delete, op_Lock, op_Unlock};
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

	/*
	 * protected void commitTransaction(UserTransactionEnvironment environment)
	 * throws RemoteException { Class clazz = GCFUtillities.class; Class clazz2 =
	 * GCFServerBinder.class; AbstractOperation ao =
	 * environment.getStartedOperation(); Operation op =
	 * environment.getEndOperation(); if(op == null) {
	 * System.err.println("Problem in GCFServerBinder.commitTransactionGCF in" +
	 * getBinderName() + ": " +  + " " + ao + " " +
	 * ao.getClass() + ", " + ); return; }
	 * if(!ao.isKnownIED() && !op.getCommitEvents().hasMoreElements()) {
	 * if(!op.isNil()) System.err.println("GCFServerBinder.commitTransactionGCF:
	 * Warning #1 - Possible problem! :" + getBinderName() + ":" + ao + ": " +
	 *  + op + " " +  + op.getID() + " " + "the start
	 * operation is not knownIED and there are not commit events defined!");
	 * return; } try { Field baseTC = clazz2.getDeclaredField("baseTC");
	 * baseTC.setAccessible(true); environment.updateGCFCommit(this,
	 * (GCFTableConnector)baseTC.get(this)); } catch(NoSuchFieldException _) {
	 * System.out.println(_); } catch(IllegalAccessException _) {
	 * System.out.println(_); }
	 * 
	 * Number nInstance = environment.getDocumentInstanceId(); Number nIED =
	 * environment.getIED(); if(nInstance == null && nIED == null) {
	 * System.err.println("!!! GCFServerBinder.commitTransactionGCF: Table
	 * connector possibly not added properly!"); throw new
	 * RemoteException("GCFServerBinder.commitTransactionGCF: Cannot get
	 * instanceID for: " + getClass()); } int instanceId = 0; int ied = 0;
	 * boolean b = nInstance == null || nIED == null; if(!b) { instanceId =
	 * nInstance.intValue(); ied = nIED.intValue(); b =
	 * !GCFUtillities.isCorrectPairDocIds(environment, instanceId, ied); } if(b)
	 * throw new RemoteException("GCFServerBinder.commitTransactionGCF: Error");
	 * String status = environment.getStatus(); String owner =
	 * environment.getOwner(); Number number = environment.getVersion(); int
	 * version = 0; if(number != null) { version = number.intValue(); version =
	 * version >= 0 ? version : 0; } if(ied != 0 && version != 0) { String mName =
	 * DocumentModulesManager.getModuleName(getRootName()); try { Method
	 * enterUnlockOperation = clazz.getDeclaredMethod("enterUnlockOperation",
	 * new Class[] {String.class, int.class});
	 * enterUnlockOperation.setAccessible(true);
	 * enterUnlockOperation.invoke(null, new Object[] {mName, new
	 * Integer(ied)}); } catch(NoSuchMethodException _) { System.out.println(_); }
	 * catch(IllegalAccessException _) { System.out.println(_); }
	 * catch(InvocationTargetException _) { System.out.println(_); }
	 * 
	 * try { int max = GCFUtillities.getMaxDocVersion(mName, ied); if(max !=
	 * version) throw new RemoteException("The document has been modified from
	 * another user."); } catch(SQLException _) { _.printStackTrace(); throw new
	 * RemoteException("SQLException ", _); } } boolean closedPreviousVersion =
	 * false; DataSet document = loadDocumentFromTransaction(environment);
	 * if(version == 0 || hasChangedVersion(environment)) {
	 * closedPreviousVersion = version > 0; version++; try { Method saveHistory =
	 * clazz.getDeclaredMethod("saveHistory", new Class[]
	 * {UserTransactionEnvironment.class, int.class, int.class, DataSet.class});
	 * saveHistory.setAccessible(true); if (!op.getName().equals(OP_AMEND)) {
	 * saveHistory.invoke(null, new Object[] {environment, new Integer(ied), new
	 * Integer(version), document}); } } catch(NoSuchMethodException _) {
	 * System.out.println(_); } catch(IllegalAccessException _) {
	 * System.out.println(_); } catch(InvocationTargetException _) {
	 * System.out.println(_); }
	 *  } environment.getDocumentEnvironment().setNewVersion(version); String
	 * newStatus = getNewStatus(status, op, environment); String sts[] =
	 * getDPPItems(status, op); if(sts == null || sts.length == 0) {
	 * System.err.println("Error in GCFServerBinder.commitTransactionGCF for" +
	 * getBinderName() + ": " +  + " " + op + " " +
	 *  + " " + status + " " + ); throw
	 * new RemoteException("GCFServerBinder.commitTransactionGCF: Operation not
	 * allowed in DPP"); } boolean setStatusOK = false; if(newStatus != null) {
	 * for(int i = 0; !setStatusOK && i < sts.length; i++) setStatusOK =
	 * sts[i].equals(newStatus);
	 *  } else { for(int i = 0; !setStatusOK && i < sts.length; i++) setStatusOK =
	 * sts[i] == null;
	 *  } if(!setStatusOK) { System.err.println("Error in
	 * GCFServerBinder.commitTransactionGCF for" + getBinderName() + ": " +
	 *  + " " + op + " " +  + " " +
	 * status + " " + ); throw new
	 * RemoteException("GCFServerBinder.commitTransactionGCF: Invalid status"); }
	 * environment.getDocumentEnvironment().setNewStatus(newStatus); String
	 * newOwner = getNewOwner(environment); if(newOwner == null) { String
	 * usrOwner = environment.getUserOwner(); newOwner = usrOwner; }
	 * TransactionRecord item =
	 * environment.getTransactionEnvironment().getInformation(); long when =
	 * item.getStartTime().getTime();
	 * 
	 * try { Method saveVersion = clazz.getDeclaredMethod("saveVersion", new
	 * Class[] {ServerBinder.class, UserTransactionEnvironment.class, int.class,
	 * int.class, Operation.class, Object.class, Object.class, long.class,
	 * boolean.class}); saveVersion.setAccessible(true); if
	 * (!op.getName().equals(OP_AMEND)) { saveVersion.invoke(null, new Object[]
	 * {this, environment, new Integer(ied), new Integer(version), op,
	 * newStatus, newOwner, new Long(when), new
	 * Boolean(closedPreviousVersion)}); } } catch(NoSuchMethodException _) {
	 * System.out.println(_); } catch(IllegalAccessException _) {
	 * System.out.println(_); } catch(InvocationTargetException _) {
	 * System.out.println(_); }
	 * 
	 * if(ao.isRequireLock()) GCFUtillities.unlockDocument(getRootName(),
	 * environment.getTransactionEnvironment().getTransaction(), instanceId,
	 * ied, environment.getUserEnvironment()); if(ao instanceof UnlockOperation) {
	 * if(ied > 0) { String mName =
	 * DocumentModulesManager.getModuleName(getRootName()); int uid =
	 * GCFUtillities.loadLockedEndUser(mName, ied); int tid =
	 * GCFUtillities.loadLockedTransactionId(mName, ied); if(uid > 0 && tid > 0)
	 * UserBroker.zapTransaction(uid, tid); else System.err.println(">>> " +
	 * ); }
	 * GCFUtillities.unlockDocument(getRootName(), instanceId, ied); } if(ao
	 * instanceof LockOperation) GCFUtillities.lockDocument(getRootName(),
	 * instanceId, ied,
	 * environment.getTransactionEnvironment().getTransaction().getId(),
	 * environment.getUserEnvironment()); }
	 */

	private static so.i18n.IntlObj lng(String key) {
		return new so.i18n.IntlObj("un.asyeco", key);
	}
}
