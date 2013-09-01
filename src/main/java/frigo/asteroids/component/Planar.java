
package frigo.asteroids.component;

import static frigo.asteroids.component.Vector.NULL;
import static frigo.asteroids.component.Vector.vector;
import frigo.asteroids.core.Component;

public class Planar extends Component {

    public static Planar planar () {
        return new Planar(NULL, NULL, NULL);
    }

    public final Vector position;
    public final Vector velocity;
    public final Vector acceleration;

    private Planar (Vector position, Vector velocity, Vector acceleration) {
        this.position = position;
        this.velocity = velocity;
        this.acceleration = acceleration;
    }

    public Planar position (Vector newPosition) {
        return new Planar(newPosition, velocity, acceleration);
    }

    public Planar position (double x, double y) {
        return position(vector(x, y));
    }

    public Planar velocity (Vector newVelocity) {
        return new Planar(position, newVelocity, acceleration);
    }

    public Planar velocity (double dx, double dy) {
        return velocity(vector(dx, dy));
    }

    public Planar acceleration (Vector newAcceleration) {
        return new Planar(position, velocity, newAcceleration);
    }

    public Planar acceleration (double ddx, double ddy) {
        return acceleration(vector(ddx, ddy));
    }

    public Planar accelerate (Vector direction) {
        return acceleration(acceleration.add(direction));
    }

    public Planar update (double elapsed) {
        Vector newVelocity = velocity.add(acceleration.mul(elapsed));
        Vector newPosition = position.add(velocity.add(newVelocity).mul(0.5 * elapsed));
        return planar().position(newPosition).velocity(newVelocity);
    }

}
