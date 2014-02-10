
package frigo.asteroids.core;

import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;

public class EntityManager {

    private List<Entity> entities = new ArrayList<>();

    public Entity create (Component... components) {
        Entity entity = new Entity();
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
            if( aspect.matches(entity) ){
                result.add(entity);
            }
        }
        return result;
    }
}
