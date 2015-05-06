
package frigo.asteroids.component;

import frigo.asteroids.core.Component;
import frigo.asteroids.core.component.ComponentId;

public class Background extends Component {

    public static final ComponentId<Background> ID = new ComponentId<>(Background.class);
    public static final Background BACKGROUND = new Background();

    private Background () {
    }

}
