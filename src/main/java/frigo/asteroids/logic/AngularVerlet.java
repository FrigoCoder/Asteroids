
package frigo.asteroids.logic;

import frigo.asteroids.component.AngularAcceleration;
import frigo.asteroids.component.AngularDisplacement;
import frigo.asteroids.component.AngularVelocity;

public class AngularVerlet {

    private AngularAcceleration acceleration;
    private AngularVelocity velocity;
    private AngularDisplacement displacement;

    public AngularVerlet (AngularAcceleration acceleration, AngularVelocity velocity, AngularDisplacement displacement) {
        this.acceleration = acceleration;
        this.velocity = velocity;
        this.displacement = displacement;
    }

    public AngularVelocity getVelocity (double elapsed) {
        return velocity.add(acceleration.mul(elapsed));
    }

    public AngularDisplacement getDisplacement (double elapsed) {
        return displacement.add(getVelocity(elapsed).add(velocity).mul(0.5 * elapsed));
    }

}
