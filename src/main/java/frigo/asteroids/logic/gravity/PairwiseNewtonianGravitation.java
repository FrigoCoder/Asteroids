
package frigo.asteroids.logic.gravity;

import static java.lang.Math.pow;
import frigo.asteroids.component.Mass;
import frigo.asteroids.component.Position;
import frigo.asteroids.component.Vector;
import frigo.asteroids.core.Entity;

public class PairwiseNewtonianGravitation {

    public static final double G = 6.6738480 * pow(10, -11);

    private double mAttractor;
    private double mAttracted;
    private Vector direction;
    private double r2;

    public PairwiseNewtonianGravitation (Entity attractor, Entity attracted) {
        mAttractor = attractor.get(Mass.class).kg;
        mAttracted = attracted.get(Mass.class).kg;
        direction = attractor.get(Position.class).sub(attracted.get(Position.class));
        r2 = direction.x * direction.x + direction.y * direction.y;
        if( r2 <= 0.0 ){
            r2 = Double.POSITIVE_INFINITY;
        }
    }

    public Vector getDirectionalAcceleration () {
        return direction.mul(getAcceleration());
    }

    public double getAcceleration () {
        return G * mAttractor / r2;
    }

    public double getAttractiveForce () {
        return G * mAttractor * mAttracted / r2;
    }

}
