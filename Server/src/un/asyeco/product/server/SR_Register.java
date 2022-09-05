package un.asyeco.product.server;

import java.rmi.RemoteException;
import java.text.DateFormat;
import java.text.DecimalFormat;
import java.text.NumberFormat;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.GregorianCalendar;
import java.util.StringTokenizer;

import so.kernel.core.DataField;
import so.kernel.core.DataSet;
import so.kernel.core.Document;
import so.kernel.core.KernelEvent;
import so.kernel.core.interfaces.DocumentInterface;
import so.kernel.server.GCFServerEvent;
import so.kernel.server.OperationRunner;
import so.kernel.server.ServerBinder;
import so.kernel.server.ServerRule;
import so.kernel.server.UserTransactionEnvironment;
import so.provider.usr.server.S_Usr;
import so.util.DebugOutput;
import so.util.calendar.DateValue;
import un.asyeco.eco.C_eCO;
import un.asyeco.serial.ast.C_Ast;
import un.asyeco.serial.eco.reg.C_Reg;
import un.kernel.util.AWServerTranslator;

public class SR_Register extends ServerRule implements C_eCO {

	/** Creates new SR_Register */
	public SR_Register(ServerBinder binder) {
		super(binder);
	}

	protected void apply(KernelEvent e) {

		if (e instanceof GCFServerEvent) {
			// Get GCF document
			boolean authorized = false;
			boolean doSerial = false;
			DataField nbr = null;
			GCFServerEvent event = (GCFServerEvent) e;
			UserTransactionEnvironment environment = event.getUserTransactionEnvironment();
			DataSet doc = environment.getFinalDocument().ds("normal");
			// ETH - lng ServerTranslator 09.2009
			AWServerTranslator.setLocale(event);
			// fin lng
			String ser_let = "";

			// Need to check that customs office of departure matches one of the
			// user authorized offices
			/*
			 * String usrAuthorizedCuos = environment.getProperty("office"); if
			 * (usrAuthorizedCuos != null) { StringTokenizer st = new
			 * StringTokenizer(usrAuthorizedCuos, ","); while (st.hasMoreTokens()) { String
			 * usrAuthorizedCuo = st.nextToken().trim(); // System.out.println("User is
			 * authorized for customs // office: " + st.nextToken().trim()); if
			 * (usrAuthorizedCuo.equalsIgnoreCase(doc.ds(IDE).ds(CUO).ds(DPA).de(COD).
			 * getString("").trim())) { authorized = true; } } if (!authorized) {
			 * setError(doc, lng(AUTHORIZATION_ERROR_MESSAGE_DPA) +
			 * doc.ds(IDE).ds(CUO).ds(DPA).de(COD).getString("").trim(), event);
			 * AWServerTranslator.leaveLocale(); return; } }
			 */

			Calendar cal = Calendar.getInstance();
			Date current = (Date) doc.ds(CO).ds(REG).de(DAT).getContent();
			if (current == null) {
				DateFormat format = new SimpleDateFormat("dd/MM/yyyy HH:mm");
				try {
					current = format.parse(format.format(new DateValue(new Date().getTime())));
				} catch (Exception exc) {
				}

				doc.ds(CO).ds(REG).de(DAT).tryToSetContent(current);
			}
			int year = cal.get(Calendar.YEAR);
			//Object cuo_cod = doc.ds(IDE).ds(CUO).ds(DPA).de(COD).getContent();
			Object cty_cod = doc.ds(CO).ds(CTY).de(COD).getContent();
			DataSet criteria = new DataSet(DocumentInterface.NORMAL_ID);
			criteria.add(C_Reg.YER).tryToSetContent(year);
			criteria.seg(C_Reg.CTY).add(C_Reg.COD).tryToSetContent(cty_cod);
			  //String serLet = getModTabSerial(doc);
	        String serLet = "C";
	        DebugOutput.print("SerialChar:" + serLet);
	        criteria.add(C_Reg.CHR).tryToSetContent(serLet);
	        
			if (doc.ds(CO).ds(REG).de(YER).getContent() == null){
			doc.ds(CO).ds(REG).de(YER).tryToSetContent(year);
			}

			boolean isOK = amendSerial(doc, criteria, event, year );
			if (!isOK) {
				createSerial(doc, year, event);
				isOK = amendSerial(doc, criteria, event, year);
				if (!isOK) {
					setError(event.getData(), lng("Problem while generating the serial number"), event);
				}
			}

			// if the document was registered without problems
			/*
			 * if (isOK) { boolean isDeclarantPerformingTransaction =
			 * "1".equals(S_Usr.getUserProperty(event.userId, "declarant")); if
			 * (isDeclarantPerformingTransaction) // if the declarant is performing the
			 * operation { AWServerTranslator.leaveLocale(); return; // this should be
			 * already taken care of }
			 * 
			 * String oldOwner =
			 * event.getUserTransactionEnvironment().getDocumentEnvironment().getOwner(); if
			 * (oldOwner != null && !oldOwner.trim().equals("")) // if owner already set,
			 * say for ex if declarant himself { AWServerTranslator.leaveLocale(); return;
			 * // created and lodged the T1 before } String newOwner =
			 * doc.ds(DEC).de(COD).getContentString();
			 * event.getUserTransactionEnvironment().getDocumentEnvironment().setNewOwner(
			 * newOwner); }
			 */
			AWServerTranslator.leaveLocale();
		}
	}

