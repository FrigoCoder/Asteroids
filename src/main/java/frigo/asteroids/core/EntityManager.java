
package frigo.asteroids.core;

import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;

public class EntityManager {

    private List<Entity> entities = new ArrayList<>();
    private ComponentDatabase database = new ComponentDatabase();

    public Entity create (Component... components) {
        Entity entity = new Entity(database);
        entities.add(entity);
        for( Component component : components ){
            entity.add(component);
        }
        return entity;
    }

    public void remove (Entity entity) {
        entities.remove(entity);
    }

    public List<Entity> getEntitiesFor (Aspect aspect) {
        List<Entity> result = new LinkedList<>();
        for( Entity entity : entities ){
            if( entity.matches(aspect) ){
                result.add(entity);
            }
        }
        return result;
    }

}
