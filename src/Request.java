import java.io.*;
import java.net.*;

public class Request {
    private Socket socket;
    private NBR body;

    Request(Socket socket) throws IOException {
        this.socket = socket;
        BufferedReader in = new BufferedReader(new InputStreamReader(socket.getInputStream()));

        this.body = NBR.read(in);
        System.out.println(this.body.toString());
    }

    void reply(String reply) throws IOException {
        DataOutputStream out = new DataOutputStream(socket.getOutputStream());

        out.writeBytes(reply);

        socket.close();
    }

    NBR getNBR() {
        return this.body;
    }
}
