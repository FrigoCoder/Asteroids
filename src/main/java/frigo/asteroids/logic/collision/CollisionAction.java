
package frigo.asteroids.logic.collision;

import frigo.asteroids.core.Entity;

public interface CollisionAction {

    void collision (Entity attacker, Entity attacked);

}
