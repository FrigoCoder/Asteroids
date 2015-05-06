
package frigo.asteroids.component;

import frigo.asteroids.core.Component;
import frigo.asteroids.core.component.ComponentId;

public class Thrustable extends Component {

    public static final ComponentId<Thrustable> ID = new ComponentId<>(Thrustable.class);

    public final double thrust;
    public final double angularThrust;

    public Thrustable (double thrust, double angularThrust) {
        this.thrust = thrust;
        this.angularThrust = angularThrust;
    }

}
