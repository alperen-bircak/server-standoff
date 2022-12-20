package Database;

import java.util.UUID;

public class Storable {
    protected UUID id;
    public UUID getId() {
        return id;
    }

    public void setId(UUID id) {
        this.id = id;
    }

}
