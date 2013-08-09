
package frigo.asteroids.core;

import java.util.HashMap;
import java.util.HashSet;
import java.util.Map;
import java.util.Set;

public class EntityManager {

    private long counter;
    private Set<Entity> entities = new HashSet<>();

    public Entity createEntity (Component... components) {
        Entity entity = new Entity(counter++);
        for( Component component : components ){
            set(entity, component);
        }
        return entity;
    }

    public void addEntity (Entity entity) {
        entities.add(entity);
    }

    public Set<Entity> getEntities () {
        return entities;
    }

    private Map<Class<? extends Component>, ComponentMapper<?>> componentMappers = new HashMap<>();

    public boolean has (Entity entity, Class<? extends Component> type) {
        return getComponentMapper(type).has(entity);
    }

    public <T extends Component> T get (Entity entity, Class<T> type) {
        return getComponentMapper(type).get(entity);
    }

    public <T extends Component> void set (Entity entity, T component) {
        Class<T> clazz = (Class<T>) component.getClass();
        getComponentMapper(clazz).set(entity, component);
    }

    public <T extends Component> ComponentMapper<T> getComponentMapper (Class<T> type) {
        if( !componentMappers.containsKey(type) ){
            componentMappers.put(type, new ComponentMapper<T>());
        }
        return (ComponentMapper<T>) componentMappers.get(type);
    }

    public Set<Entity> getEntitiesFor (Aspect aspect) {
        Set<Entity> result = new HashSet<>();
        for( Entity entity : entities ){
            if( matches(entity, aspect) ){
                result.add(entity);
            }
        }
        return result;
    }

    public boolean matches (Entity entity, Aspect aspect) {
        for( Class<? extends Component> component : aspect.all ){
            if( !has(entity, component) ){
                return false;
            }
        }
        for( Class<? extends Component> component : aspect.none ){
            if( has(entity, component) ){
                return false;
            }
        }
        return true;
    }

}
