package Player;

import Database.Storable;

import java.util.UUID;

public class Player extends Storable {
    private String name;

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }


}
