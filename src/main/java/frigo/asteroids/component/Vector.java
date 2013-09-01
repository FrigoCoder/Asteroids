
package frigo.asteroids.component;

import static java.lang.Math.sqrt;
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

    public Vector add (Vector addend) {
        return create(x + addend.x, y + addend.y);
    }

    public Vector sub (Vector subtrahend) {
        return create(x - subtrahend.x, y - subtrahend.y);
    }

    public Vector mul (double scalar) {
        return create(x * scalar, y * scalar);
    }

    public Vector div (double scalar) {
        return create(x / scalar, y / scalar);
    }

    public Vector rotate (double radians) {
        double c = Math.cos(radians);
        double s = Math.sin(radians);
        return create(c * x - s * y, s * x + c * y);
    }

    private static Vector create (double xval, double yval) {
        return new Vector(xval, yval);
    }

}
