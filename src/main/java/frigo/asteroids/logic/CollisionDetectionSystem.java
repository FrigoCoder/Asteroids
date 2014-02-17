
package frigo.asteroids.logic;

import java.util.LinkedList;
import java.util.List;

import frigo.asteroids.component.Planar;
import frigo.asteroids.component.Size;
import frigo.asteroids.core.Aspect;
import frigo.asteroids.core.Entity;
import frigo.asteroids.core.Logic;

public class CollisionDetectionSystem extends Logic {

    private Aspect aspect = Aspect.allOf(Planar.ID, Size.ID);
    private List<CollisionAction> actions = new LinkedList<>();

    public void register (CollisionAction action) {
        actions.add(action);
    }

    @Override
    public void update (double elapsedSeconds) {
        List<Entity> entities = world.getEntitiesFor(aspect);
        for( int i = 0; i < entities.size(); i++ ){
            for( int j = i + 1; j < entities.size(); j++ ){
                Entity entity1 = entities.get(i);
                Entity entity2 = entities.get(j);
                double distance = entity1.get(Planar.ID).position.sub(entity2.get(Planar.ID).position).length();
                double radiusSum = entity1.get(Size.ID).size + entity2.get(Size.ID).size;
                if( distance <= radiusSum ){
                    for( CollisionAction action : actions ){
                        action.collision(entity1, entity2);
                    }
                }
            }
        }
    }

}
