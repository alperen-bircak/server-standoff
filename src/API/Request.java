package API;

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
            this.error(e.toString());
        }

    }

    public void reply(NBR reply){
        try {
            DataOutputStream out = new DataOutputStream(socket.getOutputStream());
            System.out.println("Response{");
            System.out.println(reply.toString());
            System.out.println('}');
            out.writeBytes(reply.toString());

            socket.close();

            this.isAlive = false;
        } catch (Exception e) {
            System.out.println(e);
        }
    }

    public void error(String error) {
        NBR errorNBR = new NBR().put("error", error);
        this.reply(errorNBR);
    }

    public NBR getNBR() {
        return this.body;
    }

    boolean isAlive() {
        return this.isAlive;
    }
}
