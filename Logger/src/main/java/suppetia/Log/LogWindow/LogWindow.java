package suppetia.Log.LogWindow;

import javax.swing.JFrame;
import javax.swing.JScrollBar;

import suppetia.Log.*;
import suppetia.Log.LogFunctions.LogAnalysisFunctions;
import net.miginfocom.swing.MigLayout;
import javax.swing.JScrollPane;
import javax.swing.JButton;
import javax.swing.JTable;
import javax.swing.ListSelectionModel;

import java.awt.event.ActionListener;
import java.io.IOException;
import java.awt.event.ActionEvent;

public class LogWindow extends JFrame{
	private JTable table;
	private LogDataTableModel dataModel;
	private ILog log;
	private JScrollPane scrollPane;
	
	
    public LogWindow(String[] coloumnNames, Class<Number>[] coloumnClasses) {
	
	setDefaultCloseOperation(EXIT_ON_CLOSE);
	
	
	getContentPane().setLayout(new MigLayout("", "[grow]", "[grow][]"));
	
	scrollPane = new JScrollPane();
	getContentPane().add(scrollPane, "cell 0 0,grow");
	
	dataModel = new LogDataTableModel(coloumnNames);
	
	table = new JTable(dataModel);
	table.setCellSelectionEnabled(true);
	table.setSelectionMode(ListSelectionModel.MULTIPLE_INTERVAL_SELECTION);
	scrollPane.setViewportView(table);
	
	
	JButton btnSaveLog = new JButton("save log");
	btnSaveLog.addActionListener(new ActionListener() {
		public void actionPerformed(ActionEvent arg0) {
		    try {
			log.saveLog();
		    } catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		    }
		}
	});
	getContentPane().add(btnSaveLog, "cell 0 1");
	
	
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
    
    public double getAverage(int index, int[] rows) {
	return LogAnalysisFunctions.getAverage(log, index, rows);
    }
    public double getSum(int index, int[] rows) {
	return LogAnalysisFunctions.getSum(log, index, rows);
    }
    public double getMax(int index, int[] rows) {
	return LogAnalysisFunctions.getMax(log, index, rows);
    }
    public double getMin(int index, int[] rows) {
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
    
    
}
