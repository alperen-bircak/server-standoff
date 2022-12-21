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
    enum Win{
        ME,
        OTHER,
        TIE
    }
    private int otherPlayer(int player) {
        return player == 1 ? 2 : 1;
    }


    @Override
    /*
    params: player_id, game_id, action, bullet_change
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
            game.setState(Game.GameState.WAIT);
            game.setPlayerBullet(player, game.getPlayerBullet(player) + Integer.parseInt(req.getNBR().get("bullet_change")));
            game.setPlayerRound(player, game.getRound());

        } catch (Exception e) {
            req.error(e.getMessage());
        }
    }
}
