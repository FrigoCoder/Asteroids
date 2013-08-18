
package frigo.asteroids.component;

import static java.lang.Math.sqrt;

import java.lang.reflect.Constructor;

import com.google.common.base.Throwables;

import frigo.asteroids.core.Component;

public class Vector extends Component {

    public final double x;
    public final double y;

    public Vector (double x, double y) {
        this.x = x;
        this.y = y;
    }

    public double length () {
        return sqrt(x * x + y * y);
    }

    public <T extends Vector> T add (Vector addend) {
        return create(x + addend.x, y + addend.y);
    }

    public <T extends Vector> T mul (double scalar) {
        return create(x * scalar, y * scalar);
    }

    public <T extends Vector> T rotate (double radians) {
        double c = Math.cos(radians);
        double s = Math.sin(radians);
        return create(c * x - s * y, s * x + c * y);
    }

    protected <T extends Vector> T create (double xval, double yval) {
        try{
            Constructor<? extends Vector> constructor = getClass().getConstructor(Double.TYPE, Double.TYPE);
            return (T) constructor.newInstance(xval, yval);
        }catch( Exception e ){
            throw Throwables.propagate(e);
        }
    }

}
