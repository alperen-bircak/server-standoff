import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
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
            String line = in.readLine();

            System.out.println(line);
            if(socket.isClosed()){
                System.out.println("closed");
            }
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }
}
