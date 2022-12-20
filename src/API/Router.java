package API;

import java.util.HashMap;

public class Router {
    HashMap<String, Controller> routeMap;

    public Router() {
        this.routeMap = new HashMap<String, Controller>();
    }

    public void handle(Request req) {
        Controller c = routeMap.get(req.getNBR().get("route"));
        c.respond(req);
    }

    public void addRoute(String route, Controller controller) {
        routeMap.put(route, controller);
    }


}
