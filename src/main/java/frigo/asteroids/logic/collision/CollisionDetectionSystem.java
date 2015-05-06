
package frigo.asteroids.logic.collision;

import java.util.LinkedList;
import java.util.List;

import frigo.asteroids.component.Damage;
import frigo.asteroids.component.Health;
import frigo.asteroids.component.Planar;
import frigo.asteroids.component.Size;
import frigo.asteroids.core.Aspect;
import frigo.asteroids.core.Entity;
import frigo.asteroids.core.Logic;

public class CollisionDetectionSystem extends Logic {

    private List<CollisionAction> actions = new LinkedList<>();
    private Aspect damage = Aspect.allOf(Planar.class, Size.class, Damage.class);
    private Aspect health = Aspect.allOf(Planar.class, Size.class, Health.class);

    public void register (CollisionAction action) {
        actions.add(action);
    }

    @Override
    public void update (double elapsedSeconds) {
        List<Entity> attackers = world.getEntitiesFor(damage);
        List<Entity> attackeds = world.getEntitiesFor(health);
        for( Entity attacker : attackers ){
            for( Entity attacked : attackeds ){
                checkCollision(attacker, attacked);
            }
        }
    }

    private void checkCollision (Entity attacker, Entity attacked) {
        Planar attackerPlanar = attacker.get(Planar.ID);
        Planar attackedPlanar = attacked.get(Planar.ID);
        double distance = attackerPlanar.position.sub(attackedPlanar.position).length();

        Size attackerSize = attacker.get(Size.ID);
        Size attackedSize = attacked.get(Size.ID);
        double radiusSum = attackerSize.size + attackedSize.size;
        if( distance <= radiusSum ){
            callActions(attacker, attacked);
        }
    }

    private void callActions (Entity attacker, Entity attacked) {
        for( CollisionAction action : actions ){
            action.collision(attacker, attacked);
        }
    }

}
