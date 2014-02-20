
package frigo.asteroids.logic;

import frigo.asteroids.core.Entity;

public interface CollisionAction {

    void collision (Entity attacker, Entity attacked);

}
