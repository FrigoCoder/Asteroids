
package frigo.asteroids.component;

public class Acceleration extends Vector {

    public Acceleration (double ddx, double ddy) {
        super(ddx, ddy);
    }

    @SuppressWarnings("unchecked")
    @Override
    public Acceleration add (Vector addend) {
        return super.add(addend);
    }

    @SuppressWarnings("unchecked")
    @Override
    public Acceleration mul (double scalar) {
        return super.mul(scalar);
    }

    @SuppressWarnings("unchecked")
    @Override
    public Acceleration rotate (double radians) {
        return super.rotate(radians);
    }

    @SuppressWarnings("unchecked")
    @Override
    protected Vector create (double xval, double yval) {
        return new Vector(xval, yval);
    }

}
