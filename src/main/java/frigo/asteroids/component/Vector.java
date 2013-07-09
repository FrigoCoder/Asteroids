
package frigo.asteroids.component;

import static java.lang.Math.sqrt;

public class Vector {

    public final double x;
    public final double y;

    public Vector (double x, double y) {
        this.x = x;
        this.y = y;
    }

    public double length () {
        return sqrt(x * x + y * y);
    }

    public Vector mul (double scalar) {
        return new Vector(x * scalar, y * scalar);
    }

}
