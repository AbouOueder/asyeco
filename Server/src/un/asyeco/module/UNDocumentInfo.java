/*
 * UNDocumentInfo.java
 *
 * Created on 28 juin 2022 18:51:29
 */

package un.asyeco.module;


import so.kernel.server.DocumentModuleInfo;
import so.kernel.server.Server;
import so.ems.server.storage.def.DefaultEMSStorage;
import java.util.HashMap;


/**
 *
 */
public class UNDocumentInfo extends DocumentModuleInfo {
    
    /** Creates a new instance of UNDocumentInfo */
    public UNDocumentInfo() {
        super();
    }
    
    protected void init() {
        String url = Server.getString("module.un.asyeco.gcf.url");
        String usr = Server.getString("module.un.asyeco.gcf.user");
        String passwd = Server.getString("module.un.asyeco.gcf.password");
        
        String iedTable = Server.getString("module.un.asyeco.gcf.tables.ied");
        String historyTable = Server.getString("module.un.asyeco.gcf.tables.history");
        String locksTable = Server.getString("module.un.asyeco.gcf.tables.locks");
        String trackTable = Server.getString("module.un.asyeco.gcf.tables.track");
        
        setIED(url, usr, passwd, iedTable);
        setHistory(url, usr, passwd, historyTable);
        setLocks(url, usr, passwd, locksTable);
        setTrack(url, usr, passwd, trackTable);
        
        String mediaTablesUrl = Server.getString("module.un.asyeco.media.tables.url");
        String mediaTablesUser = Server.getString("module.un.asyeco.media.tables.user");
        String mediaTablesPass = Server.getString("module.un.asyeco.media.tables.pass");

        String idGeneratorTable = Server.getString("module.un.asyeco.media.tables.id.generator");
        String resourcesDetailsTable = Server.getString("module.un.asyeco.media.tables.resources.details");
        String multiMediaTable = Server.getString("module.un.asyeco.media.tables.multimedia");
        String multiMediaResourcesTable = Server.getString("module.un.asyeco.media.tables.multimedia.resources");
        String multiMediaResourcesPropsTable = Server.getString("module.un.asyeco.media.tables.multimedia.resources.props");
        String contentsDetailsTable = Server.getString("module.un.asyeco.media.tables.multimedia.contents.details");
        
        HashMap<String, String> mediaTables = new HashMap<String, String>();
        mediaTables.put(DefaultEMSStorage.ID_GENERATOR_TABLE, idGeneratorTable);
        mediaTables.put(DefaultEMSStorage.RESOURCES_DETAILS_TABLE, resourcesDetailsTable);
        mediaTables.put(DefaultEMSStorage.MULTIMEDIA_TABLE, multiMediaTable);
        mediaTables.put(DefaultEMSStorage.MULTIMEDIA_RESOURCES_TABLE, multiMediaResourcesTable);
        mediaTables.put(DefaultEMSStorage.MULTIMEDIA_RESOURCES_PROPS_TABLE, multiMediaResourcesPropsTable);
        mediaTables.put(DefaultEMSStorage.CONTENTS_DETAILS_TABLE, contentsDetailsTable);
        
        addTables(DefaultEMSStorage.MEDIA_TABLES_SOURCE, mediaTablesUrl, mediaTablesUser, mediaTablesPass, mediaTables);
        
        String contentDatasTableUrl = Server.getString("module.un.asyeco.content.datas.table.url");
        String contentDatasTableUser = Server.getString("module.un.asyeco.content.datas.table.user");
        String contentDatasTablePass = Server.getString("module.un.asyeco.content.datas.table.pass");
        
        String contentDatasTable = Server.getString("module.un.asyeco.content.datas.table");
        
        addTable(DefaultEMSStorage.CONTENT_DATAS_TABLE_SOURCE, contentDatasTableUrl, contentDatasTableUser, contentDatasTablePass, contentDatasTable);
    }
    
}
