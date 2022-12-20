package Player;

import API.Controller;
import API.NBR;
import API.Request;
import Database.Database;
import Database.Store;
import Database.StoreException;

import java.util.UUID;

public class GetPlayer implements Controller {

    @Override
    /*
    Params: id
    Returns: id, name
     */
    public void respond(Request req) {
        try {
            Store<Player> store = Database.get("Player");

            UUID id = UUID.fromString(req.getNBR().get("id"));

            Player player = store.get(id);
            NBR response = new NBR();
            response.put("id", player.getId().toString());
            response.put("name", player.getName());

            req.reply(response);
        } catch (StoreException e) {
            req.error(e.toString());
        }

    }
}
