
package frigo.asteroids.core;

import com.carrotsearch.hppc.IntHashSet;

public class Aspect extends Value {

    @SafeVarargs
    public static Aspect allOf (int... types) {
        return new Aspect().andAllOf(types);
    }

    @SafeVarargs
    public static Aspect noneOf (int... types) {
        return new Aspect().andNoneOf(types);
    }

    public final IntHashSet all = new IntHashSet();
    public final IntHashSet none = new IntHashSet();

    @SafeVarargs
    public final Aspect andAllOf (int... types) {
        for( int type : types ){
            all.add(type);
        }
        return this;
    }

    @SafeVarargs
    public final Aspect andNoneOf (int... types) {
        for( int type : types ){
            none.add(type);
        }
        return this;
    }

}
