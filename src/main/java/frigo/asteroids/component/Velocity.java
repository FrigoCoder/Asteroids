
package frigo.asteroids.component;

import frigo.asteroids.core.Component;

public class Velocity extends Component {

    public final double dx;
    public final double dy;

    public Velocity (double dx, double dy) {
        this.dx = dx;
        this.dy = dy;
    }

    public Velocity add (Acceleration acceleration) {
        return new Velocity(dx + acceleration.ddx, dy + acceleration.ddy);
    }

    public Velocity mul (double elapsed) {
        return new Velocity(dx * elapsed, dy * elapsed);
    }

}
