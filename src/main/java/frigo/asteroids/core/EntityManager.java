
package frigo.asteroids.core;

import java.util.HashMap;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;

public class EntityManager {

    private int counter;
    private List<Entity> entities = new LinkedList<>();
    private Map<Class<? extends Component>, ComponentStorage<?>> components = new HashMap<>();
    private ComponentStorageFactory factory;

    public EntityManager (ComponentStorageFactory factory) {
        this.factory = factory;
    }

    public Entity createEntity (Component... componentsToSet) {
        Entity entity = new Entity(counter++);
        for( Component component : componentsToSet ){
            set(entity, component);
        }
        entities.add(entity);
        return entity;
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

    private <T extends Component> ComponentStorage<T> getComponentStorage (Class<T> type) {
        if( !components.containsKey(type) ){
            components.put(type, factory.create());
        }
        return (ComponentStorage<T>) components.get(type);
    }

    public List<Entity> getEntitiesFor (Aspect aspect) {
        List<Entity> result = new LinkedList<>();
        List<ComponentStorage<?>> needed = getNeededComponentStorages(aspect);
        List<ComponentStorage<?>> undesired = getUndesiredComponentStorages(aspect);
        for( Entity entity : entities ){
            if( matchesAllOf(needed, entity) && !matchesAnyOf(undesired, entity) ){
                result.add(entity);
            }
        }
        return result;
    }

    private List<ComponentStorage<?>> getNeededComponentStorages (Aspect aspect) {
        List<ComponentStorage<?>> storages = new LinkedList<>();
        for( Class<? extends Component> clazz : aspect.all ){
            storages.add(getComponentStorage(clazz));
        }
        return storages;
    }

    private List<ComponentStorage<?>> getUndesiredComponentStorages (Aspect aspect) {
        List<ComponentStorage<?>> storages = new LinkedList<>();
        for( Class<? extends Component> clazz : aspect.none ){
            storages.add(getComponentStorage(clazz));
        }
        return storages;
    }

    private boolean matchesAllOf (List<ComponentStorage<?>> needed, Entity entity) {
        for( ComponentStorage<?> storage : needed ){
            if( !storage.has(entity) ){
                return false;
            }
        }
        return true;
    }

    private boolean matchesAnyOf (List<ComponentStorage<?>> undesired, Entity entity) {
        for( ComponentStorage<?> storage : undesired ){
            if( storage.has(entity) ){
                return true;
            }
        }
        return false;
    }

    @Deprecated
    public boolean matches (Entity entity, Aspect aspect) {
        return getEntitiesFor(aspect).contains(entity);
    }

}
