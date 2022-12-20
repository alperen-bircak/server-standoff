package API;

import java.net.*;


public class RequestThread implements Runnable{
    Request req;
    Router router;

    public RequestThread(Router router, Socket s) {

        this.req = new Request(s);

        this.router = router;


        new Thread(this).start();
    }

    @Override
    public void run() {

        if(req.isAlive()) {

            router.handle(req);
        }
    }
}
