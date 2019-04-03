package suppetia.Log.LogWindow;

import javax.swing.table.DefaultTableModel;

class LogAnalysisTableModel extends DefaultTableModel {

    public LogAnalysisTableModel(LogDataTableModel dataModel) {
	super(getColumnNames(dataModel), 4);
	setValueAt("sum", 0, 0);
	setValueAt("average", 0, 1);
	setValueAt("minimum", 0, 2);
	setValueAt("maximum", 0, 3);
    }
    
    public static Object[] getColumnNames(LogDataTableModel dataModel) {
	Object[] columnNames = new Object[dataModel.getColumnCount() - 1]; // number of data columns + analysis names
	columnNames[0] = "Analysis";
	for(int i = 1; i < columnNames.length - 1; i++) {
	    columnNames[i]= dataModel.getColumnName(i);
	}
	return columnNames;
    }
    
    /**
     * overrides isCellEditable to ensure data can't be removed/changed
     */
    @Override
    public boolean isCellEditable(int arg0, int arg1) {
        
        return false;
    }
}
