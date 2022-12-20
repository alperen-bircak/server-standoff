package Game;

import Database.Storable;
import Player.*;


public class Game extends Storable {
    int round = 0;
    String key = "";
    Player player1;
    Player player2;
    int player1bullet = 0;
    int player2bullet = 0;

    enum Action {
        NONE,
        RELOAD,
        PROTECT,
        PISTOL,
        RIFLE,
        BAZOOKA
    }

    Action player1action = Action.NONE;
    Action player2action = Action.NONE;

    enum GameState {
        CONT,
        WAIT,
        WON1,
        WON2
    }

    GameState state = GameState.WAIT;

    int player1round = -1;
    int player2round = -1;

    public Game clone() throws CloneNotSupportedException {
        return (Game) super.clone();
    }

    public GameState getState() {
        return state;
    }

    public void setState(GameState state) {
        this.state = state;
    }

    public int getRound() {
        return round;
    }

    public void setRound(int round) {
        this.round = round;
    }

    public String getKey() {
        return key;
    }

    public void setKey(String key) {
        this.key = key;
    }

    public Player getPlayer(int no) {
        if(no == 1) {
            return player1;
        } else {
            return player2;
        }
    }

    public void setPlayer(int no, Player player) {
        if(no == 1) {
            this.player1 = player;
        } else {
            this.player2 = player;
        }

    }

    public int getPlayerBullet(int no) {
        if(no == 1) {
            return player1bullet;
        } else {
            return player2bullet;
        }
    }

    public void setPlayerBullet(int no, int playerbullet) {
        if(no == 1) {
            this.player1bullet = playerbullet;
        } else {
            this.player2bullet = playerbullet;
        }

    }



    public Action getPlayerAction(int no) {
        if(no == 1) {
            return player1action;
        } else {
            return player2action;
        }

    }

    public void setPlayerAction(int no, Action playeraction) {
        if(no == 1) {
            this.player1action = playeraction;
        } else {
            this.player2action = playeraction;
        }

    }


    public int getPlayerRound(int no) {
        if(no == 1) {
            return player1round;
        } else {
            return player2round;
        }
    }

    public void setPlayerRound(int no, int playerround) {
        if(no == 1) {
            this.player1round = playerround;
        } else {
            this.player2round = playerround;
        }

    }


}
