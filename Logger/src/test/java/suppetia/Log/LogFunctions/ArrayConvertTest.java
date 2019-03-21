package suppetia.Log.LogFunctions;

import static org.junit.Assert.*;

import org.junit.Test;

import suppetia.Log.LogFunctions.ArrayConvert;

public class ArrayConvertTest {

    @Test
    public void testPrimitive() {
	assertArrayEquals(new float[] {1.1f, 1.2f}, 
		ArrayConvert.toPrimitive(new Float[] {1.1f, 1.2f}), 0.01f);
	assertArrayEquals(new double[] {1.1, 1.2}, 
		ArrayConvert.toPrimitive(new Double[] {1.1, 1.2}), 0.01);
	assertArrayEquals(new short[] {1, 2}, 
		ArrayConvert.toPrimitive(new Short[] {1, 2}));
	assertArrayEquals(new long[] {1, 2}, 
		ArrayConvert.toPrimitive(new Long[] {1l, 2l}));
	assertArrayEquals(new int[] {1, 2}, 
		ArrayConvert.toPrimitive(new Integer[] {1, 2}));
	assertArrayEquals(new short[] {1, 2}, 
		ArrayConvert.toPrimitive(new Short[] {1, 2}));
	assertArrayEquals(new byte[] {1, 2}, 
		ArrayConvert.toPrimitive(new Byte[] {1, 2}));
	assertArrayEquals(new boolean[] {true, false}, 
		ArrayConvert.toPrimitive(new Boolean[] {true, false}));
	assertArrayEquals(new char[] {1, 2}, 
		ArrayConvert.toPrimitive(new Character[] {1, 2}));
	
    }

}
