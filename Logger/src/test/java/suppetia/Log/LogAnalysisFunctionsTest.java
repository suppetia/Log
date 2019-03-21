package suppetia.Log;

import static org.junit.Assert.*;

import org.junit.Test;

import suppetia.Log.ILog;
import suppetia.Log.Log;
import suppetia.Log.LogTypeException;
import suppetia.Log.Functions.LogAnalysisFunctions;

public class LogAnalysisFunctionsTest {

    private static ILog createTestLog() {
	ILog log = new Log(new Class[] {Integer.class, Integer.class, Integer.class}, 
		"test description");
	try {
	    log.log(new Integer[] {0, 1, 2});
	    log.log(new Integer[] {1, 2, 3});
	    log.log(new Integer[] {2, 3, 4});
	} catch (LogTypeException e) {
	    e.printStackTrace();
	}
	return log;
    }
    
    
    @Test
    public void testGetSum() {
	ILog log = createTestLog();
	
	assertEquals(3, LogAnalysisFunctions.getSum(log, 0), 0.001);
	assertEquals(1, LogAnalysisFunctions.getSum(log, 0, 1, 1), 0.001);
	assertEquals(0, LogAnalysisFunctions.getSum(log, 0, 0, 1), 0.001);
    }
    
    @Test
    public void testGetAverage() {
	ILog log = createTestLog();
	
	assertEquals(2, LogAnalysisFunctions.getAverage(log, 1), 0.01);
	assertEquals(2.5, LogAnalysisFunctions.getAverage(log, 1, 1, 2), 0.01);
    }

}
