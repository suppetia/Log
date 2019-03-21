package suppetia.Log;

import java.io.IOException;
import java.util.ArrayList;

public interface ILog {
   
   int getDataAmount();
   String getDescription();
   
   Class<Number>[] getDataClasses();
   Class<Number> getDataClass(int index) throws NullPointerException;
   
   LogEntryNumber getEntry(int index);
   ArrayList<LogEntryNumber> getLogs();
   
   int numberOfLogs();
   
   void log(LogEntryNumber logEntry) throws LogTypeException;
   void log(Number obj) throws LogTypeException;
   void log(Number obj, String description) throws LogTypeException;
   void log(Number[] objs) throws LogTypeException;
   void log(Number[] objs, String description) throws LogTypeException;
   
   void saveLog(String[] couloumNames) throws IOException;
   void saveLog() throws IOException;
}
