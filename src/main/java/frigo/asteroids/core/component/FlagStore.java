
package frigo.asteroids.core.component;

import com.carrotsearch.hppc.IntOpenHashSet;

public class FlagStore extends ComponentStore<Boolean> {

    private IntOpenHashSet set = new IntOpenHashSet();

    @Override
    public boolean has (int entity) {
        return set.contains(entity);
    }

    @Override
    public void remove (int entity) {
        set.remove(entity);
    }

    @Override
    public boolean getFlag (int entity) {
        return set.contains(entity);
    }

    @Override
    public void setFlag (int entity) {
        set.add(entity);
    }

}
