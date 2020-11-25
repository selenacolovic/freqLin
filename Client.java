import java.io.*;
import java.net.*;
import java.text.DecimalFormat;
import java.util.concurrent.TimeUnit;

public class Client {
    public static void main(String[] args) throws IOException {

        Socket kkSocket = null;
        PrintWriter out = null;

        try {
            kkSocket = new Socket("127.0.0.1", 5555);
            out = new PrintWriter(kkSocket.getOutputStream(), true);
        } catch (UnknownHostException e) {
            System.err.println("Don't know about host: taranis.");
            System.exit(1);
        } catch (IOException e) {
            System.err.println("Couldn't get I/O for the connection to: taranis.");
            System.exit(1);
        }

        BufferedReader stdIn = new BufferedReader(new InputStreamReader(System.in));
        double toServer= 0.0000;
        String fromUser;
        int frequency = 0;
        int n = 0;
        MeasureFrequency measure = new MeasureFrequency();
        DecimalFormat df = new DecimalFormat("#.###");


        System.out.println("Start the program: ");

	    while ((fromUser= stdIn.readLine()) != null) {
	        switch(fromUser) 
	        { 
	            case "Start": 
	                System.out.println("Enter Frequency");
	                break; 
	            case "Stop": 
	                System.out.println("Program Stopped"); 
	                break;  
	            default: 
	                System.out.println("Frequency resieved " + fromUser);
	                frequency = Integer.parseInt(fromUser);
	             	do { 
	             		n++;
	             		toServer = measure.measureSample(frequency, n);
	             		System.out.println("Sample["+n+"]= "  +df.format(toServer) );
	             		out.println(Double.toString(toServer));
	             		try {
							TimeUnit.SECONDS.sleep(n/frequency);
						} catch (InterruptedException e) {
							// TODO Auto-generated catch block
							e.printStackTrace();
						}
	             	} while (fromUser != "Stop");
	        } 
	    }

        out.close();
        stdIn.close();
        kkSocket.close();
    }
}