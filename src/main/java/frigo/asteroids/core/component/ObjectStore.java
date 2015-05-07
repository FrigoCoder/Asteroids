
package frigo.asteroids.core.component;

import com.carrotsearch.hppc.IntObjectHashMap;

public class ObjectStore<T> extends ComponentStore<T> {

    private IntObjectHashMap<T> map = new IntObjectHashMap<>();

    @Override
    public boolean has (int entity) {
        return map.containsKey(entity);
    }

    @Override
    public void remove (int entity) {
        map.remove(entity);
    }

    @Override
    public T get (int entity) {
        return map.get(entity);
    }

    @Override
    public void set (int entity, T component) {
        map.put(entity, component);
    }

}
