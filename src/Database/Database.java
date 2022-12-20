package Database;

import java.util.HashMap;
import java.util.UUID;

public class Database {
    private static HashMap<String, Store<?>> map;


    private static HashMap<String, Store<?>> getMap() {
        if(map == null) {
            map = new HashMap<>();
        }
        return map;
    }

    public static void add(String name, Store<?> store){
        getMap().put(name, store);

    }

    public static <T extends Storable> Store<T> get(String name) throws StoreException {
        Store<?> item = getMap().get(name);

        if(item == null) {
            throw new StoreException("This store doesn't exist");
        }

        return (Store<T>) item;
    }
}
