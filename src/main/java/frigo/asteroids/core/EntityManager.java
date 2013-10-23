
package frigo.asteroids.core;

import java.util.HashMap;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;
import java.util.NoSuchElementException;

import frigo.asteroids.core.storage.TroveComponentStorage;
import frigo.asteroids.core.storage.TroveComponentStorageFactory;

public class EntityManager {

    private int entitiesSoFar;
    private List<Entity> entities = new LinkedList<>();

    private TroveComponentStorageFactory factory;
    private Map<Class<? extends Component>, TroveComponentStorage<?>> storages = new HashMap<>();

    public EntityManager (TroveComponentStorageFactory factory) {
        this.factory = factory;
    }

    public Entity create (Component... components) {
        Entity entity = create();
        for( Component component : components ){
            entity.set(component);
        }
        return entity;
    }

    private Entity create () {
        Entity entity = new Entity(this, entitiesSoFar++);
        entities.add(entity);
        for( TroveComponentStorage<?> storage : storages.values() ){
            storage.added(entity);
        }
        return entity;
    }

    public void remove (Entity entity) {
        for( TroveComponentStorage<?> storage : storages.values() ){
            storage.removed(entity);
        }
        entities.remove(entity);
    }

    <T extends Component> boolean has (Entity entity, Class<T> type) {
        TroveComponentStorage<T> storage = (TroveComponentStorage<T>) storages.get(type);
        if( storage == null ){
            return false;
        }
        return storage.has(entity);
    }

    <T extends Component> T get (Entity entity, Class<T> type) {
        TroveComponentStorage<T> storage = (TroveComponentStorage<T>) storages.get(type);
        if( storage == null ){
            throw new NoSuchElementException();
        }
        return storage.get(entity);
    }

    <T extends Component> void set (Entity entity, T component) {
        TroveComponentStorage<T> storage = getOrCreateStorage((Class<T>) component.getClass());
        storage.set(entity, component);
    }

    public <T extends Component> TroveComponentStorage<T> getOrCreateStorage (Class<T> type) {
        TroveComponentStorage<T> storage = (TroveComponentStorage<T>) storages.get(type);
        if( storage != null ){
            return storage;
        }
        storages.put(type, factory.create(entities.size()));
        return (TroveComponentStorage<T>) storages.get(type);
    }

    public List<Entity> getEntitiesFor (Aspect aspect) {
        EntityMatcher matcher = new EntityMatcher(this, aspect);
        List<Entity> result = new LinkedList<>();
        for( Entity entity : entities ){
            if( matcher.matches(entity) ){
                result.add(entity);
            }
        }
        return result;
    }

}
