
package frigo.asteroids.component;

import frigo.asteroids.core.Component;

public class Velocity extends Component {

    public final double dx;
    public final double dy;

    public Velocity (double dx, double dy) {
        this.dx = dx;
        this.dy = dy;
    }

    public Velocity add (Acceleration acceleration, double elapsed) {
        return new Velocity(dx + acceleration.ddx * elapsed, dy + acceleration.ddy * elapsed);
    }

}
