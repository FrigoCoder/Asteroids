
package frigo.asteroids.component;

public class Velocity extends Vector {

    public Velocity (double dx, double dy) {
        super(dx, dy);
    }

    public Velocity add (Vector acceleration, double elapsed) {
        return add(acceleration.mul(elapsed));
    }

}
