
package frigo.asteroids.core;

import java.util.HashSet;
import java.util.Set;

public class Aspect extends Value {

    private Set<Class<? extends Component>> all = new HashSet<>();
    private Set<Class<? extends Component>> none = new HashSet<>();

    @SafeVarargs
    public final Aspect all (Class<? extends Component>... types) {
        for( Class<? extends Component> type : types ){
            all.add(type);
        }
        return this;
    }

    @SafeVarargs
    public final Aspect none (Class<? extends Component>... types) {
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
