package suppetia.Log.LogWindow;

import javax.swing.table.DefaultTableModel;

class LogAnalysisTableModel extends DefaultTableModel {

    public LogAnalysisTableModel(LogDataTableModel dataModel) {
	super(getColumnNames(dataModel), 4);
	setValueAt("sum", 0, 0);
	setValueAt("average", 1, 0);
	setValueAt("minimum", 2, 0);
	setValueAt("maximum", 3, 0);
    }
    
    public static Object[] getColumnNames(LogDataTableModel dataModel) {
	Object[] columnNames = new Object[dataModel.getColumnCount() - 1]; // number of data columns + analysis names
	columnNames[0] = "Analysis";
	for(int i = 0; i < columnNames.length - 1; i++) {
	    columnNames[i + 1]= dataModel.getColumnName(i);
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
