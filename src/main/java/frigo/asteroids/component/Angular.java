
package frigo.asteroids.component;

import frigo.asteroids.core.Component;
import frigo.asteroids.core.component.ComponentId;

public class Angular extends Component {

    public static final ComponentId<Angular> ID = new ComponentId<>(Angular.class);

    public double position;
    public double velocity;
    public double acceleration;

    public Angular (double position, double velocity, double acceleration) {
        this.position = position;
        this.velocity = velocity;
        this.acceleration = acceleration;
    }

    public void accelerate (double direction) {
        acceleration += direction;
    }

    public void update (double elapsed) {
        double newVelocity = velocity + acceleration * elapsed;
        position += (velocity + newVelocity) * 0.5 * elapsed;
        velocity = newVelocity;
        acceleration = 0;
    }

}
