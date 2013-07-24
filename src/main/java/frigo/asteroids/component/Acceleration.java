
package frigo.asteroids.component;

public class Acceleration extends Vector {

    public Acceleration (double ddx, double ddy) {
        super(ddx, ddy);
    }

    @Override
    public Acceleration add (Vector vector) {
        return new Acceleration(x + vector.x, y + vector.y);
    }

    @Override
    public Acceleration mul (double scalar) {
        return new Acceleration(x * scalar, y * scalar);
    }
}
