
package frigo.asteroids.component;

import frigo.asteroids.core.Component;

public class Position implements Component {

    public final double x;
    public final double y;

    public Position (double x, double y) {
        this.x = wrap(x, -1, 1);
        this.y = wrap(y, -1, 1);
    }

    private double wrap (double value, double low, double high) {
        double result = value;
        while( result < low ){
            result += high - low;
        }
        while( result >= high ){
            result -= high - low;
        }
        return result;
    }

    public Position add (Speed speed) {
        return new Position(x + speed.dx, y + speed.dy);
    }

}
