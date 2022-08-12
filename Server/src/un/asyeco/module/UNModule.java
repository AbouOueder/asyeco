/*
 * UNModule.java
 *
 * Created on 28 juin 2022 18:51:29
 */

package un.asyeco.module;

import so.kernel.core.modules.ModuleInstall;
import so.kernel.server.DocumentModulesManager;

/**
 *
 */
public class UNModule extends ModuleInstall {
    
    /** Creates a new instance of UNModule */
    public UNModule() {
    }
    
    /** Called when an already-installed module is restored (at System startup
     * time). Should perform whatever initializations are required. The module
     * can load resource about the module. The class loader is a module class
     * loader.
     *
     */
    public void restored() {
        DocumentModulesManager.registerModule(new UNDocumentInfo());
    }
    
}
