
package frigo.asteroids.component;

public class Acceleration extends Vector {

    public Acceleration (double ddx, double ddy) {
        super(ddx, ddy);
    }

    @Override
    public Acceleration add (Vector addend) {
        return (Acceleration) super.add(addend);
    }

    @Override
    public Acceleration mul (double scalar) {
        return (Acceleration) super.mul(scalar);
    }

    @Override
    public Acceleration rotate (double radians) {
        return (Acceleration) super.rotate(radians);
    }

    @Override
    protected Vector create (double xval, double yval) {
        return new Acceleration(xval, yval);
    }

}
