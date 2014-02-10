
package frigo.asteroids.core;

import it.unimi.dsi.fastutil.ints.Int2ObjectMap;
import it.unimi.dsi.fastutil.ints.Int2ObjectOpenHashMap;

import java.util.NoSuchElementException;

public final class Entity extends Identity {

    private Int2ObjectMap<Component> map = new Int2ObjectOpenHashMap<>();

    public <T extends Component> boolean has (Class<T> type) {
        return map.containsKey(System.identityHashCode(type));
    }

    public <T extends Component> T get (Class<T> type) {
        T component = (T) map.get(System.identityHashCode(type));
        if( component == null ){
            throw new NoSuchElementException();
        }
        return component;
    }

    public <T extends Component> void add (T component) {
        map.put(System.identityHashCode(component.getClass()), component);
    }

    public <T extends Component> void remove (Class<T> type) {
        map.remove(System.identityHashCode(type));
    }

}
