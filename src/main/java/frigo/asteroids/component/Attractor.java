
package frigo.asteroids.component;

import frigo.asteroids.core.Component;
import frigo.asteroids.core.component.ComponentId;

public class Attractor extends Component {

    public static final ComponentId<Attractor> ID = new ComponentId<>(Attractor.class);
    public static final Attractor ATTRACTOR = new Attractor();

    private Attractor () {
    }

}
