/*
 * a single log entry
 * 2019/02/24
 * Christopher Mertens
 */

package suppetia.Log;

import java.text.DecimalFormat;
import java.time.LocalDateTime;

public class LogEntryNumber {
    private Number[] data; // log data
    private LocalDateTime logtime; // time of logging
    private String description; // (optional) description of the log
    
    public static final String DATA_SEPARATOR = "\t";
    private static final DecimalFormat DECIMAL_FORMAT = new DecimalFormat("0.0000000000");

    /* Constructors */
    
    public LogEntryNumber(final Number data) {
	this.data = new Number[] { data };
	this.logtime = LocalDateTime.now();
	this.description = null;
    }
    public LogEntryNumber(final Number data, String description){
	this(data);
	this.description = description;
    }
    public LogEntryNumber(final Number[] data) {
	this.data = new Number[data.length];
	for (int i = 0; i < data.length; i++) {
	    this.data[i] = data[i];
	}
	this.logtime = LocalDateTime.now();
	this.description = null;
    }
    public LogEntryNumber(final Number[] data, String description) {
	this(data);
	this.description = description;
    }
    
    /* getters */
    public Number[] getData() {
	return data;
    }
    public LocalDateTime getLogtime() {
	return logtime;
    }
    public String getDescription() {
	return description;
    }
    public int getDataAmount() {
	return data.length;
    }
    
    /**
     * get the data object at position index in data array
     * @param index
     * @return object at data[index]
     */
    public Number getDataAt(int index) {
	if (index < data.length && index >= 0) return data[index];
	else return null;
    }
    
    /**
     * returns the class of a data object
     * @param index
     * @return
     */
    public Class<? extends Number> getDataClass(int index) {
	return getDataAt(index).getClass();
    }
    public Class<? extends Number>[] getDataClasses() {
	Class<? extends Number>[] classes = new Class[getDataAmount()];
	for (int i = 0; i < getDataAmount(); i++) {
	    classes[i] = getDataClass(i);
	}
	return classes;
    }

    
    /* compare methods */
    /**
     * tests if the data of two LogEntries is equal
     * @param LogEntryNumber to compare
     * @return
     */
    public boolean equalsData(LogEntryNumber logEntry) {
	for (int i = 0; i < data.length; i++) {
	    if (!this.data[i].equals(logEntry.data[i])) return false;
	}
	return true;
    }
    
    /**
     * overrides equals(Object) method
     * tests if the compared object is a LogEntry 
     * 	and equal in data & description
     * @param Object to compare
     * @return 
     */
    @Override
    public boolean equals(Object obj) {
	if (obj instanceof LogEntryNumber && this.equalsData((LogEntryNumber) obj))
	    if(this.getDescription() == null && ((LogEntryNumber) obj).getDescription() == null) return true;
	    else if (this.getDescription() == null && ((LogEntryNumber) obj).getDescription() != null) return false;
	    else if (this.getDescription().equals(((LogEntryNumber) obj).getDescription())) return true;
	    else return false;
	else
	    return false;
    }
    
    /* toString() method */
    /**
     * overrides the toString() method
     * separates the the attributes with a DATA_SEPERATOR
     */
    @Override
    public String toString() {
	String dataString = "";
	for (int i = 0; i < getDataAmount(); i++) {
	    if (getDataClass(i) == Double.class 
		    || getDataAt(i).getClass() == Float.class) {
		dataString = dataString.concat(DECIMAL_FORMAT.format((getDataAt(i))) + DATA_SEPARATOR);
	    } else {
		dataString = dataString.concat(String.valueOf(getDataAt(i)) + DATA_SEPARATOR);
	    }
	}
	dataString = dataString.concat(logtime.toString());
	if (logtime != null) {
	    dataString = dataString.concat(DATA_SEPARATOR + description);
	}
	return dataString;
    }
}
