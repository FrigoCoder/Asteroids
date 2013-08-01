
package frigo.asteroids.logic.gravity;

import frigo.asteroids.component.Vector;
import frigo.asteroids.core.Entity;

public class FunGravity implements GravityCalculator {

    @Override
    public Vector getDirectionalAcceleration (Entity attractor, Entity attracted) {
        return new PairwiseFunGravitation(attractor, attracted).getDirectionalAcceleration();
    }

}
