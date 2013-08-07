
package frigo.asteroids.logic.gravity;

import frigo.asteroids.component.Mass;
import frigo.asteroids.component.Position;
import frigo.asteroids.component.Vector;
import frigo.asteroids.core.Entity;
import frigo.asteroids.core.World;

public class FunGravity implements GravityCalculator {

    public static final double G = 6.6738480 * Math.pow(10, -11);

    private World world;

    public FunGravity (World world) {
        this.world = world;
    }

    @Override
    public Vector getDirectionalAcceleration (Entity attractor, Entity attracted) {
        double m1 = world.getComponent(attractor, Mass.class).kg;
        Vector direction =
            world.getComponent(attractor, Position.class).sub(world.getComponent(attracted, Position.class));
        double r = direction.length();
        r = r <= 0.0 ? Double.MAX_VALUE : r;
        double acceleration = G * m1 / r;
        return direction.mul(acceleration);
    }

}
