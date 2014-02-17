
package frigo.asteroids.component;

import frigo.asteroids.core.Component;
import frigo.asteroids.core.ComponentId;

public class Collidable extends Component {

    public static final ComponentId<Collidable> ID = new ComponentId<>(Collidable.class);
    public static final Collidable COLLIDABLE = new Collidable();

    private Collidable () {
    }

}
