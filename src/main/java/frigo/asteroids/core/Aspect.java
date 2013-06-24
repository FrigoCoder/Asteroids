
package frigo.asteroids.core;

import java.util.Collection;
import java.util.HashSet;
import java.util.Set;

public class Aspect {

    private Set<Class<? extends Component>> components = new HashSet<>();

    public static Aspect all (Collection<Class<? extends Component>> types) {
        Aspect aspect = new Aspect();
        aspect.components.addAll(types);
        return aspect;
    }

    public boolean matches (Entity entity) {
        for( Class<? extends Component> component : components ){
            if( !entity.hasComponent(component) ){
                return false;
            }
        }
        return true;
    }
}
