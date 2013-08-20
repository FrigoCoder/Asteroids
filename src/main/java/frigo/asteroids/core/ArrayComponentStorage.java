
package frigo.asteroids.core;

import java.util.ArrayList;
import java.util.List;
import java.util.NoSuchElementException;

public class ArrayComponentStorage<T extends Component> implements ComponentStorage<T> {

    private List<T> list = new ArrayList<>();

    public ArrayComponentStorage (int entities) {
        for( int i = 0; i < entities; i++ ){
            added();
        }
    }

    @Override
    public void added () {
        list.add(null);
    }

    @Override
    public boolean has (Entity entity) {
        return list.get(entity.id) != null;
    }

    @Override
    public void set (Entity entity, T component) {
        list.set(entity.id, component);
    }

    @Override
    public T get (Entity entity) {
        T component = list.get(entity.id);
        if( component == null ){
            throw new NoSuchElementException();
        }
        return component;
    }

}
