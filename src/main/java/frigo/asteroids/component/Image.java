
package frigo.asteroids.component;

import frigo.asteroids.core.Component;

public class Image extends Component {

    public final String filename;
    public final int order;

    public Image (String filename) {
        this(filename, 0);
    }

    public Image (String filename, int order) {
        this.filename = filename;
        this.order = order;
    }

}
