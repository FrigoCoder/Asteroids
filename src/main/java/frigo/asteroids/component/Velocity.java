
package frigo.asteroids.component;

public class Velocity extends Vector {

    public Velocity (double dx, double dy) {
        super(dx, dy);
    }

    @Override
    public Velocity add (Vector addend) {
        return (Velocity) super.add(addend);
    }

    @Override
    public Velocity sub (Vector subtrahend) {
        return (Velocity) super.sub(subtrahend);
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
