import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.*;

import static java.net.InetAddress.getLocalHost;


public class Main {
    static int port = 8080;

    public static void main(String[] args) {
        Router router = new Router();
        router.addRoute("echo", new Echo());


        try {
            InetAddress localhost = getLocalHost();
            System.out.println(localhost.getHostAddress());


            while (true) {
                try (ServerSocket socket = new ServerSocket(port)) {
                    Socket s = socket.accept();
                    new RequestThread(router, s);
                }

            }


        } catch (IOException e) {
            System.out.println(e);
        }
    }
}