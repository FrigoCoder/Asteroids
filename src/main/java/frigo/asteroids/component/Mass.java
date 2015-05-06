
package frigo.asteroids.component;

import frigo.asteroids.core.Component;
import frigo.asteroids.core.component.ComponentId;

public class Mass extends Component {

    public static final ComponentId<Mass> ID = new ComponentId<>(Mass.class);

    public final double kg;

    public Mass (double kg) {
        this.kg = kg;
    }

}
