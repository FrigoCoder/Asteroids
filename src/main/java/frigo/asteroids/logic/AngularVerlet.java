
package frigo.asteroids.logic;

public class AngularVerlet {

    private double acceleration;
    private double velocity;
    private double displacement;

    public AngularVerlet (double acceleration, double velocity, double displacement) {
        this.acceleration = acceleration;
        this.velocity = velocity;
        this.displacement = displacement;
    }

    public double getVelocity (double elapsed) {
        return velocity + acceleration * elapsed;
    }

    public double getDisplacement (double elapsed) {
        return displacement + (getVelocity(elapsed) + velocity) * 0.5 * elapsed;
    }

}
