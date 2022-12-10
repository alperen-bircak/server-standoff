import java.io.*;
import java.net.*;

public class Request {
    private Socket socket;
    private NBR body;

    private boolean isAlive = false;

    Request(Socket socket){
        this.socket = socket;

        try {
            BufferedReader in = new BufferedReader(new InputStreamReader(socket.getInputStream()));

            this.body = NBR.read(in);

            this.isAlive = true;
        } catch (Exception e) {
            NBR error = new NBR().put("error", e.toString());
            this.reply(error);
        }

    }

    void reply(NBR reply){
        try {
            DataOutputStream out = new DataOutputStream(socket.getOutputStream());

            out.writeBytes(reply.toString());

            socket.close();


            this.isAlive = false;
        } catch (Exception e) {
            System.out.println(e);
        }
    }

    NBR getNBR() {
        return this.body;
    }

    boolean isAlive() {
        return this.isAlive;
    }
}
