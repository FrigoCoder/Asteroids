
package frigo.asteroids.core.component;

import com.carrotsearch.hppc.IntDoubleHashMap;

public class DoubleStore extends ComponentStore<Double> {

    private IntDoubleHashMap map = new IntDoubleHashMap();

    @Override
    public boolean has (int entity) {
        return map.containsKey(entity);
    }

    @Override
    public void remove (int entity) {
        map.remove(entity);
    }

    @Override
    public double getDouble (int entity) {
        return map.get(entity);
    }

    @Override
    public void setDouble (int entity, double component) {
        map.put(entity, component);
    }

}
