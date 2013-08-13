
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
        return new Vector(x + addend.x, y + addend.y);
    }

    public Vector mul (double scalar) {
        return new Vector(x * scalar, y * scalar);
    }

    public Vector rotate (double radians) {
        double c = Math.cos(radians);
        double s = Math.sin(radians);
        return new Vector(c * x - s * y, s * x + c * y);
    }

}
