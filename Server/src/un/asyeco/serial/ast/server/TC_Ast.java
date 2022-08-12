//
//   $Header: /home/asycuda/home/cvsroot/asybrk/Server/src/un/broker/serial/ast/server/TC_Ast.java,v 1.5 2012-07-03 07:36:03 kazan Exp $

package un.asyeco.serial.ast.server;

import java.sql.Types;

import so.kernel.server.ConnectionManager;
import so.kernel.server.GCFServerBinder;
import so.kernel.server.GCFTableConnector;
import so.kernel.server.Server;
import un.asyeco.serial.ast.C_Ast;


public class TC_Ast extends GCFTableConnector implements C_Ast {

	public TC_Ast(GCFServerBinder serverBinder, ConnectionManager connectionManager) {

		super(serverBinder, connectionManager, Server.getString(S_Ast.SER_CO_SUB_TAB));

		// Define the Instance Id field, column
		serverBinder.setInstanceIdField(this, INSTANCE_ID, INSTANCE_ID);

		// add the table columns
		add(de(YER), "YER", Types.INTEGER); // Year
		add(ds(CTY).de(COD), "CTY_COD", Types.VARCHAR); // Country code
		add(ds(CTY).de(DSC), "CTY_DSC", Types.VARCHAR); // Country name
		add(ds(SIT).de(COD), "SIT_COD", Types.VARCHAR); // Customs site code
		add(ds(SIT).de(NAM), "SIT_NAM", Types.VARCHAR); // Customs site name
		//add(de(DTI), "DIR_INP", Types.BOOLEAN); // Direct Trace Input
		add(de(CHR), "CHR", Types.VARCHAR); // Serial char
		add(ds(SER).de(INI), "SER_INI", Types.INTEGER); // Serial#, initial
														// value
		add(ds(SER).de(CUR), "SER_CUR", Types.INTEGER); // Serial#, current
														// value

		setParticipateInSearch(true);
	}

}
