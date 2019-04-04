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
import javax.swing.JPanel;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.awt.event.FocusAdapter;
import java.awt.event.FocusEvent;
import javax.swing.JTextField;

public class LogWindow extends JFrame{
	private JTable tableData;
	private LogDataTableModel dataModel;
	private LogAnalysisTableModel analysisModel;
	private ILog log;
	private JScrollPane scrollPane;
	private JScrollPane scrollPane2;
	private JTable tableAnalysis;
	private JPanel panel;
	private JButton btnAnalyseAll;
	
	
    public LogWindow(String[] coloumnNames, Class<Number>[] coloumnClasses) {
	
	setDefaultCloseOperation(EXIT_ON_CLOSE);
	
	getContentPane().setLayout(new MigLayout("", "[grow]", "[grow][86.5:86.5:86.5][]"));

	scrollPane = new JScrollPane();
	getContentPane().add(scrollPane, "cell 0 0,grow");
	
	dataModel = new LogDataTableModel(coloumnNames);
	
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

	scrollPane2 = new JScrollPane();
	getContentPane().add(scrollPane2, "cell 0 1, grow");
	
	tableAnalysis = new JTable(analysisModel);
	tableAnalysis.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);
	scrollPane2.setViewportView(tableAnalysis);
	
	
	JButton btnSaveLog = new JButton("save log");
	btnSaveLog.addActionListener(new ActionListener() {
		public void actionPerformed(ActionEvent arg0) {
		    try {
			log.saveLog();
		    } catch (IOException e) {
			e.printStackTrace();
		    }
		}
	});
	getContentPane().add(btnSaveLog, "flowx,cell 0 2");
	
	btnAnalyseAll = new JButton("analyse all");
	btnAnalyseAll.addActionListener(new ActionListener() {
		public void actionPerformed(ActionEvent arg0) {
		    analyseAllRows();
		}
	});
	getContentPane().add(btnAnalyseAll, "cell 0 2,alignx right");
	
	
	// init
	init(coloumnClasses);
    }
    
    public void init(Class<Number>[] coloumnClasses) {
	log = new Log(coloumnClasses);
    }
    
    public void log(LogEntryNumber logEntry) throws LogTypeException {
	log.log(logEntry);
	refreshTable();
    }
    public void log(Number obj) throws LogTypeException {
	log.log(obj);
	refreshTable();
    }
    public void log(Number obj, String description) throws LogTypeException {
	log.log(obj, description);
	refreshTable();
    }
    public void log(Number[] objs) throws LogTypeException {
	log.log(objs);
	refreshTable();
    }
    public void log(Number[] objs, String description) throws LogTypeException {
	log.log(objs, description);
	refreshTable();
    }
    
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
	
	// scrolls to the bottom
	JScrollBar vertical = scrollPane.getVerticalScrollBar();
	vertical.setValue(vertical.getMaximum());
    }
    
    private void analyseSelectedRows(int[] rows) {
	for (int i = 0; i < log.getDataAmount(); i++) {
	    analysisModel.setValueAt(getSum(i, rows), 0, i + 1);
	    analysisModel.setValueAt(getAverage(i, rows), 1, i + 1);
	    analysisModel.setValueAt(getMin(i, rows), 2, i + 1);
	    analysisModel.setValueAt(getMax(i, rows), 3, i + 1);
	}
    }
    private void analyseAllRows() {
	int[] rows = new int[log.numberOfLogs()];
	for (int i = 0; i < rows.length; i++) {
	    rows[i] = i;
	}
	for (int i = 0; i < log.getDataAmount(); i++) {
	    analysisModel.setValueAt(getSum(i, rows), 0, i + 1);
	    analysisModel.setValueAt(getAverage(i, rows), 1, i + 1);
	    analysisModel.setValueAt(getMin(i, rows), 2, i + 1);
	    analysisModel.setValueAt(getMax(i, rows), 3, i + 1);
	}
    }

    
    
    
}
