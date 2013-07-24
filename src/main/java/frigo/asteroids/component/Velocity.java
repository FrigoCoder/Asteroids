
package frigo.asteroids.component;

public class Velocity extends Vector {

    public Velocity (double dx, double dy) {
        super(dx, dy);
    }

    public Velocity add (Vector acceleration, double elapsed) {
        return add(acceleration.mul(elapsed));
    }

    @Override
    public Velocity add (Vector other) {
        return new Velocity(x + other.x, y + other.y);
    }

    @Override
    public Velocity mul (double scalar) {
        return new Velocity(x * scalar, y * scalar);
    }

}
