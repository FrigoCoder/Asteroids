
package frigo.asteroids.core.storage;

import java.util.NoSuchElementException;

import frigo.asteroids.core.Component;
import frigo.asteroids.core.Entity;
import gnu.trove.map.hash.TIntObjectHashMap;

public class TroveComponentStorage<T extends Component> {

    private TIntObjectHashMap<T> map = new TIntObjectHashMap<>();

    public boolean has (Entity entity) {
        return map.containsKey(entity.id);
    }

    public T get (Entity entity) {
        T component = map.get(entity.id);
        if( component == null ){
            throw new NoSuchElementException();
        }
        return component;
    }

    public void set (Entity entity, T component) {
        map.put(entity.id, component);
    }

    public void remove (Entity entity) {
        map.remove(entity.id);
    }

}
