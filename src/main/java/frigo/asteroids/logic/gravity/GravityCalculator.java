
package frigo.asteroids.logic.gravity;

import frigo.asteroids.core.Entity;
import frigo.asteroids.core.Vector;

public interface GravityCalculator {

    Vector getDirectionalAcceleration (Entity attractor, Entity attractable);

}
