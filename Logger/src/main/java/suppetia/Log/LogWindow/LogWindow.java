package suppetia.Log.LogWindow;

import javax.swing.JFrame;
import javax.swing.JScrollBar;

import suppetia.Log.*;
import suppetia.Log.LogFunctions.*;
import net.miginfocom.swing.MigLayout;
import javax.swing.JScrollPane;
import javax.swing.JButton;
import javax.swing.JTable;
import javax.swing.ListSelectionModel;

import java.awt.event.ActionListener;
import java.io.IOException;
import java.awt.event.ActionEvent;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.awt.event.WindowEvent;
import java.awt.event.WindowListener;
import java.awt.event.FocusAdapter;
import java.awt.event.FocusEvent;
import javax.swing.JTextField;
import java.awt.event.WindowAdapter;
import javax.swing.JCheckBox;

public class LogWindow extends JFrame {
    private JTable tableData;
    private LogDataTableModel dataModel;
    private LogAnalysisTableModel analysisModel;
    private ILog log;
    private JScrollPane scrollPane;
    private JScrollPane scrollPane2;
    private JTable tableAnalysis;
    private JButton btnAnalyseAll;
    private JLabel lblNumberOfLogs;
    private JCheckBox chckbxLoggingEnabled;

    public LogWindow(String[] columnNames, Class<Number>[] columnClasses, boolean loggingEnabled, boolean saveOnClose) {

	if (saveOnClose) {
	    addWindowListener(new WindowAdapter() {
		@Override
		public void windowClosed(java.awt.event.WindowEvent arg0) {
		    try {
			boolean loggingEnabled = chckbxLoggingEnabled.isSelected();
			chckbxLoggingEnabled.setSelected(false);
			log.saveLog();
			chckbxLoggingEnabled.setSelected(loggingEnabled);
		    } catch (IOException e) {
			e.printStackTrace();
		    }
		}
	    });
	}

	setDefaultCloseOperation(DISPOSE_ON_CLOSE);
	setVisible(true);
	setBounds(0, 0, 400, 600);

	getContentPane().setLayout(new MigLayout("", "[grow]", "[grow][][86.5:86.5:86.5][]"));

	scrollPane = new JScrollPane();
	getContentPane().add(scrollPane, "cell 0 0,grow");

	// add the column names of "log time" and "description"
	String[] columnNamesFinal = new String[columnNames.length + 2];
	for (int i = 0; i < columnNames.length; i++) {
	    columnNamesFinal[i] = columnNames[i];
	}
	columnNamesFinal[columnNamesFinal.length - 2] = "description";
	columnNamesFinal[columnNamesFinal.length - 1] = "log time";
	dataModel = new LogDataTableModel(columnNamesFinal);

	tableData = new JTable(dataModel);
	tableData.addMouseListener(new MouseAdapter() {
	    @Override
	    public void mouseReleased(MouseEvent arg0) {
		if (tableData.getSelectedRow() != -1)
		    analyseSelectedRows(tableData.getSelectedRows());
		else
		    analyseAllRows();
	    }
	});
	tableData.setSelectionMode(ListSelectionModel.MULTIPLE_INTERVAL_SELECTION);
	scrollPane.setViewportView(tableData);

	analysisModel = new LogAnalysisTableModel(dataModel);
	
	lblNumberOfLogs = new JLabel("number of logs");
	getContentPane().add(lblNumberOfLogs, "cell 0 1,alignx left");

	scrollPane2 = new JScrollPane();
	getContentPane().add(scrollPane2, "cell 0 2,grow");

	tableAnalysis = new JTable(analysisModel);
	tableAnalysis.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);
	scrollPane2.setViewportView(tableAnalysis);

	JButton btnSaveLog = new JButton("save log");
	btnSaveLog.addActionListener(new ActionListener() {
	    public void actionPerformed(ActionEvent arg0) {
		try {
		    boolean loggingEnabled = chckbxLoggingEnabled.isSelected();
		    chckbxLoggingEnabled.setSelected(false);
		    log.saveLog();
		    chckbxLoggingEnabled.setSelected(loggingEnabled);
		    JOptionPane.showMessageDialog(null, "successfully saved");
		} catch (IOException e) {
		    e.printStackTrace();
		}
	    }
	});
	getContentPane().add(btnSaveLog, "flowx,cell 0 3");

