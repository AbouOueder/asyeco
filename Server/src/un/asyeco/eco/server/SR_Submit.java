package un.asyeco.eco.server;

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
import so.kernel.core.interfaces.DocumentInterface;
import so.kernel.server.GCFServerEvent;
import so.kernel.server.OperationRunner;
import so.kernel.server.ServerBinder;
import so.kernel.server.ServerRule;
import so.kernel.server.UserTransactionEnvironment;
import so.util.DebugOutput;
import so.util.calendar.DateValue;
import un.asyeco.eco.C_eCO;
import un.asyeco.serial.ast.C_Ast;
import un.globalConfig.util.GlobalServerConfigUtilities;
import un.kernel.core.ArefHTCompatible;
import un.kernel.server.ServerVerifierDelegator;
import un.kernel.util.AWServerTranslator;

public class SR_Submit extends ServerRule implements C_eCO {

    public SR_Submit(ServerBinder binder) {
        super(binder);
    }

    @Override
    protected void apply(KernelEvent e) {
        DebugOutput.print("*****************     ASSESSSERIALSTART");
        // if (e instanceof GCFServerEvent) {
        // Get GCF document
        if (e instanceof GCFServerEvent) {

            e = e;

        } //else {
//            DataSet evt = (DataSet) e.getSource();
//            e = (GCFServerEvent) evt.de(EVT_ASS).getContent();
//        }

        boolean authorized = false;
        boolean doSerial = false;
        DataField nbr = null;
        GCFServerEvent event = (GCFServerEvent) e;
        UserTransactionEnvironment environment = event.getUserTransactionEnvironment();
        // ETH - lng ServerTranslator 09.2009
        AWServerTranslator.setLocale(event);
        // fin lng
        // Progress Bar February 2011 - begin
       // SR_PrintManager.sendMessageToClient(e, lng("Numbering..."));
        // Progress Bar February 2011 - end
        DataSet doc = environment.getFinalDocument().ds("normal");

        // String ser_let = "";
        // Need to check that customs office of departure matches one of the
        // user authorized offices
//        String usrAuthorizedCuos = environment.getProperty("office");
//        if (usrAuthorizedCuos != null) {
//            StringTokenizer st = new StringTokenizer(usrAuthorizedCuos, ",");
//            while (st.hasMoreTokens()) {
//                String usrAuthorizedCuo = st.nextToken().trim();
//                // System.out.println("User is authorized for customs office: "
//                // + st.nextToken().trim());
//                if (usrAuthorizedCuo.equalsIgnoreCase(doc.ds(IDE).ds(CUO).de(COD).getString("").trim())) {
//                    authorized = true;
//                }
//            }
//            if (!authorized) {
//                setError(doc, lng(AUTHORIZATION_ERROR_MESSAGE_DPA) + " " + doc.ds(IDE).ds(CUO).de(COD).getString("").trim(), event);
//                AWServerTranslator.leaveLocale();
//                return;
//            }
//        }
//        if (e.getID() != SR_OFFSET + OI_ASSESS_FROM_SELECTED_STANDBY) {
//            boolean importSadFlow = getSADFlow(doc) == 1;
//            if (((importSadFlow && "true".equals(GlobalServerConfigUtilities.getProperty(environment, "Client.ISel"))) || (!importSadFlow && "true"
//                    .equals(GlobalServerConfigUtilities.getProperty(environment, "Client.ESel"))))
//                    && ((doc.de(DELST).getInt(0) > 0 || doc.de(MIN).getInt(0) > 0) || (Integer.toString(CRED).equals(doc.ds(PTY).ds(COL).de(IND)
//                            .getString("")) || Integer.toString(CYELLOW).equals(doc.ds(PTY).ds(COL).de(IND).getString(""))))) {
//                System.out.println("pas de liquidation automatique");
//                AWServerTranslator.leaveLocale();
//                return;
//            }
//
//        }
        // On verifie qu'il n'y a pas d'administrations en attente de
        // selectivite
        DataSet root = environment.getFinalDocument().ds(DocumentInterface.NORMAL_ID);
//        boolean admSelOpen = SR_ChkAdministrationSelectivity.checkSelAdmOpen(environment, event, "un.asybrk B_AdmItmSel", root);
//
//        if (admSelOpen) {
//            System.out.println("Some administrations selectivity lines are not green");
//        }
//
//        DataSet destination = event.getDestination().ds("COL_INF");
//        if (destination != null) {
//            if (destination.ds(PTY).ds(COL).de(OAS).getString("").length() != 0) {
//                System.out.println("Some administrations selectivity lines are not green 2 ");
//                AWServerTranslator.leaveLocale();
//                return;
//            }
//        }
//
//        // Post-entry
//        if (!"".equals(doc.ds(IDE).ds(AST).de(SER).getString(""))) {
//            if (event.getID() == SR_OFFSET + OI_POST_ENTRY) {
//                Integer value = Integer.valueOf(doc.ds(IDE).ds(PST).de(NBR).getString("0"));
//                doc.ds(IDE).ds(PST).de(NBR).tryToSetContent(String.valueOf(value.intValue() + 1));
//                doc.ds(IDE).ds(PST).de(DAT).tryToSetContent(new DateValue(new Date().getTime()));
//            } else {
//                DebugOutput.print("Pb while generation the serial number, Event not compatible with this action id: " + event.getID());
//                setError(doc, lng("Problem while generating the serial number"), event);
//            }
//            AWServerTranslator.leaveLocale();
//            return;
//        }
        // end PostEntry

        Calendar cal = Calendar.getInstance();
        Date current = (Date) doc.ds(CO).ds(SUB).de(DAT).getContent();
        if (current == null) {
            DateFormat format = new SimpleDateFormat("dd/MM/yyyy HH:mm");
            try {
                current = format.parse(format.format(new DateValue(new Date().getTime())));
            } catch (Exception exc) {
            }

            doc.ds(CO).ds(SUB).de(DAT).tryToSetContent(current);

        }
        int year = cal.get(Calendar.YEAR);
        Object cty_cod = doc.ds(CO).ds(CTY).de(COD).getContent();
        DataSet criteria = new DataSet(DocumentInterface.NORMAL_ID);
        criteria.add(C_Ast.YER).tryToSetContent(year);

//        String declarant = environment.getProperty("declarant");
       // Boolean directTradeInput = new Boolean(false);
        //directTradeInput = "1".equals(declarant);
        //criteria.add(C_Ast.DTI).tryToSetContent(directTradeInput);
        criteria.seg(C_Ast.CTY).add(C_Ast.COD).tryToSetContent(cty_cod);
        //String serLet = getModTabSerial(doc);
        String serLet = "S";
        DebugOutput.print("SerialChar:" + serLet);
        criteria.add(C_Ast.CHR).tryToSetContent(serLet);
        // Avoid duplicates - Start
        /*boolean lockFlag = S_asysad.assSerialLock.isSerialLocked((String) cuo_cod, serLet);
        if (lockFlag) {
            setError(event.getData(), lng("The serial document is locked, try again"), event);
            AWServerTranslator.leaveLocale();
            return;
        }
        */
        // Avoid duplicates - end
        boolean isOK = amendSerial(doc, criteria, event, year);
        if (!isOK) {
          //  if (directTradeInput.booleanValue()) {
             //   directTradeInput = Boolean.valueOf(false);
            //    criteria.de(C_Ast.DTI).tryToSetContent(directTradeInput);
//                isOK = amendSerial(doc, criteria, event, year);
//                if (!isOK) {
//                    createSerial(doc, year, event, serLet);
//                    isOK = amendSerial(doc, criteria, event, year);
//                    if (!isOK) {
//                        setError(event.getData(), lng("Problem while generating the serial number"), event);
//                    }
//                }

         //   } 
            //else {
                createSerial(doc, year, event, serLet);
                isOK = amendSerial(doc, criteria, event, year);
                if (!isOK) {
                    setError(event.getData(), lng("Problem while generating the serial number"), event);
                }
           // }

        }
        // Avoid duplicates - Start
       // S_asysad.assSerialLock.unLockSerial((String) cuo_cod, serLet);
        // Avoid duplicates - end
        AWServerTranslator.leaveLocale();
        // }
    }

