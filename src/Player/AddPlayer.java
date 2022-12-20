package Player;

import API.Controller;
import API.NBR;
import API.Request;
import Database.Database;
import Database.Store;
import Database.StoreException;

public class AddPlayer implements Controller {

    @Override
    /*
    Params: name
    Returns: id, name
     */
    public void respond(Request req) {
        try {
            Store<Player> store = Database.get("Player");

            Player player = new Player();
            player.setName(req.getNBR().get("name"));

            store.add(player);
            NBR response = new NBR();
            response.put("id", player.getId().toString());
            response.put("name", player.getName());

            req.reply(response);
        } catch (StoreException e) {
            req.error(e.toString());
        }

    }
}
