
package frigo.asteroids.component;

import frigo.asteroids.core.Component;
import frigo.asteroids.core.component.ComponentId;

public class Attractable extends Component {

    public static final ComponentId<Attractable> ID = new ComponentId<>(Attractable.class);
    public static final Attractable ATTRACTABLE = new Attractable();

    private Attractable () {
    }

}
