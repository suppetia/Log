/*
 * converts Wrapperclass arrays into arrays of the primitive type
 * 2019/03/03
 * Christopher Mertens
 */

package suppetia.Log.Functions;

public class ArrayConvert {

    public static double[] toPrimitive(Double[] array) {
	double[] convertedArray = new double[array.length];
	for (int i = 0; i < array.length; i++) {
	    convertedArray[i] = array[i].doubleValue();
	}
	return convertedArray;
    }
    
    public static int[] toPrimitive(Integer[] array) {
	int[] convertedArray = new int[array.length];
	for (int i = 0; i < array.length; i++) {
	    convertedArray[i] = array[i].intValue();
	}
	return convertedArray;
    }
    
    public static long[] toPrimitive(Long[] array) {
	long[] convertedArray = new long[array.length];
	for (int i = 0; i < array.length; i++) {
	    convertedArray[i] = array[i].longValue();
	}
	return convertedArray;
    }
    
    public static short[] toPrimitive(Short[] array) {
	short[] convertedArray = new short[array.length];
	for (int i = 0; i < array.length; i++) {
	    convertedArray[i] = array[i].shortValue();
	}
	return convertedArray;
    }
    
    public static byte[] toPrimitive(Byte[] array) {
	byte[] convertedArray = new byte[array.length];
	for (int i = 0; i < array.length; i++) {
	    convertedArray[i] = array[i].byteValue();
	}
	return convertedArray;
    }
    
    public static float[] toPrimitive(Float[] array) {
	float[] convertedArray = new float[array.length];
	for (int i = 0; i < array.length; i++) {
	    convertedArray[i] = array[i].floatValue();
	}
	return convertedArray;
    }
    
    public static boolean[] toPrimitive(Boolean[] array) {
	boolean[] convertedArray = new boolean[array.length];
	for (int i = 0; i < array.length; i++) {
	    convertedArray[i] = array[i].booleanValue();
	}
	return convertedArray;
    }
    
    public static char[] toPrimitive(Character[] array) {
	char[] convertedArray = new char[array.length];
	for (int i = 0; i < array.length; i++) {
	    convertedArray[i] = array[i].charValue();
	}
	return convertedArray;
    }
}