	btnAnalyseAll = new JButton("analyse all");
	btnAnalyseAll.addActionListener(new ActionListener() {
	    public void actionPerformed(ActionEvent arg0) {
		analyseAllRows();
	    }
	});
	getContentPane().add(btnAnalyseAll, "cell 0 3,alignx right");
	
	chckbxLoggingEnabled = new JCheckBox("logging enabled");
	getContentPane().add(chckbxLoggingEnabled, "cell 0 3,alignx left");
	chckbxLoggingEnabled.setSelected(loggingEnabled);

	// initialize
	log = new Log(columnClasses);
    }

    /**
     * @wbp.parser.constructor
     */
    // constructor which doesn't save the Log on WindowClosing automatically
    public LogWindow(String[] columnNames, Class<Number>[] columnClasses, boolean loggingEnabled) {
	this(columnNames, columnClasses, loggingEnabled, false);
    }

    // constructor which displays a ILog in the LogWindow
    public LogWindow(final String[] columnNames, final ILog log, boolean loggingEnabled,boolean saveOnClose) {
	this(columnNames, log.getDataClasses(), loggingEnabled, saveOnClose);
	this.log = log;
	
	// read in the log data
	for (LogEntryNumber entry : this.log.getLogs()) {
	    dataModel.addLogEntry(entry);
	}
	
	// update lblNumberOfLogs
	lblNumberOfLogs.setText("number of logs: " + log.numberOfLogs());
	
	// scrolls to the bottom
	JScrollBar vertical = scrollPane.getVerticalScrollBar();
	vertical.setValue(vertical.getMaximum());
	
	analyseAllRows();
    }

    public LogWindow(String[] columnNames, ILog log, boolean loggingEnabled) {
	this(columnNames, log, loggingEnabled, false);
    }

    // log data with these functions
    public void log(LogEntryNumber logEntry) throws LogTypeException {
	if (chckbxLoggingEnabled.isSelected()) {
	    log.log(logEntry);
	    refreshTable();
	}
    }

    public void log(Number obj) throws LogTypeException {
	if (chckbxLoggingEnabled.isSelected()) {
	    log.log(obj);
	    refreshTable();
	}
    }

    public void log(Number obj, String description) throws LogTypeException {
	if (chckbxLoggingEnabled.isSelected()) {
	    log.log(obj, description);
	    refreshTable();
	}
    }

    public void log(Number[] objs) throws LogTypeException {
	if (chckbxLoggingEnabled.isSelected()) {
	    log.log(objs);
	    refreshTable();
	}
    }

    public void log(Number[] objs, String description) throws LogTypeException {
	if (chckbxLoggingEnabled.isSelected()) {
	    log.log(objs, description);
	    refreshTable();
	}
    }

    // analysis functions
    private double getAverage(int index, int[] rows) {
	return LogAnalysisFunctions.getAverage(log, index, rows);
    }

    private double getSum(int index, int[] rows) {
	return LogAnalysisFunctions.getSum(log, index, rows);
    }

    private double getMax(int index, int[] rows) {
	return LogAnalysisFunctions.getMax(log, index, rows);
    }

    private double getMin(int index, int[] rows) {
	return LogAnalysisFunctions.getMin(log, index, rows);
    }

    private void refreshTable() {
	// adds the logEntry to the table
	LogEntryNumber logEntry = log.getEntry(log.getLogs().size() - 1);
	dataModel.addLogEntry(logEntry);

	// updates lblNumberOfLogs
	lblNumberOfLogs.setText("number of logs: " + log.numberOfLogs());
	
	// scrolls to the bottom
	JScrollBar vertical = scrollPane.getVerticalScrollBar();
	vertical.setValue(vertical.getMaximum());
    }

    // executes analysis functions for the selected log entries
    private void analyseSelectedRows(int[] rows) {
	for (int i = 0; i < log.getDataAmount(); i++) {
	    analysisModel.setValueAt(getSum(i, rows), 0, i + 1);
	    analysisModel.setValueAt(getAverage(i, rows), 1, i + 1);
	    analysisModel.setValueAt(getMin(i, rows), 2, i + 1);
	    analysisModel.setValueAt(getMax(i, rows), 3, i + 1);
	}
    }

    // executes analysis functions for all the log entries
    public void analyseAllRows() {
	tableData.selectAll();
	analyseSelectedRows(tableData.getSelectedRows());
    }

}
