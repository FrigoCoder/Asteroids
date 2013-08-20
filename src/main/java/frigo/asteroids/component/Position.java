
package frigo.asteroids.component;

public class Position extends Vector {

    public Position (double x, double y) {
        super(x, y);
    }

    @Override
    public Position add (Vector addend) {
        return (Position) super.add(addend);
    }

    @Override
    public Position sub (Vector substrahend) {
        return (Position) super.sub(substrahend);
    }

    @Override
    public Position mul (double scalar) {
        return (Position) super.mul(scalar);
    }

    @Override
    public Position rotate (double radians) {
        return (Position) super.rotate(radians);
    }

    @Override
    protected Position create (double xval, double yval) {
        return new Position(xval, yval);
    }

}
