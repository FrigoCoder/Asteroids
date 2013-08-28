
package frigo.asteroids.component;

import frigo.asteroids.core.Component;

public class Planar extends Component {

    public final Vector position;
    public final Vector velocity;
    public final Vector acceleration;

    public Planar (Vector position) {
        this(position, new Vector(0, 0), new Vector(0, 0));
    }

    public Planar (Vector position, Vector velocity) {
        this(position, velocity, new Vector(0, 0));
    }

    public Planar (Vector position, Vector velocity, Vector acceleration) {
        this.position = position;
        this.velocity = velocity;
        this.acceleration = acceleration;
    }

    public Planar accelerate (Vector direction) {
        return new Planar(position, velocity, acceleration.add(direction));
    }

    public Planar update (double elapsed) {
        Vector newVelocity = velocity.add(acceleration.mul(elapsed));
        Vector newPosition = position.add(velocity.add(newVelocity).mul(0.5 * elapsed));
        return new Planar(newPosition, newVelocity, new Vector(0, 0));
    }

}
