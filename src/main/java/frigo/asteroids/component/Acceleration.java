
package frigo.asteroids.component;

public class Acceleration extends Vector {

    public Acceleration (double ddx, double ddy) {
        super(ddx, ddy);
    }

    @SuppressWarnings("unchecked")
    @Override
    protected Vector create (double xval, double yval) {
        return new Vector(xval, yval);
    }

}
