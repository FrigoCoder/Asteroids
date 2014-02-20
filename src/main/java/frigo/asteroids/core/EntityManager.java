
package frigo.asteroids.core;

import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;

public class EntityManager {

    private List<Entity> entities = new ArrayList<>();
    private ComponentDatabase database = new ComponentDatabase();

    public Entity create () {
        Entity entity = new Entity(database);
        entities.add(entity);
        return entity;
    }

    public void remove (Entity entity) {
        entities.remove(entity);
    }

    public List<Entity> getEntitiesFor (Aspect aspect) {
        // TODO OPTIMIZE THE SHIT OUT OF THIS
        List<Entity> result = new LinkedList<>();
        for( Entity entity : entities ){
            if( entity.matches(aspect) ){
                result.add(entity);
            }
        }
        return result;
    }

}
