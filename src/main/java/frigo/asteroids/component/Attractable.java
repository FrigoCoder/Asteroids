
package frigo.asteroids.component;

import frigo.asteroids.core.Component;

public class Attractable extends Component {

    public static final int ID = System.identityHashCode(Attractable.class);

    public static final Attractable ATTRACTABLE = new Attractable();

    private Attractable () {
    }

}
