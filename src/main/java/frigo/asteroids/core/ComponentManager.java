
package frigo.asteroids.core;

import java.util.HashMap;
import java.util.Map;

public class ComponentManager<T extends Component> {

    private Map<Long, T> map = new HashMap<>();

    public boolean has (Entity entity) {
        return map.containsKey(entity.id);
    }

    public void set (Entity entity, T component) {
        map.put(entity.id, component);
    }

    public T get (Entity entity) {
        T component = map.get(entity.id);
        if( component == null ){
            throw new IllegalArgumentException();
        }
        return component;
    }

}
