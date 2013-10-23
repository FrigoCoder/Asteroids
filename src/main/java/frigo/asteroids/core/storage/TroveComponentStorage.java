
package frigo.asteroids.core.storage;

import java.util.NoSuchElementException;

import frigo.asteroids.core.Component;
import frigo.asteroids.core.ComponentStorage;
import frigo.asteroids.core.Entity;
import gnu.trove.TIntObjectHashMap;

public class TroveComponentStorage<T extends Component> implements ComponentStorage<T> {

    private TIntObjectHashMap map = new TIntObjectHashMap();

    @Override
    public void added (Entity entity) {
    }

    @Override
    public void removed (Entity entity) {
        map.remove(entity.id);
    }

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