    public boolean amendSerial(DataSet doc, DataSet criteria, GCFServerEvent event, int yearn) {

        OperationRunner or = new OperationRunner(event.getUserTransactionEnvironment());
        or.setStartStatus(C_Ast.ST_REGISTERED);
        or.setSlave(true);
        or.setLockTimeout(0);
        or.setMaxTimeout(60000);
        or.setMaxTries(50);
        String serialBinderName = getServerBinder().getProperty("Serial_ast");
        Document serial = or.start(serialBinderName, C_Ast.OC_AMEND, criteria);
        String commitOperation = C_Ast.OP_AMEND;

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
        Integer key = new Integer(0);
        DataField serial_nbr = serial.ds(C_Ast.SER).de(C_Ast.CUR);
        DataField serial_chr = serial.de(C_Ast.CHR);
        key = serial_nbr.getContentInteger();
        ser_let = serial_chr.getContentString();

        if (key == null) {
            key = C_Ast.FIRST_VALUE;
            // Initialize the first value to the serial document
            serial.ds(C_Ast.SER).de(C_Ast.INI).tryToSetContent(key);
        }

        serial_nbr.tryToSetContent(key.intValue() + 1);
        doc.ds(CO).ds(SUB).de(NBR).tryToSetContent(key.intValue() + 1);
        doc.ds(CO).ds(SUB).de(SER).tryToSetContent(ser_let);
        event.newDestination(doc);
        if (!or.commit(commitOperation)) {
            or.rollback(); // failure of commit operation
            return false;
        } else {

            DataSet destination = event.getDestination();
            destination.add(RESULT_SUBMIT_SERIAL_NBR, serial_nbr.getContent());
            destination.add(RESULT_SUBMIT_SERIAL_CHAR, ser_let);
            return true;
        }

    }

