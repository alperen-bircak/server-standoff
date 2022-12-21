package Game;

import API.Controller;
import API.NBR;
import API.Request;
import Database.Database;
import Database.Store;

import java.util.Collection;
import java.util.HashMap;
import java.util.UUID;
import Player.*;

public class GetState implements Controller {
    HashMap<Game.Action, Integer> powers;

    GetState() {
        powers = new HashMap<>();
        powers.put(Game.Action.RELOAD, 0);
        powers.put(Game.Action.PISTOL, 1);
        powers.put(Game.Action.RIFLE, 2);
        powers.put(Game.Action.BAZOOKA, 3);
    }
    private Game.GameState calculateState(Game game, Game.Action action1, Game.Action action2) {
        if(action1 == Game.Action.PROTECT || action2 == Game.Action.PROTECT) {
            return Game.GameState.CONT;
        }
        if(powers.get(action1) > powers.get(action2)) {
            return Game.GameState.WON1;
        }
        if(powers.get(action2) > powers.get(action1)) {
            return Game.GameState.WON2;
        }
        return  Game.GameState.CONT;

    }

    @Override
    /*
    Params: game_id
    Returns: state, name1, name2, bullet1, bullet2, action1, action2
     */

    public void respond(Request req) {
        try {
            Store<Game> store = Database.get("Game");


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

            if(game.getPlayerRound(1) == game.getRound() && game.getPlayerRound(2) == game.getRound()) {
                game.setState(calculateState(game, game.getPlayerAction(1), game.getPlayerAction(2)));
            }

            req.reply(response);
        } catch (Exception e) {
            req.error(e.getMessage());
        }
    }
}
