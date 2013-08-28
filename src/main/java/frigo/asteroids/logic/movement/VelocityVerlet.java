
package frigo.asteroids.logic.movement;

import frigo.asteroids.component.Planar;
import frigo.asteroids.component.Vector;

public class VelocityVerlet {

    private Planar planar;

    public VelocityVerlet (Planar planar) {
        this.planar = planar;
    }

    public Vector getVelocity (double elapsed) {
        return planar.velocity.add(planar.acceleration.mul(elapsed));
    }

    public Vector getPosition (double elapsed) {
        return planar.position.add(getVelocity(elapsed).add(planar.velocity).mul(0.5 * elapsed));
    }
}
