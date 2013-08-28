
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
}
