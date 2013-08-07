
package frigo.asteroids.core;

import java.util.HashSet;
import java.util.Set;

public class Aspect extends Value {

    private Set<Class<? extends Component>> all = new HashSet<>();
    private Set<Class<? extends Component>> none = new HashSet<>();
    private World world;

    public Aspect (World world) {
        this.world = world;
    }

    @SafeVarargs
    public final Aspect allOf (Class<? extends Component>... types) {
        for( Class<? extends Component> type : types ){
            all.add(type);
        }
        return this;
    }

    @SafeVarargs
    public final Aspect noneOf (Class<? extends Component>... types) {
        for( Class<? extends Component> type : types ){
            none.add(type);
        }
        return this;
    }

    @SafeVarargs
    public final Aspect andAllOf (Class<? extends Component>... types) {
        return allOf(types);
    }

    @SafeVarargs
    public final Aspect andNoneOf (Class<? extends Component>... types) {
        return noneOf(types);
    }

    public boolean matches (Entity entity) {
        for( Class<? extends Component> component : all ){
            if( !world.hasComponent(entity, component) ){
                return false;
            }
        }
        for( Class<? extends Component> component : none ){
            if( world.hasComponent(entity, component) ){
                return false;
            }
        }
        return true;
    }

}
