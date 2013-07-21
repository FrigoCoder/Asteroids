
package frigo.asteroids.core;

import java.util.HashMap;
import java.util.Map;
import java.util.NoSuchElementException;

public final class Entity {

    private Map<Class<? extends Component>, Component> components = new HashMap<>();

    public Entity () {
    }

    public Entity (Component... components) {
        for( Component component : components ){
            set(component);
        }
    }

    public void set (Component component) {
        components.put(component.getClass(), component);
    }

    public <T extends Component> T get (Class<T> type) {
        T component = (T) components.get(type);
        if( component == null ){
            throw new NoSuchElementException();
        }
        return component;
    }

    public boolean has (Class<? extends Component> type) {
        return components.containsKey(type);
    }

}
