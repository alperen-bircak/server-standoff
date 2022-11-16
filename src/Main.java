import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.*;

import static java.net.InetAddress.getLocalHost;


public class Main {
    static int port = 8080;

    public static void main(String[] args) {


        try {
            InetAddress localhost = getLocalHost();
            System.out.println(localhost.getHostAddress());

            ServerSocket socket = new ServerSocket(port);

            Socket s = socket.accept();

            System.out.println("connected");

            BufferedReader in = new BufferedReader(new InputStreamReader(s.getInputStream()));

            while(true) {
                String line = in.readLine();
                System.out.println(line);
            }

        } catch (IOException e) {
            System.out.println("No host found");
        }


    }
}