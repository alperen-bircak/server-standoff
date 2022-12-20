package Game;

import API.Controller;
import API.NBR;
import API.Request;
import Database.*;

import java.util.Collection;
import java.util.Objects;
import java.util.Random;
import java.util.UUID;

import Player.*;

public class StartGame implements Controller {
    private static final char[] chars = {'A', 'B', 'C', 'D', 'E', 'F', 'G', 'H', 'I', 'K', 'L', 'M', 'O', 'P', 'S', 'T', 'V', 'Z'};
    @Override
    /*
    Params: player_id
    Returns: game_id, key
     */
    public void respond(Request req) {
        try {
            Store<Game> store = Database.get("Game");
            Store<Player> playerStore = Database.get("Player");

            Collection<Game> games = store.getAll();

            String key = "";
            while(true) {
                Random r = new Random();
                for(int i = 0; i < 4; i++) {
                    key += chars[r.nextInt(chars.length)];
                }
                boolean dup = false;
                for(Game item: games) {
                    if (Objects.equals(item.key, key)) {
                        dup = true;
                        break;
                    }
                }
                if(!dup) {
                    break;
                }
            }

            Game game = new Game();
            Player player1 = playerStore.get(UUID.fromString(req.getNBR().get("player_id")));
            game.setPlayer1(player1);
            game.setKey(key);
            store.add(game);

            NBR response = new NBR();
            response.put("key", key);
            response.put("game_id", game.getId().toString());

            req.reply(response);
        } catch (StoreException e) {
            req.error(e.toString());
        }

    }
}
