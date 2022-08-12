package un.asyeco.eco.server;

//import static un.broker.asysad.C_asysad.AUTHORIZATION_ERROR_MESSAGE_DPA;
//import static un.broker.asysad.C_asysad.CHK_KEYS;
//import static un.broker.asysad.C_asysad.COD;
//import static un.broker.asysad.C_asysad.CUO;
//import static un.broker.asysad.C_asysad.DAT;
//import static un.broker.asysad.C_asysad.DEC;
//import static un.broker.asysad.C_asysad.IDE;
//import static un.broker.asysad.C_asysad.INSTANCE_ID;
//import static un.broker.asysad.C_asysad.MOD_TAB;
//import static un.broker.asysad.C_asysad.NAM;
//import static un.broker.asysad.C_asysad.NBR;
//import static un.broker.asysad.C_asysad.OP_ASSESS;
//import static un.broker.asysad.C_asysad.OP_VALIDATE;
//import static un.broker.asysad.C_asysad.PRC;
//import static un.broker.asysad.C_asysad.REF;
//import static un.broker.asysad.C_asysad.REG;
//import static un.broker.asysad.C_asysad.RESULT_REGISTERED_SERIAL_CHAR;
//import static un.broker.asysad.C_asysad.RESULT_REGISTERED_SERIAL_NBR;
//import static un.broker.asysad.C_asysad.SAD;
//import static un.broker.asysad.C_asysad.SER;
//import static un.broker.asysad.C_asysad.TYP;
//import static un.broker.asysad.C_asysad.YER;

import java.rmi.RemoteException;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.Iterator;
import java.util.StringTokenizer;

import so.kernel.core.DataField;
import so.kernel.core.DataSet;
import so.kernel.core.Document;
import so.kernel.core.KernelEvent;
import so.kernel.core.events.ServerEvent;
import so.kernel.core.exceptions.ServerRuleException;
import so.kernel.core.interfaces.DocumentInterface;
import so.kernel.core.rules.DocumentRead_Item;
import so.kernel.server.GCFServerEvent;
import so.kernel.server.OperationRunner;
import so.kernel.server.ServerBinder;
import so.kernel.server.ServerRule;
import so.kernel.server.UserTransactionEnvironment;
import so.util.DebugOutput;
import so.util.calendar.DateValue;
import un.asyeco.eco.C_eCO;
import un.asyeco.serial.eco.submit.C_Sub;
//import un.broker.asysad.C_asysad;
//import un.broker.serial.reg.C_Reg;
//import un.broker.serial.sto.C_Sto;
import un.kernel.core.ArefHTCompatible;
import un.kernel.server.ServerVerifierDelegator;
import un.kernel.util.AWServerTranslator;

public class SR_Submit300722 extends ServerRule implements C_eCO {

