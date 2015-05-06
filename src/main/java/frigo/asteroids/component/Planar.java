
package frigo.asteroids.component;

import static frigo.asteroids.core.Vector.ZERO;
import frigo.asteroids.core.Component;
import frigo.asteroids.core.Vector;

public class Planar extends Component {

    public static final int ID = System.identityHashCode(Planar.class);

    public Vector position;
    public Vector velocity;
    public Vector acceleration;

    public Planar (Vector position, Vector velocity, Vector acceleration) {
        this.position = position;
        this.velocity = velocity;
        this.acceleration = acceleration;
    }

    public void accelerate (Vector direction) {
        acceleration = acceleration.add(direction);
    }

    public void update (double elapsed) {
        Vector newVelocity = velocity.add(acceleration.mul(elapsed));
        position = position.add(velocity.add(newVelocity).mul(0.5 * elapsed));
        velocity = newVelocity;
        acceleration = ZERO;
    }

}
