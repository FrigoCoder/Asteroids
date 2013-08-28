
package frigo.asteroids.component;

import frigo.asteroids.core.Component;

public class Angular extends Component {

    public final double position;
    public final double velocity;
    public final double acceleration;

    public Angular (double position, double velocity, double acceleration) {
        this.position = position;
        this.velocity = velocity;
        this.acceleration = acceleration;
    }

    public Angular accelerate (double direction) {
        return new Angular(position, velocity, acceleration + direction);
    }

}
