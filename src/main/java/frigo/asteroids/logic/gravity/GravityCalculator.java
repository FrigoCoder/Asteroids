
package frigo.asteroids.logic.gravity;

import frigo.asteroids.component.Vector;
import frigo.asteroids.core.Entity;

public interface GravityCalculator {

    Vector getDirectionalAcceleration (Entity attractor, Entity attractable);

}
