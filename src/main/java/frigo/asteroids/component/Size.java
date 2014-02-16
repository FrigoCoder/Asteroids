
package frigo.asteroids.component;

import frigo.asteroids.core.Component;
import frigo.asteroids.core.ComponentId;

public class Size extends Component {

    public static final ComponentId<Size> ID = new ComponentId<>(Size.class);

    public final double size;

    public Size (double size) {
        this.size = size;
    }

}
