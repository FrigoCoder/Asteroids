
package frigo.asteroids.logic.gravity;

import frigo.asteroids.component.Mass;
import frigo.asteroids.component.Position;
import frigo.asteroids.component.Vector;
import frigo.asteroids.core.Entity;

public class NewtonianGravity implements GravityCalculator {

    public static final double G = 6.6738480 * Math.pow(10, -11);

    @Override
    public Vector getDirectionalAcceleration (Entity attractor, Entity attracted) {
        double m1 = attractor.get(Mass.class).kg;
        Vector direction = attractor.get(Position.class).sub(attracted.get(Position.class));
        double r2 = direction.x * direction.x + direction.y * direction.y;
        r2 = r2 <= 0.0 ? Double.MAX_VALUE : r2;
        double acceleration = G * m1 / r2;
        return direction.mul(acceleration);
    }

}
