
package frigo.asteroids.component;

import frigo.asteroids.core.Component;
import frigo.asteroids.core.component.ComponentId;

public class Damage extends Component {

    public static final ComponentId<Damage> ID = new ComponentId<>(Damage.class);

    public final double damage;

    public Damage (double damage) {
        this.damage = damage;
    }

}
