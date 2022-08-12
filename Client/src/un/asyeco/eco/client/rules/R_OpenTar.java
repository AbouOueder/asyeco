package un.asyeco.eco.client.rules;

import java.util.ArrayList;

import so.kernel.core.BinderShortcut;
import so.kernel.core.ClientBinderInfo;
import so.kernel.core.DataSet;
import so.kernel.core.Document;
import so.kernel.core.Operation;
import so.kernel.core.Operations;
import so.kernel.core.Rule;
import so.kernel.core.events.StartOperationEvent;
import un.globalConfig.util.GlobalConfigUtilities;

public class R_OpenTar extends Rule {

    private DataSet set;

    public R_OpenTar(DataSet set) {
        this.set = set;
    };

    public void apply(so.kernel.core.KernelEvent e) {
        // if ("N".equals(Document.getClientProperty(".tar.acces")))
        if ("N".equals(GlobalConfigUtilities.getProperty(".tar.acces"))) return;
        so.kernel.core.events.PrepareAttachedFinderEvent ev = (so.kernel.core.events.PrepareAttachedFinderEvent) e;
        openAsytar(ev.getDocument());
        e.consume();
    }

    private boolean openAsytar(final Document doc) {
        // First search for the BinderShortcut in order to give the data through
        // it when the operation is started
        String classToOpen = "un.asytar.tariff.D_Tar";
        final String operationToPerform = "View"; // operation class name
        int opId = Operation.INTERNAL_OPERATIONS_MAX + 18; // some of the final
                                                           // operations in the
                                                           // OperationClass
        // int opId = C_Tar.OI_OPEN - better use this if asytar is dependent and
        // you have correct classpath.properties
        java.util.Enumeration e = ClientBinderInfo.getAllShortcutIdsForClass(classToOpen);
        DataSet keys = (DataSet) doc.getKeys().clone();
        while (e.hasMoreElements()) {
            int id = ((Integer) e.nextElement()).intValue();
            // if we have access right to start a read operation, do this
            if (ClientBinderInfo.checkAccessRight(id, opId)) {
                // create the new shortcut that will activate the new document
                final BinderShortcut bs = ClientBinderInfo.createBinderShortcut(id, keys);
                Operations ops = ClientBinderInfo.getValidOperations(id);
                // get the operation to start
                final Operation op = ops.getOperation(opId);
                // This is correct shortcut as it gives us access to the final
                // operation opId
                if (op != null) {

                    // THE TRICKY CODE - LINK THE DATA FROM THE PARENT DOCUMENT
                    // TO THE SHORTCUT
                    ArrayList hiddenData = new ArrayList(); // will not be
                                                            // cloned with
                                                            // BinderShortcut
                    bs.tmp("hidden", hiddenData); // will not travel with the
                                                  // network, will be saved
                                                  // with the desktop

                    // THE ADDING OF ARRAY LIST AS CONTENT WILL PREVENT THE
                    // CLONING OF THE DATA
                    // INSIDE THE LIST, WHEN THE SHORTCUT IS CLONED, SO TAR
                    // DOCUMENT WILL BE ABLE
                    // TO ACCESS AND MODIFY THE ORIGINAL SEGMENT VIA THE THE
                    // LINK!
                    //
                    hiddenData.add(set);
                    // THAT'S ALL

                    // we have the shortcut and the operation, start the
                    // operation giving explicitely a BinderShortcut
                    StartOperationEvent ev = new StartOperationEvent(doc, operationToPerform, bs);
                    doc.fire(ev);
                } else {
                    return false;
                }
                return true;
            }
        }
        return false;
    }

}
