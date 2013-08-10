
package frigo.asteroids.component;

import frigo.asteroids.core.Component;

public class Renderable extends Component {

    public final double size;
    public final String texture;

    public Renderable (double size) {
        this.size = size;
        texture = null;
    }

    public Renderable (double size, String texture) {
        this.size = size;
        this.texture = texture;
    }

}
