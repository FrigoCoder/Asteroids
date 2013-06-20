
package frigo.asteroids;


public class Position extends Component {

    public double x;
    public double y;

    public Position (double x, double y) {
        this.x = x;
        this.y = y;
        wrap();
    }

    public void add (Speed speed) {
        x += speed.dx;
        y += speed.dy;
        wrap();
    }

    private void wrap () {
        while( x < -1 ){
            x += 2;
        }
        while( x >= 1 ){
            x -= 2;
        }
        while( y < -1 ){
            y += 2;
        }
        while( y >= 1 ){
            y -= 2;
        }
    }

}
