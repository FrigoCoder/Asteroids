
package frigo.asteroids.core;

import java.util.HashMap;
import java.util.Map;

public class ComponentManager {

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

}
