import java.io.*;
import java.net.Socket;

public class MessageThread implements Runnable{
    Socket socket;
    MessageThread(Socket socket) {
        this.socket = socket;
        new Thread(this).start();
    }

    public void run() {
        try {
            BufferedReader in = new BufferedReader(new InputStreamReader(socket.getInputStream()));
            OutputStream outs = socket.getOutputStream();
            PrintWriter out = new PrintWriter(outs, true);
            String line = in.readLine();
            System.out.println(line);





            out.println(line);

            socket.close();
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }
}
