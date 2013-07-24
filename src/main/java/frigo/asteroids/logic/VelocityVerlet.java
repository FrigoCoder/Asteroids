
package frigo.asteroids.logic;

import frigo.asteroids.component.Acceleration;
import frigo.asteroids.component.Position;
import frigo.asteroids.component.Velocity;

public class VelocityVerlet {

    private Acceleration acceleration;
    private Velocity velocity;
    private Position position;

    public VelocityVerlet (Acceleration acceleration, Velocity velocity, Position position) {
        this.acceleration = acceleration;
        this.velocity = velocity;
        this.position = position;
    }

    public Velocity getVelocity (double elapsed) {
        return velocity.add(acceleration, elapsed);
    }

    public Position getPosition (double elapsed) {
        return position.add(getVelocity(elapsed).add(velocity), 0.5 * elapsed);
    }
}
