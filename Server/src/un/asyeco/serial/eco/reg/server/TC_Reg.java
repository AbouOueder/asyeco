//   $Header: /home/asycuda/home/cvsroot/nattrs/Server/src/un/nattrs/serial/t1/reg/server/TC_Reg.java,v 1.4 2009-10-08 14:54:19 john Exp $

package un.asyeco.serial.eco.reg.server;

import java.sql.Types;

import so.kernel.server.ConnectionManager;
import so.kernel.server.GCFServerBinder;
import so.kernel.server.GCFTableConnector;
import so.kernel.server.Server;
import un.asyeco.serial.eco.reg.C_Reg;

public class TC_Reg extends GCFTableConnector implements C_Reg {

	public TC_Reg(GCFServerBinder serverBinder, ConnectionManager connectionManager) {

		super(serverBinder, connectionManager, Server.getString(S_Reg.SER_CO_REG_TAB));

		// Define the Instance Id field, column
		serverBinder.setInstanceIdField(this, INSTANCE_ID, INSTANCE_ID);

		// add the table columns
		add(de(YER), "YER", Types.INTEGER); // Year
		add(ds(CTY).de(COD), "CTY_COD", Types.VARCHAR); // Country code
		add(ds(CTY).de(DSC), "CTY_DSC", Types.VARCHAR); // Country name
		add(ds(SIT).de(COD), "SIT_COD", Types.VARCHAR); // Customs site code
		add(ds(SIT).de(NAM), "SIT_NAM", Types.VARCHAR); // Customs site name
		add(de(CHR), "CHR", Types.VARCHAR); // Serial char
		add(ds(SER).de(INI), "SER_INI", Types.INTEGER); // Serial#, initial
														// value
		add(ds(SER).de(CUR), "SER_CUR", Types.INTEGER); // Serial#, current
														// value

		setParticipateInSearch(true);
	}

}
