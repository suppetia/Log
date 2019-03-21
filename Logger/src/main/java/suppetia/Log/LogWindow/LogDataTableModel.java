package suppetia.Log.LogWindow;

import javax.swing.table.DefaultTableModel;
import suppetia.Log.LogEntryNumber;

public class LogDataTableModel extends DefaultTableModel {

    public LogDataTableModel(Object[] columnNames) {
	super(columnNames, 0);
    }
    
    public void addLogEntry(LogEntryNumber logEntry) {
	Object[] data = new Object[logEntry.getDataAmount()+ 2];
	for (int i = 0; i < logEntry.getDataAmount(); i++) {
	    data[i] = logEntry.getDataAt(i); 
	}
	data[data.length - 2] = logEntry.getDescription();
	data[data.length - 1] = logEntry.getLogtime();
	
	addRow(data);
	
    }
    
    /**
     * overrides isCellEditable to ensure data can't be removed/changed
     */
    @Override
    public boolean isCellEditable(int arg0, int arg1) {
        
        return false;
    }
}
