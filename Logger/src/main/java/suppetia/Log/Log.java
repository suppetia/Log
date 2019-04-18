/*
 * List of LogEntries of one kind
 * 2019/03/02
 * Christopher Mertens
 */
package suppetia.Log;

import java.io.BufferedWriter;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Iterator;

public class Log implements ILog {
    
    private ArrayList<LogEntryNumber> logEntries;
    private int dataAmount;
    private String description;
    private Class<Number>[] dataClasses;
    
    public Log(Class<Number>[] dataClasses) {
	logEntries = new ArrayList<>();
	this.dataClasses = dataClasses;
	this.dataAmount = dataClasses.length;
	this.description = null;
    }
    public Log(Class<Number>[] dataTypes, String description) {
	this(dataTypes);
	this.description = description;
    }

    @Override
    public ArrayList<LogEntryNumber> getLogs() {
        return logEntries;
    }
    
    @Override
    public Class<Number>[] getDataClasses() {
        return dataClasses;
    }
    
    @Override
    public Class<Number> getDataClass(int index) throws NullPointerException {
	if (index < dataAmount && index >= 0) return getDataClasses()[index];
	throw new NullPointerException();
    }
    
    @Override
    public String getDescription() {
	if (description != null) return description;
	else return "no description";
    }
    
    @Override
    public int getDataAmount() {
        return dataAmount;
    }

    @Override
    public int numberOfLogs() {
	return logEntries.size();
    }

    @Override
    public LogEntryNumber getEntry(int index) {
	return logEntries.get(index);
    }
    
    /* loggers */
    /**
     * adds the LogEntry to the List if 
     * 	- the amount of data stored in the LogEntry equals the given number
     *  - the description of the LogEntry equals the given one or is 'null'
     */
    @Override
    public void log(LogEntryNumber logEntry) throws LogTypeException {
    	if (Arrays.equals(logEntry.getDataClasses(), getDataClasses()) 
    		&& (getDescription().equals(logEntry.getDescription())
    			|| logEntry.getDescription() == null))
    	    logEntries.add(logEntry);
    	else throw new LogTypeException();
    }
    
    @Override
    public void log(Number num) throws LogTypeException {
	log(new LogEntryNumber(num));
    }

    @Override
    public void log(Number num, String description) throws LogTypeException{
	log(new LogEntryNumber(num, description));
    }

    @Override
    public void log(Number[] nums) throws LogTypeException{
	log(new LogEntryNumber(nums));
    }

    @Override
    public void log(Number[] nums, String description) throws LogTypeException{
	log(new LogEntryNumber(nums, description));
    }
    
    
    /**
     * writes the log list to a file
     */
    @Override
    public void saveLog(String[] coloumnNames) throws IOException {
	
	if (!Files.exists(getLogDirectoryPath())){
	    createLogDirectory();
	}
	LocalDateTime time = LocalDateTime.now();
	String logtime = "" + time.getYear() + time.getMonthValue() 
		+ time.getDayOfMonth() + time.getHour() + time.getMinute();
	String filename = getLogDirectoryPath().toAbsolutePath()
		+ File.separator + "log" + logtime + ".csv";
        writeToFile(filename, coloumnNames);
        System.out.println("successfully saved");
    }
    
    @Override
    public void saveLog() throws IOException {
	String[] coloumnNames = new String[getDataClasses().length];
	for (int i = 0; i < coloumnNames.length; i++) {
	    coloumnNames[i] = "data " + (i+1);
	}
	saveLog(coloumnNames);
    }
    private void writeToFile(String filename, String[] coloumnNames) throws IOException{
	File file = new File(filename);
        file.createNewFile();
        BufferedWriter writer = null;
        try {
            writer = new BufferedWriter(new FileWriter(file));
            for (int i = 0; i < coloumnNames.length; i++) {
        	writer.write(coloumnNames[i] + LogEntryNumber.DATA_SEPARATOR);
	    }
            writer.write("logtime" + LogEntryNumber.DATA_SEPARATOR + "description");
            writer.newLine();
            for (LogEntryNumber logEntryNumber : logEntries) {
    	    	writer.write(logEntryNumber.toString());
    	    	writer.newLine();
            }
	} finally {
	    if (writer != null) {
		writer.close();
	    }
	}
    }
    private void createLogDirectory() {
	Path dir = getLogDirectoryPath();
	try {
	    Files.createDirectory(dir);
	} catch (IOException e) {
	    // TODO Auto-generated catch block
	    e.printStackTrace();
	}
    }
    private Path getLogDirectoryPath() {
	return Paths.get(".\\LogFiles");
    }


}
