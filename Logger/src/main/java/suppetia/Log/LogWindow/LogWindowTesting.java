package suppetia.Log.LogWindow;

import suppetia.Log.LogTypeException;
import java.util.Random;

public class LogWindowTesting {

    public static void main(String[] args) {
	
	Class<Number>[] dataClasses = new Class[] { Integer.class, Integer.class, Double.class};
	LogWindow window = new LogWindow(new String[] {"1" , "2" , "3", "Description", "Logtime"}, dataClasses);
	window.setVisible(true);
	window.setBounds(0, 0, 200, 500);
	
	//test
	try {
	    for (int i = 0; i < 10; i++) {
		Random r = new Random();
		window.log(new Number[] {r.nextInt(), r.nextInt(), r.nextDouble()});
	    }
	} catch (LogTypeException e) {
	    // TODO Auto-generated catch block
	    e.printStackTrace();
	}

    }

}
