
package frigo.asteroids.core;

import java.util.HashSet;
import java.util.Set;

public class EntityManager {

    private long counter;
    private Set<Entity> entities = new HashSet<>();

    public Entity create () {
        return new Entity(counter++);
    }

    public void addEntity (Entity entity) {
        entities.add(entity);
    }

    public Set<Entity> getEntities () {
        return entities;
    }

    public Set<Entity> getEntitiesFor (Aspect aspect) {
        Set<Entity> result = new HashSet<>();
        for( Entity entity : entities ){
            if( aspect.matches(entity) ){
                result.add(entity);
            }
        }
        return result;
    }

}
