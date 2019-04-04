# Logger

A possibility to store some log data; provides some analysis tools.

--- work in progress ---

## suppetia.Log

### public interface ILog

 - store an ArrayList of LogEntryNumbers of one type (same amount/types of data)
 - provides save method
 

### public class Log

 -> implements ILog


### public class LogEntryNumber

 - single log entry
 - stores data, log time (and optional a description)



### public class LogTypeException extends Exception

 - thrown if a LogEntryNumber of wrong type is about to be stored in ILog (Log)
 

 
## suppetia.Log.LogFunctions
 
### public final class LogAnalysisFunctions

 - provides static methods to analyze the data of ILog
     - getAverage
     - getSum
     - getMax
     - getMin
     
### public final class ArrayConvert

 - provides static method to convert wrapper-class arrays 
    into their primitive equivalent
     - toPrimitive
 

## suppetia.Log.LogWindow

 -> possible use of ILog to show the data in a window
 
### public class LogWindow
 -> extends JFrame

 - uses JTable to store a log (from type ILog)

### (package-private) class LogDataTableModel
 -> extends DefaultTableModel
 
 - columns of the type of the log
 
### (package-private) class LogDataTableModel
 -> extends DefaultTableModel
 
 - table model for results of the analysis of log entries 
 
### public class LogWindowTesting

 --- just for testing purpose ---
