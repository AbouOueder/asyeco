/**
 * 
 */
package un.asyeco.eco.server.printing;

import net.sf.jasperreports.engine.JRDataSource;
import net.sf.jasperreports.engine.JRException;
import net.sf.jasperreports.engine.JRField;
import so.kernel.core.DataSet;

//GVA <patch ID="#530 Release Order improvement" version="4.2.2" type="IMPROVEMENT" date="Feb 15, 2014" author="Leonardo Flores">
public class EcoDataSource implements JRDataSource {

    private DataSet data;
    int index = 0;

    public EcoDataSource(DataSet data) {
        if (data == null) throw new NullPointerException("Source can't be null!");
        this.data = data;
    }

    public Object getFieldValue(JRField field) throws JRException {
        String fieldName = field.getName();
        if (data.getData(fieldName) == null) return null;
        return data.de(fieldName).getContent();
    }
    
    public boolean next() throws JRException {
        return index++ < 1;
    }
}