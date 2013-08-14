
package frigo.asteroids.component;

import frigo.asteroids.core.Component;

public class PlayerControllable extends Component {

    public final double thrust;
    public final double rotation;

    public PlayerControllable (double thrust, double rotation) {
        this.thrust = thrust;
        this.rotation = rotation;
    }

}
