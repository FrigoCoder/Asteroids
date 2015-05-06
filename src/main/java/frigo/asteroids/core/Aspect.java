
package frigo.asteroids.core;

import com.carrotsearch.hppc.IntOpenHashSet;

public class Aspect extends Value {

    @SafeVarargs
    public static Aspect allOf (Class<? extends Component>... types) {
        return new Aspect().andAllOf(types);
    }

    @SafeVarargs
    public static Aspect noneOf (Class<? extends Component>... types) {
        return new Aspect().andNoneOf(types);
    }

    public final IntOpenHashSet all = new IntOpenHashSet();
    public final IntOpenHashSet none = new IntOpenHashSet();

    @SafeVarargs
    public final Aspect andAllOf (Class<? extends Component>... types) {
        for( Class<? extends Component> type : types ){
            all.add(System.identityHashCode(type));
        }
        return this;
    }

    @SafeVarargs
    public final Aspect andNoneOf (Class<? extends Component>... types) {
        for( Class<? extends Component> type : types ){
            none.add(System.identityHashCode(type));
        }
        return this;
    }

}
