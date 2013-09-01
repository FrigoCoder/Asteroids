
package frigo.asteroids.component;

import frigo.asteroids.core.Component;

public class Angular extends Component {

    public static Angular angular () {
        return new Angular(0, 0, 0);
    }

    public final double position;
    public final double velocity;
    public final double acceleration;

    private Angular (double position, double velocity, double acceleration) {
        this.position = position;
        this.velocity = velocity;
        this.acceleration = acceleration;
    }

    public Angular position (double newPosition) {
        return new Angular(newPosition, velocity, acceleration);
    }

    public Angular velocity (double newVelocity) {
        return new Angular(position, newVelocity, acceleration);
    }

    public Angular acceleration (double newAcceleration) {
        return new Angular(position, velocity, newAcceleration);
    }

    public Angular accelerate (double direction) {
        return angular().position(position).velocity(velocity).acceleration(acceleration + direction);
    }

    public Angular update (double elapsed) {
        double newVelocity = velocity + acceleration * elapsed;
        double newPosition = position + (velocity + newVelocity) * 0.5 * elapsed;
        return new Angular(newPosition, newVelocity, 0);
    }
}
