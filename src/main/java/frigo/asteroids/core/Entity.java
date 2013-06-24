
package frigo.asteroids.core;

import java.util.HashMap;
import java.util.Map;


public class Entity {

    private Map<Class<? extends Component>, Component> components = new HashMap<>();

    public void add (Component component) {
        components.put(component.getClass(), component);
    }

    public <T extends Component> T getComponent (Class<T> clazz) {
        return (T) components.get(clazz);
    }

}
