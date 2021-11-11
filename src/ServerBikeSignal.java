import org.w3c.dom.ls.LSOutput;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.net.ServerSocket;
import java.net.Socket;

public class ServerBikeSignal {

    public static void main(String[] args) {
        if(args.length != 1) {
            System.err.println(SrvUtil.INVALID_APP_USAGE);
            System.exit(1);
        }
        System.out.println("\nServer Bike Signal App\n");
        // ServerBikeSignal will listen to this port number
        // for a SwitchBikeSignal client to make connection
        int portNumber = Integer.parseInt(args[0]);

        // System.out.println("Connecting to the android app...");
        // ServerToAndroidProtocol s2aProtocol = new ServerToAndroidProtocol();
        //ServerToAndroidProtocol s2aProtocol = new ServerToAndroidProtocol(SrvUtil.ANDROID_IP, SrvUtil.ANDROID_PORT_NUMBER);
        if (true/*s2aProtocol != null*/) {
            // System.out.println("Connected.");
            //System.out.println("Not Connected to Android app.");
            System.out.println("Listening for Switch Client Connection...");
            try(ServerSocket serverSocket =
                        new ServerSocket(portNumber);
                Socket switchClientSocket = serverSocket.accept();
                PrintWriter out =
                        new PrintWriter(switchClientSocket.getOutputStream(), true);
                BufferedReader in = new BufferedReader(
                        new InputStreamReader(switchClientSocket.getInputStream()));
            ){
                System.out.println("Connected to a client");
                while (in.readLine() != null) {
                    // out.println will send the response from the android app back to the switch client
                    //out.println(s2aProtocol.sendAlertSignalToAndorid());
                    out.println(System.currentTimeMillis());
                }
                //s2aProtocol.closeSocketConnection();
            } catch (IOException e) {
                System.out.println("Exception caught when trying to listen on port "
                        + portNumber + " or listening for a connection");
                System.out.println(e.getMessage());
            }
            System.out.println("Closing ServerBikeSignal Signal Server.");
        } else {
            System.out.println("Unable to Connect");
            System.exit(1);
        }
    }
}
