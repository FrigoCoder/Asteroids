
package frigo.asteroids.core;

import java.util.LinkedList;
import java.util.List;

public class EntityMatcher {

    private List<ComponentStorage<?>> all = new LinkedList<>();
    private List<ComponentStorage<?>> none = new LinkedList<>();

    public EntityMatcher (EntityManager mapper, Aspect aspect) {
        for( Class<? extends Component> clazz : aspect.all ){
            all.add(mapper.getOrCreateStorage(clazz));
        }
        for( Class<? extends Component> clazz : aspect.none ){
            none.add(mapper.getOrCreateStorage(clazz));
        }
    }

    public boolean matches (Entity entity) {
        for( ComponentStorage<?> storage : all ){
            if( !storage.has(entity) ){
                return false;
            }
        }
        for( ComponentStorage<?> storage : none ){
            if( storage.has(entity) ){
                return false;
            }
        }
        return true;
    }

}
