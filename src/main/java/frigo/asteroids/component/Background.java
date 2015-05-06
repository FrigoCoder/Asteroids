
package frigo.asteroids.component;

import frigo.asteroids.core.Component;

public class Background extends Component {

    public static final int ID = System.identityHashCode(Background.class);

    public static final Background BACKGROUND = new Background();

    private Background () {
    }

}
