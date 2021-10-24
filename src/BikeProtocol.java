import java.io.*;
import java.net.*;

public class BikeProtocol {

    public String processAlertSignal(BufferedReader stdIn) {
        // TODO: I will have to figure out
        // how to communicate with the android app from here.
        // The android app will return the timeinmillis when it received the alert signal
        // or in this context, when server tried to connect to the android app.
        // For now, I am just simulating the work-flow.
        //return System.currentTimeMillis() + "";
        String hostName = "192.168.232.2";
        //hostName = "EMULATOR30X2X5X0";
        hostName = "fe80::ff:fe44:5566";
        //hostName = "fec0::ff:fe44:5566";
        //hostName = "fec0::b899:b895:ef38:263b";
        int portNumber = 7777;
        try (
                Socket androidBikeAppSocket = new Socket(hostName, portNumber);
                PrintWriter out = new PrintWriter(androidBikeAppSocket.getOutputStream(), true);
                BufferedReader in = new BufferedReader(new InputStreamReader(androidBikeAppSocket.getInputStream()));
        ) {
            System.out.println("Connected to Android App Server.");
            out.println("SS Alert! Alert! Alert!");
            String output = in.readLine();
            System.out.println("App Response (t2) = " + output);
            return output;
        } catch (UnknownHostException e) {
            System.err.println("Don't know about the host " + hostName);
            System.exit(1);
        } catch (IOException e) {
            System.err.println("Couldn't get I/O for the connection to " + hostName);
            System.exit(1);
        }
        return "0";
    }
}
