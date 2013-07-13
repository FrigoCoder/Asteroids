
package frigo.asteroids.component;

import frigo.asteroids.core.Component;

public class Renderable extends Component {

    public final double size;
    public final double r;
    public final double g;
    public final double b;

    public Renderable (double size) {
        this(size, 1.0, 1.0, 1.0);
    }

    public Renderable (double size, double r, double g, double b) {
        this.size = size;
        this.r = r;
        this.g = g;
        this.b = b;
    }

}
