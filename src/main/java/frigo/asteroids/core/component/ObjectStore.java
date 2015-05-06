
package frigo.asteroids.core.component;

import com.carrotsearch.hppc.IntObjectOpenHashMap;

public class ObjectStore<T> extends ComponentStore<T> {

    private IntObjectOpenHashMap<T> map = new IntObjectOpenHashMap<>();

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
