import java.util.Random;
import suppetia.Log.*;

public class LogWindow_example {
    
    public static void main(String[] args) {

        // use the wrapper classes to create an array of the data types you want to log
        Class<Number>[] dataClasses = new Class[] { Integer.class, Integer.class, Double.class};

        // create the log window
        // LogWindow(String[] columnNames, Class[] dataClasses, boolean loggingEnabled, boolean saveOnClose=false)
        LogWindow window = new LogWindow(new String[] {"1" , "2" , "3"}, dataClasses, true);

        try {
            for (int i = 0; i < 10; i++) {
                Random r = new Random();
                window.log(new Number[] {r.nextInt(), r.nextInt(), r.nextDouble()});
            }
            // show analysis of all the entries
            window.analyseAllRows();
        } catch (LogTypeException e) {
            e.printStackTrace();
        }
    }
}
