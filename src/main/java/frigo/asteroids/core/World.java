
package frigo.asteroids.core;

import java.util.HashMap;
import java.util.HashSet;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;
import java.util.Set;

public class World {

    private Set<Entity> entities = new HashSet<>();
    private List<Logic> logics = new LinkedList<>();
    private Map<Class<?>, List<Object>> messages = new HashMap<>();

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
            return new LinkedList<>();
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
