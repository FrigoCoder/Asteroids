
package frigo.asteroids.logic.gravity;

import frigo.asteroids.component.Mass;
import frigo.asteroids.component.Planar;
import frigo.asteroids.core.Entity;
import frigo.asteroids.core.Vector;
import frigo.asteroids.core.World;

public class FunGravity implements GravityCalculator {

    public static final double G = 6.6738480 * Math.pow(10, -11);

    private World world;

    public FunGravity (World world) {
        this.world = world;
    }

    @Override
    public Vector getDirectionalAcceleration (Entity attractor, Entity attracted) {
        double m1 = attractor.get(Mass.class).kg;
        Vector direction = attractor.get(Planar.class).position.sub(attracted.get(Planar.class).position);
        double r = direction.length();
        r = r <= 0 ? Double.MAX_VALUE : r;
        double acceleration = G * m1 / r;
        return direction.mul(acceleration);
    }

}
