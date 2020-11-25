import java.net.*;
import java.io.*;

public class Server {
	public static void main(String[] args) throws IOException {
		ServerSocket serverSocket = null;
		try {
			serverSocket = new ServerSocket(5555);
		} catch (IOException e) {
			System.err.println("Could not listen on port: 5555.");
			System.exit(1);
		}

		Socket clientSocket = null;
		try {
			clientSocket = serverSocket.accept();
		} catch (IOException e) {
			System.err.println("Accept failed.");
			System.exit(1);
		}

		InputStream input = clientSocket.getInputStream();
		BufferedReader in = new BufferedReader(new InputStreamReader(input));
		 
		String inputLine;// , outputLine;
		double recieved_val;
		
		while ((inputLine = in.readLine()) != null) {
			recieved_val = Double.parseDouble(inputLine);
			System.out.println("Value recieved : " + recieved_val);
			System.out.println("Linearized value is : " + round(recieved_val, 2));
		}

		in.close();
		clientSocket.close();
		serverSocket.close();
	}

	public static double round(double value, int places) {
		if (places < 0)
			throw new IllegalArgumentException();

		long factor = (long) Math.pow(10, places);
		value = value * factor;
		long tmp = Math.round(value);
		return (double) tmp / factor;
	}
}