    public synchronized boolean createSerial(DataSet doc, int year, GCFServerEvent event, String lettre) {

        DataSet[] serialDocs = null;
        UserTransactionEnvironment environment = event.getUserTransactionEnvironment();

        DataSet criteria = new DataSet();
        criteria.add(C_Ast.YER).tryToSetContent(year);
        criteria.seg(C_Ast.CTY).add(C_Ast.COD).tryToSetContent(doc.ds(CO).ds(CTY).de(COD).getContent());
        criteria.add(C_Ast.CHR).tryToSetContent(lettre);
        // String lettre =getLastChar(criteria,event);
        // String lettre =getModTabSerial(doc);
        if (lettre == null) {
            return false;
        }
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
        Document serial = or.start(serialBinderName, C_Ast.OC_NEW);
        serial.ds(C_Ast.CTY).de(C_Ast.COD).tryToSetContent(doc.ds(CO).ds(CTY).de(COD).getContent());
        serial.ds(C_Ast.CTY).de(C_Ast.DSC).tryToSetContent(doc.ds(CO).ds(CTY).de(DSC).getContent());
        serial.de(C_Ast.CHR).tryToSetContent(lettre);
        serial.de(C_Ast.YER).tryToSetContent(year);
        //Boolean directInputTrade = new Boolean(false);
        //serial.de(C_Ast.DTI).tryToSetContent(directInputTrade);
        String commitOperation = C_Ast.OP_CREATE;
        boolean createCommit = or.commit(commitOperation);
        if (!createCommit) {
            or.rollback();
        }
        return createCommit;
        // }
    }

    public String getLastChar(DataSet criteria, GCFServerEvent event) {

        criteria.de(C_Ast.YER).tryToSetContent((criteria.de(C_Ast.YER).getInt() - 1));
        OperationRunner or = new OperationRunner(event.getUserTransactionEnvironment());
        or.setStartStatus(C_Ast.ST_REGISTERED);
        or.setSlave(true);
        or.setLockTimeout(0);
        or.setMaxTimeout(60000);
        or.setMaxTries(50);
        String serialBinderName = getServerBinder().getProperty("Serial_ast");
        Document serial = or.start(serialBinderName, C_Ast.OC_AMEND, criteria);
        String commitOperation = C_Ast.OP_AMEND;

        if (serial == null) {
            if (or.getLockException() != null) {
                setError(event.getData(), lng("The serial document is locked, try again"), event);
                return null;
            } else if (or.getServerRuleException() == null) {
                // No document found we will create one
                return "L";
            } else {
                System.out.println("this one");
                setError(event.getData(), lng("Problem while generating the serial number"), event);
                return null;
            }
        } else {
            String lastChar = serial.de(C_Ast.CHR).getString("S");
            or.rollback();
            return lastChar;
        }

    }

//    public int getSADFlow(DataSet sad) {
//        String modTab = MOD_TAB;
//        String flow = "";
//        String cod, gpc, typ;
//        String fld_cod = sad.ds(IDE).ds(TYP).de(SAD).getString("");
//        String fld_gpc = sad.ds(IDE).ds(TYP).de(PRC).getString("");
//        ArefHTCompatible verifier = ServerVerifierDelegator.getVerifier(getServerBinder());
//        java.util.Date now = new java.util.Date();
//        // java.util.Date now = AWUtil.getUTCDate();
//        Iterator it = verifier.find(modTab, now, "MOD_FLW", new String[] { "MOD_COD", "CP1_COD" }, new Object[] { fld_cod, fld_gpc });
//        if (it.hasNext()) {
//            typ = (String) it.next();
//
//            if (typ.equals("0")) {
//                flow = "E"; // Export
//                if (it != null) verifier.closeIterator(it);
//                return 0;
//            } else {
//                if (typ.equals("1")) {
//                    flow = "I"; // Import
//                    if (it != null) verifier.closeIterator(it);
//                    return 1;
//                } else {
//                    flow = "";
//                    if (it != null) verifier.closeIterator(it);
//                    return -1;
//                }
//            }
//        }
//        if (it != null) verifier.closeIterator(it);
//        return -1;
//    }

//    public String getModTabSerial(DataSet sad) {
//        String modTab = MOD_TAB;
//        String flow = "";
//        String cod, gpc, assSerial = "L";
//        String fld_cod = sad.ds(IDE).ds(TYP).de(SAD).getString("");
//        String fld_gpc = sad.ds(IDE).ds(TYP).de(PRC).getString("");
//        ArefHTCompatible verifier = ServerVerifierDelegator.getVerifier(getServerBinder());
//        java.util.Date now = new java.util.Date();
//        // java.util.Date now = AWUtil.getUTCDate();
//        Iterator it = verifier.find(modTab, now, "ASS_SER", new String[] { "MOD_COD", "CP1_COD" }, new Object[] { fld_cod, fld_gpc });
//        if (it.hasNext()) {
//            assSerial = (String) it.next();
//
//        }
//        if (it != null) verifier.closeIterator(it);
//        return assSerial;
//    }

    private static String lng(String property) {
        return so.i18n.IntlObj.createMessage("un.asyeco", property);
    }

}
