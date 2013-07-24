
package frigo.asteroids.core;

import java.util.HashSet;
import java.util.Set;

public final class Aspect extends Value {

    @SafeVarargs
    public static Aspect allOf (Class<? extends Component>... types) {
        return new Aspect().andAllOf(types);
    }

    @SafeVarargs
    public static Aspect noneOf (Class<? extends Component>... types) {
        return new Aspect().andNoneOf(types);
    }

    private Set<Class<? extends Component>> all = new HashSet<>();
    private Set<Class<? extends Component>> none = new HashSet<>();

    private Aspect () {
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

    public boolean matches (Entity entity) {
        for( Class<? extends Component> component : all ){
            if( !entity.has(component) ){
                return false;
            }
        }
        for( Class<? extends Component> component : none ){
            if( entity.has(component) ){
                return false;
            }
        }
        return true;
    }

}
