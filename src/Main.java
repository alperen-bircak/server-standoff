import API.Echo;
import API.RequestThread;
import API.Router;
import Database.Database;
import Database.Store;
import Database.StoreException;
import Game.*;
import Player.*;

import java.io.IOException;
import java.net.*;

import static java.net.InetAddress.getLocalHost;


public class Main {
    static int port = 8080;

    public static void main(String[] args) throws StoreException {
        Database.add("Player", new Store<Player>());
        Database.add("Game", new Store<Game>());

        Router router = new Router();
        router.addRoute("echo", new Echo());

        router.addRoute("player.add", new AddPlayer());
        router.addRoute("player.get", new GetPlayer());
        router.addRoute("game.start", new StartGame());
        router.addRoute("game.enter", new EnterGame());
        router.addRoute("game.state", new GetState());
        router.addRoute("game.close", new CloseGame());

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