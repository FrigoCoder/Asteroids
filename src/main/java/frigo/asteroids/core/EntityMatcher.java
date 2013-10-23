
package frigo.asteroids.core;

import java.util.LinkedList;
import java.util.List;

import frigo.asteroids.core.storage.TroveComponentStorage;

public class EntityMatcher {

    private List<TroveComponentStorage<?>> all = new LinkedList<>();
    private List<TroveComponentStorage<?>> none = new LinkedList<>();

    public EntityMatcher (EntityManager mapper, Aspect aspect) {
        for( Class<? extends Component> clazz : aspect.all ){
            all.add(mapper.getOrCreateStorage(clazz));
        }
        for( Class<? extends Component> clazz : aspect.none ){
            none.add(mapper.getOrCreateStorage(clazz));
        }
    }

    public boolean matches (Entity entity) {
        for( TroveComponentStorage<?> storage : all ){
            if( !storage.has(entity) ){
                return false;
            }
        }
        for( TroveComponentStorage<?> storage : none ){
            if( storage.has(entity) ){
                return false;
            }
        }
        return true;
    }

}
