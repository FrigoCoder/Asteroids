
package frigo.asteroids.core;

import java.util.HashMap;
import java.util.Map;

import frigo.asteroids.core.storage.TroveComponentStorage;

public class EntityComponentMap {

    private Map<Class<? extends Component>, TroveComponentStorage<?>> storages = new HashMap<>();

    public void add (Entity entity) {
    }

    public void remove (Entity entity) {
        for( TroveComponentStorage<?> storage : storages.values() ){
            storage.remove(entity);
        }
    }

    public <T extends Component> boolean has (Entity entity, Class<T> type) {
        return getOrCreateStorage(type).has(entity);
    }

    public <T extends Component> T get (Entity entity, Class<T> type) {
        return getOrCreateStorage(type).get(entity);
    }

    public <T extends Component> void set (Entity entity, T component) {
        getOrCreateStorage((Class<T>) component.getClass()).set(entity, component);
    }

    public <T extends Component> void remove (Entity entity, Class<T> type) {
        getOrCreateStorage(type).remove(entity);
    }

    private <T extends Component> TroveComponentStorage<T> getOrCreateStorage (Class<T> type) {
        TroveComponentStorage<T> storage = (TroveComponentStorage<T>) storages.get(type);
        if( storage != null ){
            return storage;
        }
        storages.put(type, new TroveComponentStorage<>());
        return (TroveComponentStorage<T>) storages.get(type);
    }

}
