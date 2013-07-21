
package frigo.asteroids.core;

import java.util.HashSet;
import java.util.Set;

public class Aspect extends Value {

    private Set<Class<? extends Component>> components = new HashSet<>();

    @SafeVarargs
    public static Aspect all (Class<? extends Component>... types) {
        Aspect aspect = new Aspect();
        for( Class<? extends Component> type : types ){
            aspect.components.add(type);
        }
        return aspect;
    }

    public boolean matches (Entity entity) {
        for( Class<? extends Component> component : components ){
            if( !entity.has(component) ){
                return false;
            }
        }
        return true;
    }

}
