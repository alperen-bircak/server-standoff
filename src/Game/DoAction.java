package Game;

import API.Controller;
import API.NBR;
import API.Request;
import Database.Database;
import Database.Store;
import Game.*;
import Player.Player;

import java.util.Collection;
import java.util.Objects;
import java.util.UUID;

public class DoAction implements Controller {
    private int otherPlayer(int player) {
        return player == 1 ? 2 : 1;
    }
    @Override
    /*
    params: player_id, game_id, action
    returns: success
     */

    public void respond(Request req) {
        try {
            Store<Game> store = Database.get("Game");
            Game game = store.get(UUID.fromString(req.getNBR().get("game_id")));

            int player = 0;
            if(game.getPlayer(1).getId() == UUID.fromString(req.getNBR().get("player_id"))) {
                player = 1;
            } else if (game.getPlayer(2).getId() == UUID.fromString(req.getNBR().get("player_id"))) {
                player = 2;
            } else {
                throw new Exception("invalid player");
            }

            game.setPlayerAction(player, Game.Action.valueOf(req.getNBR().get("action")));

            //If you are the second one to make an action
            if (game.getPlayerRound(otherPlayer(player)) == game.getRound()) {

            } else {
                game.setState(Game.GameState.WAIT);
            }
        } catch (Exception e) {
            req.error(e.getMessage());
        }
    }
}
