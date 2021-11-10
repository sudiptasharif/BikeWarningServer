import java.io.*;
import java.net.*;

public class ServerToAndroidProtocol {
    private Socket androidClientSocket;
    private PrintWriter out;
    private BufferedReader in;

    public ServerToAndroidProtocol() {

    }

    public ServerToAndroidProtocol(String androidHostIP, int androidHostPortNumber) {
        try {
            Socket androidClientSocket = new Socket(androidHostIP, androidHostPortNumber);
            PrintWriter out = new PrintWriter(androidClientSocket.getOutputStream(), true);
            BufferedReader in = new BufferedReader(new InputStreamReader(androidClientSocket.getInputStream()));
        } catch (UnknownHostException e) {
            System.err.println("Unknown Host " + androidHostIP);
            System.err.println(e.getMessage());
            System.exit(1);
        }
        catch (IOException e) {
            System.err.println("Unable to connect to  " + androidHostIP);
            System.err.println(e.getMessage());
            System.exit(1);
        }
    }

    public String sendAlertSignalToAndorid() {
        String output = "0";
        try{
            out.println("Alert! Alert! Alert!");
            out.flush();
            output = in.readLine();
            System.out.println("App Response (t2) = " + output);
        } catch (IOException e) {
            System.err.println("Socket I/O exception: " + e.getMessage());
            System.exit(1);
        }
        return output;
    }

    public String sendDummySignal() {
        return "0";
    }

    public void closeSocketConnection() {
        try {
            androidClientSocket.close();
        } catch (IOException e) {
            System.err.println("Socket I/O exception: " + e.getMessage());
            System.exit(1);
        }
    }
}
