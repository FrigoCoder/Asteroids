
package frigo.asteroids.logic.gravity;

import frigo.asteroids.component.Mass;
import frigo.asteroids.component.Planar;
import frigo.asteroids.core.Entity;
import frigo.asteroids.core.Vector;
import frigo.asteroids.core.World;

public class NewtonianGravity implements GravityCalculator {

    public static final double G = 6.6738480 * Math.pow(10, -11);

    public NewtonianGravity (World world) {
    }

    @Override
    public Vector getDirectionalAcceleration (Entity attractor, Entity attracted) {
        double m1 = attractor.get(Mass.class).kg;
        Vector direction = attractor.get(Planar.class).position.sub(attracted.get(Planar.class).position);
        double r2 = direction.x * direction.x + direction.y * direction.y;
        r2 = r2 <= 0 ? Double.MAX_VALUE : r2;
        double acceleration = G * m1 / r2;
        return direction.mul(acceleration);
    }

}
