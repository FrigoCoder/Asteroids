
package frigo.asteroids.core;

import java.util.HashMap;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;

public class EntityManager {

    private int entitiesSoFar;
    private Map<Integer, Entity> entities = new HashMap<>();

    public Entity create (Component... components) {
        Entity entity = new Entity(entitiesSoFar++);
        entities.put(entity.id, entity);
        for( Component component : components ){
            entity.add(component);
        }
        return entity;
    }

    public void remove (Entity entity) {
        entities.remove(entity.id);
    }

    public List<Entity> getEntitiesFor (Aspect aspect) {
        List<Entity> result = new LinkedList<>();
        for( Object object : entities.values() ){
            Entity entity = (Entity) object;
            if( aspect.matches(entity) ){
                result.add(entity);
            }
        }
        return result;
    }

}
