
package frigo.asteroids.component;

import frigo.asteroids.core.Component;

public class Thrustable extends Component {

    public final double thrust;
    public final double angularThrust;

    public Thrustable (double thrust, double angularThrust) {
        this.thrust = thrust;
        this.angularThrust = angularThrust;
    }

}
