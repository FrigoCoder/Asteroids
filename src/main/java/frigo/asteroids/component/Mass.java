
package frigo.asteroids.component;

import frigo.asteroids.core.Component;

public class Mass extends Component {

    public static final int ID = System.identityHashCode(Mass.class);

    public final double kg;

    public Mass (double kg) {
        this.kg = kg;
    }

}