	public SR_Submit300722(ServerBinder binder) {
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
			// ETH - lng ServerTranslator 09.2009
			AWServerTranslator.setLocale(event);
			//Progress Bar February 2011 - begin
			//SR_PrintManager.sendMessageToClient(e, lng("Numbering..."));
			//Progress Bar February 2011 - end
			DataSet doc = environment.getFinalDocument().ds("normal");


			// Need to check that customs office of departure matches one of the
			// user authorized offices
//			String usrAuthorizedCuos = environment.getProperty("office");
//			if (usrAuthorizedCuos != null) {
//				StringTokenizer st = new StringTokenizer(usrAuthorizedCuos, ",");
//				while (st.hasMoreTokens()) {
//					String usrAuthorizedCuo = st.nextToken().trim();
//					// System.out.println("User is authorized for customs
//					// office: " + st.nextToken().trim());
//					if (usrAuthorizedCuo.equalsIgnoreCase(doc.ds(IDE).ds(CUO).de(COD).getString("").trim())) {
//						authorized = true;
//					}
//				}
//				if (!authorized) {
//					setError(doc, lng(AUTHORIZATION_ERROR_MESSAGE_DPA) + doc.ds(IDE).ds(CUO).de(COD).getString("").trim(), event);
//					AWServerTranslator.leaveLocale();
//					return;
//				}
//			}

			Calendar cal = Calendar.getInstance();
			Date current = (Date) doc.ds(CO).ds(SUB).de(DAT).getContent();
			if (current == null) {
				DateFormat format = new SimpleDateFormat("dd/MM/yyyy HH:mm");
				try {
					current = format.parse(format.format(new DateValue(new Date().getTime())));
				} catch (Exception exc) {
				}

				doc.ds(CO).ds(SUB).de(DAT).tryToSetContent(current);

				/*
				 * root.ds(IDE).ds(REG).de(SER).setString(RegSerial); String
				 * RegNber = mh.getStr(); DebugOutput.print("Reg Nber " +
				 * RegNber); root.ds(IDE).ds(REG).de(NBR).setString(RegNber);
				 * String RegDate = mh.getStr(); DebugOutput.print("Reg Date " +
				 * RegDate);
				 * root.ds(IDE).ds(REG).de(DAT).tryToSetContent(AWUtil.dateFromEngine(RegDate));
				 */

			}
			int year = cal.get(Calendar.YEAR);
			Object cty_cod = doc.ds(CO).ds(CTY).de(COD).getContent();
			DataSet criteria = new DataSet(DocumentInterface.NORMAL_ID);
			criteria.add(C_Sub.YER).tryToSetContent(year);
			criteria.seg(C_Sub.CTY).add(C_Sub.COD).tryToSetContent(cty_cod);
			String serLet=getLastChar(criteria, event);
			//String serLet = getModTabSerial(doc);
			//DebugOutput.print("SerialChar: " + serLet);
			criteria.add(C_Sub.CHR).tryToSetContent(serLet);

			boolean isOK = amendSerial(doc, criteria, event, year);
			if (!isOK) {
				createSerial(doc, year, event);
				isOK = amendSerial(doc, criteria, event, year);
				if (!isOK) {
					setError(event.getData(), lng("Problem while generating the serial number"), event);
				}
			}//ahmed
			// GVA <patch ID="Bug #713 duplicated declarations with same declarant reference number" version="4.2.2" type="modification" date="Oct 09, 2014" author="ahmed">
			else{
//				//we need to check if dec/Ref exists id DB. The checking must be done here because the register operation must be locked at the same time , otherwise we get many declarations with the same declarant reference
// 
//				if (environment.getEndOperation().equals(OP_SUBMIT)) {
//					DataSet source = new DataSet();
//					DataSet cuo = source.seg(IDE).seg(CUO);
//					cuo.add(COD);
//					DataSet dec = source.seg(DEC);
//					dec.add(COD);
//					DataSet ref = dec.seg(REF);
//					ref.add(YER);
//					ref.add(NBR);
//					source.ds(IDE).ds(CUO).de(COD).tryToSetContent(doc.ds(IDE).ds(CUO).de(COD).getContent());
//					source.ds(DEC).ds(REF).de(YER).tryToSetContent(doc.ds(DEC).ds(REF).de(YER).getContent());
//					source.ds(DEC).ds(REF).de(NBR).tryToSetContent(doc.ds(DEC).ds(REF).de(NBR).getContent());
//					source.ds(DEC).de(COD).tryToSetContent(doc.ds(DEC).de(COD).getContent());
//					
//					
//					cal.setTime(current);
//					int curYear = cal.get(Calendar.YEAR);
//					if (doc.ds(DEC).ds(REF).de(YER).getContent() == null){
//					doc.ds(DEC).ds(REF).de(YER).tryToSetContent(curYear);
//					}
//					ServerEvent se = new ServerEvent(source, CHK_KEYS);
//					se.newDestination("keySad");
//					try {
//						se = environment.getServerBinder().fire(se);
//						
//						DocumentRead_Item item = (DocumentRead_Item) se.getDestination().getContent();
//						if (item != null) {
//							DataSet keys = item.getDataSet();
//							if (keys != null) {
//								if (keys.de(INSTANCE_ID).getInt(0) !=  environment.getFinalDocument().ds(DocumentInterface.KEYS_ID).de(INSTANCE_ID).getInt(0)) {
//									setError(event.getData(),  lng("This declaration already exists"), event);
//								}
//							}
//						}
//						
//					} catch (ServerRuleException exc) {
//						setError(event.getData(),lng("Problem while checking if This declaration already exists"), event);
//						
//					}
//					
//					
//				}
				
 
				    
 
			}
			// GVA <patch ID="Bug #713 duplicated declarations with same declarant reference number" version="4.2.2" type="modification" date="Oct 09, 2014" author="ahmed"/>
			AWServerTranslator.leaveLocale();
		}
	}

	public boolean amendSerial(DataSet doc, DataSet criteria, GCFServerEvent event, int year) {

		OperationRunner or = new OperationRunner(event.getUserTransactionEnvironment());
		or.setStartStatus(C_Sub.ST_REGISTERED);
		or.setSlave(true);
		or.setLockTimeout(0);
		or.setMaxTimeout(60000);
		or.setMaxTries(50);
		String serialBinderName = getServerBinder().getProperty("Serial_ast");
		Document serial = or.start(serialBinderName, C_Sub.OC_AMEND, criteria);
		String commitOperation = C_Sub.OP_AMEND;

		if (serial == null) {
			if (or.getLockException() != null) {
				setError(event.getData(), lng("The serial document is locked, try again"), event);
				return true;
			} else if ( or.getOtherException() != null) {
				setError(event.getData(), lng("Exception during updating Serial, try again"), event);  
				System.out.println("Exception during updating Serial, try again");
				return true;
			}  else if (or.getServerRuleException() == null) {
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
		Integer key = new Integer(0);
		DataField serial_nbr = serial.ds(C_Sub.SER).de(C_Sub.CUR);
		DataField serial_chr = serial.de(C_Sub.CHR);
		key = serial_nbr.getContentInteger();
		ser_let = serial_chr.getContentString();

		if (key == null) {
			key = C_Sub.FIRST_VALUE;
			// Initialize the first value to the serial document
			serial.ds(C_Sub.SER).de(C_Sub.INI).tryToSetContent(key);
		}

		serial_nbr.tryToSetContent(key.intValue() + 1);
		doc.ds(CO).ds(SUB).de(NBR).tryToSetContent(key.intValue() + 1);
		doc.ds(CO).ds(SUB).de(SER).tryToSetContent(ser_let);
		event.newDestination(doc);
		if (!or.commit(commitOperation)) {
			or.rollback(); // failure of commit operation
			return false;
		} else {

			event.getDestination().add(RESULT_SUBMIT_SERIAL_NBR, serial_nbr.getContent());
			event.getDestination().add(RESULT_SUBMIT_SERIAL_CHAR, ser_let);
			return true;
		}

	}

	public synchronized boolean createSerial(DataSet doc, int year, GCFServerEvent event) {

		DataSet[] serialDocs = null;
		UserTransactionEnvironment environment = event.getUserTransactionEnvironment();

		DataSet criteria = new DataSet();
		criteria.add(C_Sub.YER).tryToSetContent(year);
		criteria.seg(C_Sub.CTY).add(C_Sub.COD).tryToSetContent(doc.ds(CO).ds(CTY).de(COD).getContent());
		//criteria.add(C_Reg.CHR).tryToSetContent(lettre);
		 String lettre=getLastChar(criteria,event);
		// String lettre=getModTabSerial(doc);
//		if (lettre == null) {
//			return false;
//		}
		// criteria.de(C_Reg.YER).tryToSetContent(year);
		String serialBinderName = getServerBinder().getProperty("Serial_ast");
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
		Document serial = or.start(serialBinderName, C_Sub.OC_NEW);
		serial.ds(C_Sub.CTY).de(C_Sub.COD).tryToSetContent(doc.ds(CO).ds(CTY).de(COD).getContent());
		serial.ds(C_Sub.CTY).de(C_Sub.DSC).tryToSetContent(doc.ds(CO).ds(CTY).de(DSC).getContent());
		//serial.ds(C_Reg.SIT).de(C_Reg.NAM).tryToSetContent(doc.ds(IDE).ds(CUO).de(NAM).getContent());
		serial.de(C_Sub.CHR).tryToSetContent(lettre);

		serial.de(C_Sub.YER).tryToSetContent(year);
		String commitOperation = C_Sub.OP_CREATE;

		boolean createCommit = or.commit(commitOperation);

		if (!createCommit) {
			or.rollback();
		}
		return createCommit;
		// }
	}

	public String getLastChar(DataSet criteria, GCFServerEvent event) {

		criteria.de(C_Sub.YER).tryToSetContent((criteria.de(C_Sub.YER).getInt() - 1));
		OperationRunner or = new OperationRunner(event.getUserTransactionEnvironment());
		or.setStartStatus(C_Sub.ST_REGISTERED);
		or.setSlave(true);
		or.setLockTimeout(0);
		or.setMaxTimeout(60000);
		or.setMaxTries(50);
		String serialBinderName = getServerBinder().getProperty("Serial_ast");
		Document serial = or.start(serialBinderName, C_Sub.OP_VIEW, criteria);

		if (serial == null) {
			if (or.getLockException() != null) {
				setError(event.getData(), lng("The serial document is locked, try again"), event);
				return null;
			} else if (or.getServerRuleException() == null) {
				// No document found we will create one
				return "S";
			} else {
				System.out.println("this one");
				setError(event.getData(), lng("Problem while generating the serial number"), event);
				return null;
			}
		} else {
			String lastChar = serial.de(C_Sub.CHR).getString("S");
			or.rollback();
			return lastChar;
		}

	}

//	public String getModTabSerial(DataSet sad) {
//		String modTab = MOD_TAB;
//		String regSerial = "C";
//		String fld_cod = sad.ds(IDE).ds(TYP).de(SAD).getString("");
//		String fld_gpc = sad.ds(IDE).ds(TYP).de(PRC).getString("");
//		ArefHTCompatible verifier = ServerVerifierDelegator.getVerifier(getServerBinder());
//		java.util.Date now = new java.util.Date();
//		// java.util.Date now = AWUtil.getUTCDate();
//		Iterator it = verifier.find(modTab, now, "CUS_SER", new String[] { "MOD_COD", "CP1_COD" }, new Object[] { fld_cod, fld_gpc });
//		if (it.hasNext()) {
//			regSerial = (String) it.next();
//
//		}
//		if (it != null)
//			verifier.closeIterator(it);
//		return regSerial;
//	}

	private static String lng(String property) {
		return so.i18n.IntlObj.createMessage("un.asyeco", property);
	}

}
