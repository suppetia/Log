package suppetia.Log.LogWindow;

import suppetia.Log.LogTypeException;

public class LogWindowTesting {

    public static void main(String[] args) {
	
	Class<Number>[] dataClasses = new Class[] { Integer.class, Integer.class, Double.class};
	LogWindow window = new LogWindow(new String[] {"1" , "2" , "3", "Description", "Logtime"}, dataClasses);
	window.setVisible(true);
	window.setBounds(0, 0, 200, 500);
	
	//test
	try {
	    for (int i = 0; i < 100000; i++) {
		window.log(new Number[] {i, i * 5, i / 3.0});
	    }
	} catch (LogTypeException e) {
	    // TODO Auto-generated catch block
	    e.printStackTrace();
	}

    }

}
