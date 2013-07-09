
package frigo.asteroids.logic;

import static java.lang.Math.pow;

import java.util.Set;

import frigo.asteroids.component.Acceleration;
import frigo.asteroids.component.Mass;
import frigo.asteroids.component.Position;
import frigo.asteroids.component.Vector;
import frigo.asteroids.core.Aspect;
import frigo.asteroids.core.Entity;
import frigo.asteroids.core.Logic;
import frigo.asteroids.core.World;

public class GravitySystem implements Logic {

    private static final double G = 6.6738480 * pow(10, -11);

    private Aspect affectedAspect = Aspect.all(Acceleration.class, Position.class, Mass.class);
    private Aspect attractorAspect = Aspect.all(Position.class, Mass.class);

    @Override
    public void init (World world) {
    }

    @Override
    public void update (World world, double elapsedSeconds) {
        Set<Entity> affecteds = world.getEntitiesFor(affectedAspect);
        Set<Entity> attractors = world.getEntitiesFor(attractorAspect);
        for( Entity affected : affecteds ){
            for( Entity attractor : attractors ){
                Vector vector = attractor.get(Position.class).sub(affected.get(Position.class));
                double m1 = affected.get(Mass.class).mass;
                double m2 = attractor.get(Mass.class).mass;
                double r = vector.length();
                double F = r == 0 ? 0 : G * m1 * m2 / r;
                affected.set(affected.get(Acceleration.class).add(vector.mul(F)));
            }
        }
    }

}
