
package frigo.asteroids.component;

import static frigo.asteroids.core.Vector.NULL;
import static frigo.asteroids.core.Vector.vector;
import frigo.asteroids.core.Component;
import frigo.asteroids.core.Vector;

public class Planar extends Component {

    public Vector position;
    public Vector velocity;
    public Vector acceleration;

    public Planar () {
        this(NULL, NULL, NULL);
    }

    public Planar (Vector position, Vector velocity, Vector acceleration) {
        this.position = position;
        this.velocity = velocity;
        this.acceleration = acceleration;
    }

    public Planar (double x, double y, double dx, double dy, double ddx, double ddy) {
        position = vector(x, y);
        velocity = vector(dx, dy);
        acceleration = vector(ddx, ddy);
    }

    public void accelerate (Vector direction) {
        acceleration = acceleration.add(direction);
    }

    public void update (double elapsed) {
        Vector newVelocity = velocity.add(acceleration.mul(elapsed));
        position = position.add(velocity.add(newVelocity).mul(0.5 * elapsed));
        velocity = newVelocity;
        acceleration = NULL;
    }

}
