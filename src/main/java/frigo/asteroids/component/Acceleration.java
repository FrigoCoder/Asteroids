
package frigo.asteroids.component;

import frigo.asteroids.core.Component;

public class Acceleration extends Component {

    public final double ddx;
    public final double ddy;

    public Acceleration (double ddx, double ddy) {
        this.ddx = ddx;
        this.ddy = ddy;
    }

    public Acceleration add (double x, double y) {
        return new Acceleration(ddx + x, ddy + y);
    }

    public Acceleration add (Vector vector) {
        return new Acceleration(ddx + vector.x, ddy + vector.y);
    }
}
