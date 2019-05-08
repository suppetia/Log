# Logger

A possibility to store some log data; provides some analysis tools.

## suppetia.Log
### public interface ILog

    - store an ArrayList of LogEntryNumbers of one type (same amount/types of data)
    - provides save method

### public class Log

-> implements ILog

```java
// Log(Class<Number>[] dataClasses)
ILog log = new Log(new Class[]{Double.class, Integer.class, Float.class});

// use log.log(...) to add log entries from type LogEntryNumber
log.log(new Number[]{12.34, 42, 13.5f}); // throws LogDataException if the data classes mismatch
log.log(new Number[]{12.34, 42, 13.5f}, "random stuff");
```

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

    - provides static method to convert wrapper-class arrays into their primitive equivalent
        - toPrimitive

## suppetia.Log.LogWindow
-> possible use of ILog to show the data in a window

### public class LogWindow
-> extends JFrame

    - use to display ILog

    - possible use:

```java
// LogWindow(String[] columnNames, ILog log, boolean loggingEnabled, boolean saveOnClose)
LogWindow lowWindow = new LogWindow(new String[]{"temperature", "humidity", "brightness"},
			log, true, true);
						
// LogWindow(String[] columnNames, Class<Number>[] dataClasses, boolean loggingEnabled)
LogWindow logWindow2 = new LogWindow(new String[]{"temperature", "humidity", "brightness"},
			new Class[]{Double.class, Double.class, Double.class}, true);
						
// use lowWindow.log(...) to add new log entries
logWindow2.log(new Number[]{22.3, 47.98, 0.1324}); // throws LogDataException if the data classes mismatch
```
    - mark rows in the window to analyze its logging data
    
### (package-private) class LogDataTableModel
-> extends DefaultTableModel

    - columns of the type of the log

### (package-private) class LogDataTableModel

-> extends DefaultTableModel

    - table model for results of the analysis of log entries
