
package frigo.asteroids.component;

import frigo.asteroids.core.Component;

public class Speed extends Component {

    public final double dx;
    public final double dy;

    public Speed (double dx, double dy) {
        this.dx = dx;
        this.dy = dy;
    }

    public Speed mul (double elapsed) {
        return new Speed(dx * elapsed, dy * elapsed);
    }

}
