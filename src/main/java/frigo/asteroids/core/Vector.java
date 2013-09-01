
package frigo.asteroids.core;

import static java.lang.Math.sqrt;

public class Vector extends Value {

    public static final Vector NULL = vector(0, 0);
    public static final Vector UNIT_X = vector(1, 0);
    public static final Vector UNIT_Y = vector(0, 1);

    public static Vector vector (double x, double y) {
        return new Vector(x, y);
    }

    public final double x;
    public final double y;

    private Vector (double x, double y) {
        this.x = x;
        this.y = y;
    }

    public double length () {
        return sqrt(x * x + y * y);
    }

    public Vector add (Vector addend) {
        return vector(x + addend.x, y + addend.y);
    }

    public Vector sub (Vector subtrahend) {
        return vector(x - subtrahend.x, y - subtrahend.y);
    }

    public Vector mul (double scalar) {
        return vector(x * scalar, y * scalar);
    }

    public Vector div (double scalar) {
        return vector(x / scalar, y / scalar);
    }

    public Vector rotate (double radians) {
        double c = Math.cos(radians);
        double s = Math.sin(radians);
        return vector(c * x - s * y, s * x + c * y);
    }

    public Vector normalize () {
        double length = length();
        if( length == 0 ){
            return this;
        }
        return div(length);
    }

}
