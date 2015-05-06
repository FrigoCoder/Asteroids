
package frigo.asteroids.core;

import com.carrotsearch.hppc.IntOpenHashSet;

import frigo.asteroids.core.component.ComponentId;

public class Aspect extends Value {

    public final IntOpenHashSet all = new IntOpenHashSet();
    public final IntOpenHashSet none = new IntOpenHashSet();

    @SafeVarargs
    public static Aspect allOf (ComponentId<? extends Component>... types) {
        return new Aspect().andAllOf(types);
    }

    @SafeVarargs
    public static Aspect noneOf (ComponentId<? extends Component>... types) {
        return new Aspect().andNoneOf(types);
    }

    @SafeVarargs
    public final Aspect andAllOf (ComponentId<? extends Component>... types) {
        for( ComponentId<? extends Component> type : types ){
            all.add(type.id);
        }
        return this;
    }

    @SafeVarargs
    public final Aspect andNoneOf (ComponentId<? extends Component>... types) {
        for( ComponentId<? extends Component> type : types ){
            none.add(type.id);
        }
        return this;
    }

}
