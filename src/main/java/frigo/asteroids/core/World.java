
package frigo.asteroids.core;

import java.util.Collections;
import java.util.HashMap;
import java.util.HashSet;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;
import java.util.Set;

public class World {

    private long entityCounter;
    private Set<Entity> entities = new HashSet<>();
    private List<Logic> logics = new LinkedList<>();
    private Map<Class<?>, List<Object>> messages = new HashMap<>();
    private Map<Class<? extends Component>, ComponentManager<?>> componentMappers = new HashMap<>();

    private <T extends Component> ComponentManager<T> getComponentMapper (Class<T> type) {
        if( !componentMappers.containsKey(type) ){
            componentMappers.put(type, new ComponentManager<T>());
        }
        return (ComponentManager<T>) componentMappers.get(type);
    }

    public Entity createEntity (Component... components) {
        Entity entity = new Entity(entityCounter++);
        for( Component component : components ){
            set(entity, component);
        }
        return entity;
    }

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

    public void addEntity (Entity entity) {
        entities.add(entity);
    }

    public Set<Entity> getEntities () {
        return entities;
    }

    public Set<Entity> getEntitiesFor (Aspect aspect) {
        Set<Entity> result = new HashSet<>();
        for( Entity entity : entities ){
            if( aspect.matches(entity) ){
                result.add(entity);
            }
        }
        return result;
    }

    public void addLogic (Logic logic) {
        logics.add(logic);
    }

    public void addMessage (Object message) {
        Class<?> clazz = message.getClass();
        if( !messages.containsKey(clazz) ){
            messages.put(clazz, new LinkedList<>());
        }
        messages.get(clazz).add(message);
    }

    public <T> List<T> getMessages (Class<? extends T> clazz) {
        if( !messages.containsKey(clazz) ){
            return Collections.EMPTY_LIST;
        }
        return (List<T>) messages.get(clazz);
    }

    public void init () {
        for( Logic logic : logics ){
            logic.init(this);
        }
    }

    public void update (double elapsedSeconds) {
        for( Logic logic : logics ){
            logic.update(this, elapsedSeconds);
        }
        messages.clear();
    }

}
