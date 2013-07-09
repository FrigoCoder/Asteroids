
package frigo.asteroids.component;

import frigo.asteroids.core.Component;

public class Acceleration extends Component {

    public final double ddx;
    public final double ddy;

    public Acceleration (double ddx, double ddy) {
        this.ddx = ddx;
        this.ddy = ddy;
    }

}
