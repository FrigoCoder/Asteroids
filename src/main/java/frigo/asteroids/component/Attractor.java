
package frigo.asteroids.component;

import frigo.asteroids.core.Component;

public class Attractor extends Component {

    public static final int ID = System.identityHashCode(Attractor.class);

    public static final Attractor ATTRACTOR = new Attractor();

    private Attractor () {
    }

}
