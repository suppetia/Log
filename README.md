# Logger

A possibility to store some logdata; provides some analysis tools.



--- work in progress ---



-> eclipse project, build with gradle



# interface ILog

 -> implemented by Log

 - store an ArrayList of LogEntryNumbers of one type (same amount/types of data)

 - provides save method
 
# final class LogAnalysisFunctions

 - provides STATIC methods to analyse the data of ILog
 


# class LogEntryNumber

 - single log

 - stores data, logtime and (optional a description)



# class LogTypeException extends Exception

 - thrown if a LogEntryNumber of wrong type is about to be stored in ILog (Log)
