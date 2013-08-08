
package frigo.asteroids.core;

import gnu.trove.TLongObjectHashMap;

import java.util.NoSuchElementException;

public class ComponentMapper<T extends Component> {

    private TLongObjectHashMap map = new TLongObjectHashMap();

    public boolean has (Entity entity) {
        return map.containsKey(entity.id);
    }

    public void set (Entity entity, T component) {
        map.put(entity.id, component);
    }

    public T get (Entity entity) {
        T component = (T) map.get(entity.id);
        if( component == null ){
            throw new NoSuchElementException();
        }
        return component;
    }

}
