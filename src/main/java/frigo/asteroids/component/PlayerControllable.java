
package frigo.asteroids.component;

import frigo.asteroids.core.Component;

public class PlayerControllable extends Component {

    public final double thrust;
    public final double angularThrust;

    public PlayerControllable (double thrust, double angularThrust) {
        this.thrust = thrust;
        this.angularThrust = angularThrust;
    }

}
