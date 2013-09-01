
package frigo.asteroids.component;

import static frigo.asteroids.component.Vector.NULL;
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

    public Planar velocity (Vector newVelocity) {
        return new Planar(position, newVelocity, acceleration);
    }

    public Planar acceleration (Vector newAcceleration) {
        return new Planar(position, velocity, newAcceleration);
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
