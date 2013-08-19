
package frigo.asteroids.component;

public class Velocity extends Vector {

    public Velocity (double dx, double dy) {
        super(dx, dy);
    }

    public Velocity add (Vector acceleration, double elapsed) {
        return add(acceleration.mul(elapsed));
    }

    @Override
    public Velocity add (Vector addend) {
        return (Velocity) super.add(addend);
    }

    @Override
    public Velocity mul (double scalar) {
        return (Velocity) super.mul(scalar);
    }

    @Override
    public Velocity rotate (double radians) {
        return (Velocity) super.rotate(radians);
    }

    @Override
    protected Velocity create (double xval, double yval) {
        return new Velocity(xval, yval);
    }

}
