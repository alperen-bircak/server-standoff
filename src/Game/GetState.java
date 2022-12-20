package Game;

import API.Controller;
import API.NBR;
import API.Request;
import Database.Database;
import Database.Store;

import java.util.Collection;
import java.util.UUID;

public class GetState implements Controller {

    @Override
    /*
    Params: game_id
    Returns: state, name1, name2, bullet1, bullet2, action1, action2
     */
    public void respond(Request req) {
        try {
            Store<Game> store = Database.get("Game");

            Collection<Game> games = store.getAll();

            Game game = store.get(UUID.fromString(req.getNBR().get("game_id")));

            NBR response = new NBR();

            response.put("state", game.getState().name());
            response.put("name1", game.getPlayer1().getName());
            response.put("name2", game.getPlayer2().getName());
            response.put("bullet1", Integer.toString(game.getPlayer1bullet()));
            response.put("bullet2", Integer.toString(game.getPlayer2bullet()));
            response.put("action1", game.getPlayer1action().name());
            response.put("action2", game.getPlayer2action().name());

            req.reply(response);
        } catch (Exception e) {
            req.error(e.getMessage());
        }
    }
}
