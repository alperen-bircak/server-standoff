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
        RELOAD,
        PROTECT,
        PISTOL,
        RIFLE,
        BAZOOKA
    }
    Action player1action;
    Action player2action;

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

    public Player getPlayer1() {
        return player1;
    }

    public void setPlayer1(Player player1) {
        this.player1 = player1;
    }

    public Player getPlayer2() {
        return player2;
    }

    public void setPlayer2(Player player2) {
        this.player2 = player2;
    }

    public int getPlayer1bullet() {
        return player1bullet;
    }

    public void setPlayer1bullet(int player1bullet) {
        this.player1bullet = player1bullet;
    }

    public int getPlayer2bullet() {
        return player2bullet;
    }

    public void setPlayer2bullet(int player2bullet) {
        this.player2bullet = player2bullet;
    }

    public Action getPlayer1action() {
        return player1action;
    }

    public void setPlayer1action(Action player1action) {
        this.player1action = player1action;
    }

    public Action getPlayer2action() {
        return player2action;
    }

    public void setPlayer2action(Action player2action) {
        this.player2action = player2action;
    }

    public boolean isPlayer1ready() {
        return player1ready;
    }

    public void setPlayer1ready(boolean player1ready) {
        this.player1ready = player1ready;
    }

    public boolean isPlayer2ready() {
        return player2ready;
    }

    public void setPlayer2ready(boolean player2ready) {
        this.player2ready = player2ready;
    }

    boolean player1ready = false;
    boolean player2ready = false;
}
