
package frigo.asteroids.core;

import gnu.trove.map.hash.TIntObjectHashMap;

import java.util.LinkedList;
import java.util.List;

public class EntityManager {

    private int entitiesSoFar;
    private TIntObjectHashMap<Entity> entities = new TIntObjectHashMap<>();

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
