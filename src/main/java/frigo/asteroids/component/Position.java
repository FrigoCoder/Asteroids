
package frigo.asteroids.component;

import frigo.asteroids.core.Component;

public class Position extends Component {

    public final double x;
    public final double y;

    public Position (double x, double y) {
        this.x = x;
        this.y = y;
    }

    public Position add (Vector vector) {
        return new Position(x + vector.x, y + vector.y);
    }

    public Position add (Vector velocity, double elapsed) {
        return add(velocity.mul(elapsed));
    }

    public Vector sub (Position substrahend) {
        return new Vector(x - substrahend.x, y - substrahend.y);
    }

}
