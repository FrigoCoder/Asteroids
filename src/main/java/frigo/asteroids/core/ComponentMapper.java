
package frigo.asteroids.core;

import java.util.HashMap;
import java.util.Map;

public class ComponentMapper implements EntityListener {

    private ComponentStorageFactory factory;
    private Map<Class<? extends Component>, ComponentStorage<?>> storages = new HashMap<>();

    public ComponentMapper (ComponentStorageFactory factory) {
        this.factory = factory;
    }

    public boolean has (Entity entity, Class<? extends Component> type) {
        return getComponentStorage(type).has(entity);
    }

    public <T extends Component> T get (Entity entity, Class<T> type) {
        return getComponentStorage(type).get(entity);
    }

    public <T extends Component> void set (Entity entity, T component) {
        Class<T> clazz = (Class<T>) component.getClass();
        getComponentStorage(clazz).set(entity, component);
    }

    public <T extends Component> ComponentStorage<T> getComponentStorage (Class<T> type) {
        if( !storages.containsKey(type) ){
            storages.put(type, factory.create());
        }
        return (ComponentStorage<T>) storages.get(type);
    }

    @Override
    public void added () {
        for( ComponentStorage<?> storage : storages.values() ){
            storage.added();
        }
    }

}
