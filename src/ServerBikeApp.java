import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.net.ServerSocket;
import java.net.Socket;

public class ServerBikeApp {

    public static void main(String[] args) {
        if(args.length != 1) {
            System.err.println(SrvUtil.INVALID_APP_USAGE);
            System.exit(1);
        }
        int portNumber = Integer.parseInt(args[0]);

        try(ServerSocket serverSocket =
                new ServerSocket(portNumber);
            Socket switchClientSocket = serverSocket.accept();
            PrintWriter out =
                    new PrintWriter(switchClientSocket.getOutputStream(), true);
            BufferedReader in = new BufferedReader(
                    new InputStreamReader(switchClientSocket.getInputStream()));

        ){
            BikeProtocol bikeProtocol = new BikeProtocol();
            while (in.readLine() != null) {
                out.println(bikeProtocol.processAlertSignal());
            }
        } catch (IOException e) {
            System.out.println("Exception caught when trying to listen on port "
                    + portNumber + " or listening for a connection");
            System.out.println(e.getMessage());
        }
    }
}
