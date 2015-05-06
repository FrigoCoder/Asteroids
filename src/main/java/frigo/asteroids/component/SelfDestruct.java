
package frigo.asteroids.component;

import frigo.asteroids.core.Component;
import frigo.asteroids.core.component.ComponentId;

public class SelfDestruct extends Component {

    public static final ComponentId<SelfDestruct> ID = new ComponentId<>(SelfDestruct.class);
    public static final SelfDestruct SELF_DESTRUCT = new SelfDestruct();

    private SelfDestruct () {
    }

}
