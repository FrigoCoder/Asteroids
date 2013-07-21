
package frigo.asteroids.logic;

import static java.lang.Math.pow;

import com.google.common.annotations.VisibleForTesting;

import frigo.asteroids.component.Mass;
import frigo.asteroids.component.Position;
import frigo.asteroids.component.Vector;
import frigo.asteroids.core.Entity;

public class PairwiseGravitation {

    @VisibleForTesting
    static final double G = 6.6738480 * pow(10, -11);

    private Vector direction;
    private double mAttractor;
    private double mAttracted;

    public PairwiseGravitation (Entity attractor, Entity attracted) {
        direction = attractor.get(Position.class).sub(attracted.get(Position.class));
        mAttractor = attractor.get(Mass.class).mass;
        mAttracted = attracted.get(Mass.class).mass;
    }

    public Vector getDirectionalAcceleration () {
        double a = getAcceleration();
        return direction.mul(a);
    }

    public double getAcceleration () {
        double F = getAttractiveForce();
        double a = F / mAttracted;
        return a;
    }

    public double getAttractiveForce () {
        double r = direction.length();
        double F = r == 0 ? 0 : PairwiseGravitation.G * mAttractor * mAttracted / (r * r);
        return F;
    }

}
