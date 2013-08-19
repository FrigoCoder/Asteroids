
package frigo.asteroids.core;

import gnu.trove.TIntObjectHashMap;

import java.util.NoSuchElementException;

public class TroveComponentMapper<T extends Component> implements ComponentMapper<T> {

    private TIntObjectHashMap map = new TIntObjectHashMap();

    @Override
    public boolean has (Entity entity) {
        return map.containsKey(entity.id);
    }

    @Override
    public void set (Entity entity, T component) {
        map.put(entity.id, component);
    }

    @Override
    public T get (Entity entity) {
        T component = (T) map.get(entity.id);
        if( component == null ){
            throw new NoSuchElementException();
        }
        return component;
    }

}
