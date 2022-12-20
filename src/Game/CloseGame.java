package Game;

import API.Controller;
import API.NBR;
import API.Request;
import Database.Database;
import Database.Store;
import Player.Player;

import java.util.Collection;
import java.util.Objects;
import java.util.UUID;

public class CloseGame implements Controller {
    @Override
    /*
    Params: game_id
    Returns: success
     */
    public void respond(Request req) {
        try {
            Store<Game> store = Database.get("Game");

            store.remove(UUID.fromString(req.getNBR().get("game_id")));

            req.reply((new NBR()).put("success", "true"));
        } catch (Exception e) {
            req.error(e.getMessage());
        }
    }
}
