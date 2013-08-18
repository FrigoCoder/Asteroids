
package frigo.asteroids.component;

public class Velocity extends Vector {

    public Velocity (double dx, double dy) {
        super(dx, dy);
    }

    public Velocity add (Vector acceleration, double elapsed) {
        return add(acceleration.mul(elapsed));
    }

    @SuppressWarnings("unchecked")
    @Override
    public Velocity add (Vector addend) {
        return super.add(addend);
    }

    @SuppressWarnings("unchecked")
    @Override
    public Velocity mul (double scalar) {
        return super.mul(scalar);
    }

    @SuppressWarnings("unchecked")
    @Override
    public Velocity rotate (double radians) {
        return super.rotate(radians);
    }

    @SuppressWarnings("unchecked")
    @Override
    protected Velocity create (double xval, double yval) {
        return new Velocity(xval, yval);
    }

}
