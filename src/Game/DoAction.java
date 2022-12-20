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

public class DoAction implements Controller {
    @Override
    /*
    params: player_id, game_id, action
    returns: success
     */
    public void respond(Request req) {
        try {
            Store<Game> store = Database.get("Game");

        } catch (Exception e) {
            req.error(e.getMessage());
        }
    }
}
