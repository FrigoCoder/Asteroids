
package frigo.asteroids.logic;

import static java.lang.Math.pow;

import java.util.Set;

import com.google.common.annotations.VisibleForTesting;

import frigo.asteroids.component.Acceleration;
import frigo.asteroids.component.Attractable;
import frigo.asteroids.component.Attractor;
import frigo.asteroids.component.Mass;
import frigo.asteroids.component.Position;
import frigo.asteroids.component.Vector;
import frigo.asteroids.core.Aspect;
import frigo.asteroids.core.Entity;
import frigo.asteroids.core.Logic;
import frigo.asteroids.core.World;

public class GravitySystem implements Logic {

    @VisibleForTesting
    static final double G = 6.6738480 * pow(10, -11);

    private Aspect attractorAspect = Aspect.all(Attractor.class, Mass.class, Position.class);
    private Aspect attractableAspect = Aspect.all(Acceleration.class, Attractable.class, Mass.class, Position.class);

    @Override
    public void init (World world) {
    }

    @Override
    public void update (World world, double elapsedSeconds) {
        Set<Entity> attractors = world.getEntitiesFor(attractorAspect);
        Set<Entity> attractables = world.getEntitiesFor(attractableAspect);
        for( Entity attractor : attractors ){
            for( Entity attracted : attractables ){
                attracted.set(attracted.get(Acceleration.class).add(getDirectionalAcceleration(attractor, attracted)));
            }
        }
    }

    @VisibleForTesting
    Vector getDirectionalAcceleration (Entity attractor, Entity attracted) {
        Vector direction = attractor.get(Position.class).sub(attracted.get(Position.class));
        double a = getAcceleration(attractor, attracted);
        return direction.mul(a);
    }

    @VisibleForTesting
    double getAcceleration (Entity attractor, Entity attracted) {
        double F = getAttractiveForce(attractor, attracted);
        double a = F / attracted.get(Mass.class).mass;
        return a;
    }

    @VisibleForTesting
    double getAttractiveForce (Entity attractor, Entity attracted) {
        double m1 = attractor.get(Mass.class).mass;
        double m2 = attracted.get(Mass.class).mass;
        double r = attractor.get(Position.class).sub(attracted.get(Position.class)).length();
        double F = r == 0 ? 0 : G * m1 * m2 / (r * r);
        return F;
    }

}
