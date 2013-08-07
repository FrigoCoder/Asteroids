
package frigo.asteroids.core;

import java.util.HashMap;
import java.util.Map;
import java.util.NoSuchElementException;

public final class Entity {

    public final long id;

    private Map<Class<? extends Component>, Component> components = new HashMap<>();

    Entity (long id) {
        this.id = id;
    }

    void set (Component component) {
        components.put(component.getClass(), component);
    }

    <T extends Component> T get (Class<T> type) {
        T component = (T) components.get(type);
        if( component == null ){
            throw new NoSuchElementException();
        }
        return component;
    }

    boolean has (Class<? extends Component> type) {
        return components.containsKey(type);
    }

}
