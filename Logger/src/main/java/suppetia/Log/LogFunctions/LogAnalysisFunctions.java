package suppetia.Log.LogFunctions;

import suppetia.Log.ILog;

public final class LogAnalysisFunctions {

    public static double getSum(ILog log, int index, int offset, int length) throws NullPointerException {
	double sum = 0;
	if (index < log.getDataAmount() && index >= 0
		&& offset < log.numberOfLogs() && offset >= 0
		&& offset + length - 1 < log.numberOfLogs() && length > 0) {
	    for(int i = offset; i < offset + length; i++) {
		sum += log.getEntry(i).getDataAt(index).doubleValue();
	    }
	    return sum;
	}
	throw new NullPointerException();
    }
    public static double getSum(ILog log, int index) throws NullPointerException {
        return getSum(log, index, 0, log.numberOfLogs());
    }
    public static double getSum(ILog log, int index, int[] rows) throws NullPointerException {
	double sum = 0;
	for (int i = 0; i < rows.length; i++) {
	    sum += log.getEntry(rows[i]).getDataAt(index).doubleValue();
	}
	return sum;
    }

    
    public static double getAverage(ILog log, int index, int offset, int length) throws NullPointerException {
	if (index < log.getDataAmount() && index >= 0
		&& offset < log.numberOfLogs() && offset >= 0
		&& offset + length - 1 < log.numberOfLogs() && length > 0) {
	    return getSum(log, index, offset, length) / length;
	}
	throw new NullPointerException();
    }
    public static double getAverage(ILog log, int index) throws NullPointerException {
	return getAverage(log, index, 0, log.numberOfLogs());
    }
    public static double getAverage(ILog log, int index, int[] rows) throws NullPointerException {
	return getSum(log, index, rows) / rows.length;
    }
    
    public static double getMax(ILog log, int index, int offset, int length) throws NullPointerException {
	double max = Double.NEGATIVE_INFINITY;
	if (index < log.getDataAmount() && index >= 0
		&& offset < log.numberOfLogs() && offset >= 0
		&& offset + length - 1 < log.numberOfLogs() && length > 0) {
	    for(int i = offset; i < offset + length; i++) {
		double currentValue = log.getEntry(i).getDataAt(index).doubleValue();
		if (max <= currentValue) max = currentValue;
	    }
	    return max;
	}
	throw new NullPointerException();
    }
    public static double getMax(ILog log, int index) throws NullPointerException {
	return getMax(log, index, 0, log.numberOfLogs());
    }
    public static double getMax(ILog log, int index, int[] rows) throws NullPointerException{
	double max = Double.NEGATIVE_INFINITY;
	for (int i = 0; i < rows.length; i++) {
	    double currentValue = log.getEntry(rows[i]).getDataAt(index).doubleValue();
	    if (max <= currentValue) max = currentValue;
	}
	return max;
    }
    
    public static double getMin(ILog log, int index, int offset, int length) throws NullPointerException {
	double min = Double.POSITIVE_INFINITY;
	if (index < log.getDataAmount() && index >= 0
		&& offset < log.numberOfLogs() && offset >= 0
		&& offset + length - 1 < log.numberOfLogs() && length > 0) {
	    for(int i = offset; i < offset + length; i++) {
		double currentValue = log.getEntry(i).getDataAt(index).doubleValue();
		if (min >= currentValue) min = currentValue;
	    }
	    return min;
	}
	throw new NullPointerException();
    }
    public static double getMin(ILog log, int index) throws NullPointerException {
	return getMin(log, index, 0, log.numberOfLogs());
    }
    public static double getMin(ILog log, int index, int[] rows) throws NullPointerException{
	double min = Double.POSITIVE_INFINITY;
	for (int i = 0; i < rows.length; i++) {
	    double currentValue = log.getEntry(rows[i]).getDataAt(index).doubleValue();
	    if (min >= currentValue) min = currentValue;
	}
	return min;
    }

}
