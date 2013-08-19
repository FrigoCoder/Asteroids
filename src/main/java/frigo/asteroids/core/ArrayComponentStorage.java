
package frigo.asteroids.core;

import java.util.ArrayList;
import java.util.List;
import java.util.NoSuchElementException;

public class ArrayComponentStorage<T extends Component> implements ComponentStorage<T> {

    private List<T> list = new ArrayList<>();

    @Override
    public boolean has (Entity entity) {
        if( entity.id >= list.size() ){
            return false;
        }
        return list.get(entity.id) != null;
    }

    @Override
    public void set (Entity entity, T component) {
        while( list.size() <= entity.id ){
            list.add(null);
        }
        list.set(entity.id, component);
    }

    @Override
    public T get (Entity entity) {
        if( entity.id >= list.size() ){
            throw new NoSuchElementException();
        }
        T component = list.get(entity.id);
        if( component == null ){
            throw new NoSuchElementException();
        }
        return component;
    }

}
