
package frigo.asteroids.component;

import frigo.asteroids.core.Component;

public class SelfDestruct extends Component {

    public static final int ID = System.identityHashCode(SelfDestruct.class);

    public static final SelfDestruct SELF_DESTRUCT = new SelfDestruct();

    private SelfDestruct () {
    }

}
