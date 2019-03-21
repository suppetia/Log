package suppetia.Log;

import static org.junit.Assert.*;

import org.junit.Test;

import suppetia.Log.LogEntryNumber;

public class LogEntryNumberTest {

    @Test
    public void testGetData() {
	LogEntryNumber log = new LogEntryNumber(123);
	assertArrayEquals(new Integer[] {123}, log.getData());
	assertNotEquals(0, log.getData());
    }
    
    @Test
    public void testGetDataAt() {
	LogEntryNumber log = new LogEntryNumber(new Integer[] {0,1,2,3,4,5,6,7,8,9});
	assertEquals(3, log.getDataAt(3));
	assertNotEquals(3, log.getDataAt(4));
    }
    
    @Test
    public void testGetDateAmount() {
	LogEntryNumber log = new LogEntryNumber(new Integer[] {0,1,2,3,4,5,6,7,8,9});
	assertEquals(10, log.getDataAmount());
	assertNotEquals(11, log.getDataAmount());
    }
    
    @Test
    public void testGetDescription() {
	LogEntryNumber log = new LogEntryNumber(1);
	assertEquals(null, log.getDescription());
	assertNotEquals("test data",log.getDescription());
	
	LogEntryNumber log2 = new LogEntryNumber(123, "test data");
	assertTrue(log2.getDescription().equals("test data"));
	assertFalse(log2.getDescription().equals("not test data"));
    }
    
    @Test
    public void testGetDataClass() {
	LogEntryNumber log = new LogEntryNumber(new Number[] { Integer.valueOf(5), Double.valueOf(1.2), Float.valueOf(1.3f)});
	assertEquals(Integer.class, log.getDataClass(0));
	assertEquals(Double.class, log.getDataClass(1));
	assertEquals(Float.class, log.getDataClass(2));
    }
    
    @Test
    public void testDataEquals() {
	LogEntryNumber log = new LogEntryNumber(1);
	assertTrue(log.equalsData(new LogEntryNumber(1)));
	assertFalse(log.equalsData(new LogEntryNumber(0)));
	
	LogEntryNumber log2 = new LogEntryNumber(new Double[] { 1.1, 1.2, 1.3});
	assertTrue(log2.equalsData(new LogEntryNumber(new Double[] { 1.1, 1.2, 1.3})));
	assertFalse(log2.equalsData(new LogEntryNumber(new Integer[] {1,2,3})));
    }
    
    @Test
    public void testEquals() {
	
	LogEntryNumber log = new LogEntryNumber(1);
	assertTrue(log.equals(new LogEntryNumber(1)));
	assertFalse(log.equals(new LogEntryNumber(2)));
	assertFalse(log.equals(new LogEntryNumber(1, "test")));
	assertFalse(log.equals(15));
	
	
	LogEntryNumber log2 = new LogEntryNumber(1, "test");
	assertTrue(log2.equals(new LogEntryNumber(1, "test")));
	assertFalse(log2.equals(new LogEntryNumber(2, "test")));
	assertFalse(log2.equals(new LogEntryNumber(1)));
	assertFalse(log2.equals("test"));
    }
    
    /*
    @Test
    public void testToString() {
	
	LogEntryNumber log = new LogEntryNumber(1);
	assertEquals(expected, actual);
    }
    */
}
