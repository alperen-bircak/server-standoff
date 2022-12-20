package Database;

import java.util.Collection;
import java.util.HashMap;
import java.util.UUID;

public class Store<T extends Storable>{
    private HashMap<UUID, T> map;



    public Store() {
        this.map = new HashMap<>();
    }

    public synchronized T add(T item){
        UUID id = UUID.randomUUID();
        map.put(id, item);
        item.setId(id);
        return item;
    }

    public T get(UUID id) throws StoreException {
        T item = map.get(id);

        if(item == null) {
            throw new StoreException("This item doesn't exist");
        }

        return item;
    }

    public Collection<T> getAll(){
        return map.values();
    }

    public synchronized T modify(UUID id, T item) throws StoreException {
        T newItem = map.replace(id, item);

        if(newItem == null) {
            throw new StoreException("This item doesn't exist");
        }

        return newItem;
    }

    public synchronized T remove(UUID id) throws StoreException {
        T removedItem = map.remove(id);

        if(removedItem == null) {
            throw new StoreException("This item doesn't exist");
        }

        return removedItem;
    }
}
