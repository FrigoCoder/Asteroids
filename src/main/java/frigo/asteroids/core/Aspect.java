
package frigo.asteroids.core;

import java.util.HashSet;
import java.util.Set;

public class Aspect extends Value {

    Set<Class<? extends Component>> all = new HashSet<>();
    Set<Class<? extends Component>> none = new HashSet<>();

    @SafeVarargs
    public static Aspect allOf (Class<? extends Component>... types) {
        return new Aspect().andAllOf(types);
    }

    @SafeVarargs
    public static Aspect noneOf (Class<? extends Component>... types) {
        return new Aspect().andNoneOf(types);
    }

    @SafeVarargs
    public final Aspect andAllOf (Class<? extends Component>... types) {
        for( Class<? extends Component> type : types ){
            all.add(type);
        }
        return this;
    }

    @SafeVarargs
    public final Aspect andNoneOf (Class<? extends Component>... types) {
        for( Class<? extends Component> type : types ){
            none.add(type);
        }
        return this;
    }

}
