
package frigo.asteroids.component;

import frigo.asteroids.core.Component;

public class Damage extends Component {

    public static final int ID = System.identityHashCode(Damage.class);

    public final double damage;

    public Damage (double damage) {
        this.damage = damage;
    }

}
