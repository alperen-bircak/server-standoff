package Game;

import API.Controller;
import API.NBR;
import API.Request;
import Database.Database;
import Database.Store;

import java.util.Collection;
import java.util.UUID;
import Player.*;

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
            Player player1 = game.getPlayer(1);
            Player player2 = game.getPlayer(2);
            if(player1 != null && player2 != null) {
                response.put("name1", game.getPlayer(1).getName());
                response.put("name2", game.getPlayer(2).getName());
            }

            response.put("state", game.getState().name());

            response.put("bullet1", Integer.toString(game.getPlayerBullet(1)));
            response.put("bullet2", Integer.toString(game.getPlayerBullet(2)));
            response.put("action1", game.getPlayerAction(1).name());
            response.put("action2", game.getPlayerAction(2).name());

            req.reply(response);
        } catch (Exception e) {
            req.error(e.getMessage());
        }
    }
}
