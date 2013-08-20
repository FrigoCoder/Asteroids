
package frigo.asteroids.core;

import java.util.HashMap;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;

public class EntityManager {

    private int counter;
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
        Entity entity = new Entity(counter++);
        entities.add(entity);
        for( ComponentStorage<?> storage : storages.values() ){
            storage.added();
        }
        return entity;
    }

    public <T extends Component> boolean has (Entity entity, Class<T> type) {
        return getOrCreateStorage(type).has(entity);
    }

    public <T extends Component> T get (Entity entity, Class<T> type) {
        return getOrCreateStorage(type).get(entity);
    }

    public <T extends Component> void set (Entity entity, T component) {
        ComponentStorage<T> storage = getOrCreateStorage((Class<T>) component.getClass());
        storage.set(entity, component);
    }

    public <T extends Component> ComponentStorage<T> getOrCreateStorage (Class<T> type) {
        if( !storages.containsKey(type) ){
            storages.put(type, factory.create());
        }
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
