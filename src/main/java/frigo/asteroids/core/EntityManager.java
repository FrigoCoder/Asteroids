
package frigo.asteroids.core;

import gnu.trove.TIntObjectHashMap;

import java.util.LinkedList;
import java.util.List;

public class EntityManager {

    private int entitiesSoFar;
    private TIntObjectHashMap entities = new TIntObjectHashMap();
    private EntityComponentMap map = new EntityComponentMap();

    public Entity create (Component... components) {
        Entity entity = create();
        for( Component component : components ){
            entity.set(component);
        }
        return entity;
    }

    private Entity create () {
        Entity entity = new Entity(map, entitiesSoFar++);
        entities.put(entity.id, entity);
        map.add(entity);
        return entity;
    }

    public void remove (Entity entity) {
        map.remove(entity);
        entities.remove(entity.id);
    }

    public List<Entity> getEntitiesFor (Aspect aspect) {
        List<Entity> result = new LinkedList<>();
        for( Object object : entities.getValues() ){
            Entity entity = (Entity) object;
            if( aspect.matches(entity) ){
                result.add(entity);
            }
        }
        return result;
    }

}
