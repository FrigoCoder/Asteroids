
package frigo.asteroids.core;

import java.util.HashMap;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;
import java.util.NoSuchElementException;

public class EntityManager {

    private List<Entity> entities = new LinkedList<>();

    private ComponentStorageFactory factory;
    private Map<Class<? extends Component>, ComponentStorage<?>> storages = new HashMap<>();

    public EntityManager (ComponentStorageFactory factory) {
        this.factory = factory;
    }

    public Entity createEntity (Component... componentsToSet) {
        Entity entity = createEntity();
        for( Component component : componentsToSet ){
            set(entity, component);
        }
        return entity;
    }

    private Entity createEntity () {
        Entity entity = new Entity(entities.size());
        entities.add(entity);
        for( ComponentStorage<?> storage : storages.values() ){
            storage.added(entity);
        }
        return entity;
    }

    public <T extends Component> boolean has (Entity entity, Class<T> type) {
        ComponentStorage<T> storage = (ComponentStorage<T>) storages.get(type);
        if( storage == null ){
            return false;
        }
        return storage.has(entity);
    }

    public <T extends Component> T get (Entity entity, Class<T> type) {
        ComponentStorage<T> storage = (ComponentStorage<T>) storages.get(type);
        if( storage == null ){
            throw new NoSuchElementException();
        }
        return storage.get(entity);
    }

    public <T extends Component> void set (Entity entity, T component) {
        ComponentStorage<T> storage = getOrCreateStorage((Class<T>) component.getClass());
        storage.set(entity, component);
    }

    public <T extends Component> ComponentStorage<T> getOrCreateStorage (Class<T> type) {
        ComponentStorage<T> storage = (ComponentStorage<T>) storages.get(type);
        if( storage != null ){
            return storage;
        }
        storages.put(type, factory.create(entities.size()));
        return (ComponentStorage<T>) storages.get(type);
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

    @Deprecated
    public boolean matches (Entity entity, Aspect aspect) {
        return new EntityMatcher(this, aspect).matches(entity);
    }

}
