package API;

import java.util.HashMap;

public class Router {
    HashMap<String, Controller> routeMap;

    public Router() {
        this.routeMap = new HashMap<String, Controller>();
    }

    public void handle(Request req) {
        System.out.println(req.getNBR().toString());
        Controller c = routeMap.get(req.getNBR().get("route"));
        if(c==null) {
            req.error("Route not found.");
            return;
        }
        c.respond(req);
    }

    public void addRoute(String route, Controller controller) {
        routeMap.put(route, controller);
    }


}
