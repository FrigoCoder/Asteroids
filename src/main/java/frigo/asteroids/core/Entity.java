
package frigo.asteroids.core;

import java.util.HashMap;
import java.util.Map;
import java.util.NoSuchElementException;

public final class Entity extends Identity {

    public final int id;
    private Map<Class<? extends Component>, Component> map = new HashMap<>();

    Entity (int id) {
        this.id = id;
    }

    public <T extends Component> boolean has (Class<T> type) {
        return map.containsKey(type);
    }

    public <T extends Component> T get (Class<T> type) {
        T component = (T) map.get(type);
        if( component == null ){
            throw new NoSuchElementException();
        }
        return component;
    }

    public <T extends Component> void set (T component) {
        map.put(component.getClass(), component);
    }

    public <T extends Component> void remove (Class<T> type) {
        map.remove(type);
    }

}
