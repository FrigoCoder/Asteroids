
package frigo.asteroids.core;

import java.util.NoSuchElementException;

import com.carrotsearch.hppc.IntObjectOpenHashMap;

public final class Entity extends Identity {

    private IntObjectOpenHashMap<Component> map = new IntObjectOpenHashMap<>();

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
