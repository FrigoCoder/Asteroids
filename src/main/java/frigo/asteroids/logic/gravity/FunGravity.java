
package frigo.asteroids.logic.gravity;

import frigo.asteroids.component.Mass;
import frigo.asteroids.component.Planar;
import frigo.asteroids.core.Entity;
import frigo.asteroids.core.Vector;

public class FunGravity implements GravityCalculator {

    public static final double G = 6.6738480 * Math.pow(10, -11);

    @Override
    public Vector getDirectionalAcceleration (Entity attractor, Entity attracted) {
        double m1 = attractor.getDouble(Mass.ID);
        Planar planar1 = attractor.get(Planar.ID);
        Planar planar2 = attracted.get(Planar.ID);
        Vector direction = planar1.position.sub(planar2.position);
        double r = direction.length();
        r = r <= 0 ? Double.MAX_VALUE : r;
        double acceleration = G * m1 / r;
        return direction.mul(acceleration);
    }

}
