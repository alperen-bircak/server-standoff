package Game;

import API.Controller;
import API.NBR;
import API.Request;
import Database.Database;
import Database.Store;
import Player.Player;

import java.util.Collection;
import java.util.Objects;
import java.util.Random;
import java.util.UUID;

import Database.*;
import Player.*;

public class EnterGame implements Controller {
    @Override
    /*
    Params: key, player_id
    Returns: game_id
     */
    public void respond(Request req) {
        try {
            Store<Game> store = Database.get("Game");
            Store<Player> playerStore = Database.get("Player");

            Collection<Game> games = store.getAll();

            String key = req.getNBR().get("key");
            boolean found = false;
            Game game = new Game();
            for (Game item : games) {

                if (Objects.equals(item.key, key)) {
                    found = true;
                    game = item;
                    break;
                }
            }
            if (!found) {
                throw new Exception("Game not found");
            }

            if(game.getPlayer2()!=null){
                throw new Exception("Game is full");
            }

            Player player2 = playerStore.get(UUID.fromString(req.getNBR().get("player_id")));
            game.setPlayer2(player2);

            NBR response = new NBR();
            response.put("game_id", game.getId().toString());

            req.reply(response);
        } catch (Exception e) {
            req.error(e.getMessage());
        }
    }
}
