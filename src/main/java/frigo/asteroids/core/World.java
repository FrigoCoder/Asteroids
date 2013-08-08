
package frigo.asteroids.core;

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
    private Map<Class<? extends Component>, ComponentMapper<?>> componentMappers = new HashMap<>();

    private MessageManager messages = new MessageManager();

    private <T extends Component> ComponentMapper<T> getComponentMapper (Class<T> type) {
        if( !componentMappers.containsKey(type) ){
            componentMappers.put(type, new ComponentMapper<T>());
        }
        return (ComponentMapper<T>) componentMappers.get(type);
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

    public void addMessage (Object message) {
        messages.addMessage(message);
    }

    public <T> List<T> getMessages (Class<? extends T> clazz) {
        return messages.getMessages(clazz);
    }

}
