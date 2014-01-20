
package frigo.asteroids.component;

import frigo.asteroids.core.Component;

public class Angular extends Component {

    public double position;
    public double velocity;
    public double acceleration;

    public Angular () {
    }

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
        double newPosition = position + (velocity + newVelocity) * 0.5 * elapsed;
        acceleration = 0;
        velocity = newVelocity;
        position = newPosition;
    }

}