	public boolean amendSerial(DataSet doc, DataSet criteria, GCFServerEvent event, int year) {

		OperationRunner or = new OperationRunner(event.getUserTransactionEnvironment());
		or.setStartStatus(C_Reg.ST_REGISTERED);
		or.setSlave(true);
		or.setLockTimeout(0);
		or.setMaxTimeout(60000);
		or.setMaxTries(50);
		String serialBinderName = getServerBinder().getProperty("reg");
		Document serial = or.start(serialBinderName, C_Reg.OC_AMEND, criteria);
		String commitOperation = C_Reg.OP_AMEND;

		if (serial == null) {
			if (or.getLockException() != null) {
				setError(event.getData(), lng("The serial document is locked, try again"), event);
				return true;
			} else if (or.getServerRuleException() == null) {
				// No document found we will create one
				return false;
			} else {
				System.out.println("this one");
				setError(event.getData(), lng("Problem while generating the serial number"), event);
				return false;
			}
		} else {
			return doCommitOperation(or, commitOperation, serial, event, doc);
		}

	}

	public boolean doCommitOperation(OperationRunner or, String commitOperation, Document serial, GCFServerEvent event, DataSet doc) {
		String ser_let = "";
		String crn = "";
		Integer key = new Integer(0);
		DataField serial_nbr = serial.ds(C_Reg.SER).de(C_Reg.CUR);
		DataField serial_chr = serial.de(C_Reg.CHR);
		key = serial_nbr.getContentInteger();
		ser_let = serial_chr.getContentString();

		if (key == null) {
			key = C_Reg.FIRST_VALUE;
			// Initialize the first value to the serial document
			serial.ds(C_Reg.SER).de(C_Reg.INI).tryToSetContent(key);
		}
		//correction rodrigue 
		if(ser_let == null){
			ser_let = "O";
		}
		//fin correction rodrigue

		serial_nbr.tryToSetContent(key.intValue() + 1);
		doc.ds(CO).de(NBR).tryToSetContent(key.intValue() + 1);
		doc.ds(CO).ds(REG).de(SER).tryToSetContent(ser_let);
		//CRN
		doc.ds(CO).de(CRN).tryToSetContent(getCrn(doc, doc.ds(CO).de(NBR).getInt(), doc.ds(CO).ds(REG).de(SER).getString()));
		crn = doc.ds(CO).de(CRN).getString("");
		
		event.newDestination(doc);
		if (!or.commit(commitOperation)) {
			or.rollback(); // failure of commit operation
			return false;
		} else {
			event.getDestination().add(RESULT_REGISTERED_SERIAL_NBR, serial_nbr.getContent());
			event.getDestination().add(RESULT_REGISTERED_SERIAL_CHAR, ser_let);
			event.getDestination().add(RESULT_REGISTERED_CRN, crn);
			return true;
		}

	}

	public String getCrn(DataSet doc, int nbrResgister, String nbrSerial)
	{
		String crn ="";
		NumberFormat formatter = new DecimalFormat("00000");
		//int ideNbr = 0;
		int nbr = nbrResgister; // doc.ds(IDE).ds(REG).de(NBR).getInt();
		
		
		Date current = (Date) doc.ds(CO).ds(REG).de(DAT).getContent();
		int year = 0;
		if(current != null)
		{
			//year = current.getYear();
			Calendar myCalendar = GregorianCalendar.getInstance();
			myCalendar.setTime(current);
			year = myCalendar.get(Calendar.YEAR); //) YEAR;
		}
		String year1 = String.valueOf(year);
		String cty_cod = doc.ds(CO).ds(CTY).de(COD).getString();
		//String ideNbr = formatter.format(nbr); // format(nbr );
		String ideNbr = String.format("%05d", nbr);
		String ideSer = nbrSerial; //doc.ds(IDE).ds(REG).de(SER).getString("");
				
		 crn = String.valueOf(year) +  cty_cod + ideSer + ideNbr;
		System.out.println("crn  :"+ crn);
		//identificationIDType.setValue(mrn);

		return crn;
	}

	public synchronized boolean createSerial(DataSet doc, int year, GCFServerEvent event) {

		DataSet[] serialDocs = null;
		UserTransactionEnvironment environment = event.getUserTransactionEnvironment();

		DataSet criteria = new DataSet();
		criteria.add(C_Reg.YER).tryToSetContent(year);
		criteria.seg(C_Reg.CTY).add(C_Reg.COD).tryToSetContent(doc.ds(CO).ds(CTY).de(COD).getContent());
		//criteria.seg(C_Reg.SIT).add(C_Reg.COD).tryToSetContent(doc.ds(IDE).ds(CUO).ds(DPA).de(COD).getContent());

		String serialBinderName = getServerBinder().getProperty("reg");
		try {
			serialDocs = environment.loadDocumentsWithNonKeysData(serialBinderName, criteria);
		} catch (RemoteException _) {
			return false;
		}

		if (serialDocs != null && serialDocs.length != 0) {
			return false;
		}

		OperationRunner or = new OperationRunner(event.getUserTransactionEnvironment());
		or.setSlave(false);
		Document serial = or.start(serialBinderName, C_Reg.OC_NEW);
		serial.ds(C_Reg.CTY).de(C_Reg.COD).tryToSetContent(doc.ds(CO).ds(CTY).de(COD).getContent());
		serial.ds(C_Reg.CTY).de(C_Reg.DSC).tryToSetContent(doc.ds(CO).ds(CTY).de(DSC).getContent());
		serial.de(C_Reg.YER).tryToSetContent(year);
		String commitOperation = C_Reg.OP_CREATE;
		boolean createCommit = or.commit(commitOperation);
		if (!createCommit) {
			or.rollback();
		}
		return createCommit;
		// }
	}

	private static String lng(String property) {
		return so.i18n.IntlObj.createMessage("un.asyeco", property);
	}
}
