
package frigo.asteroids.logic;

import static java.lang.Math.pow;

import java.util.Set;

import frigo.asteroids.component.Acceleration;
import frigo.asteroids.component.Attractor;
import frigo.asteroids.component.Mass;
import frigo.asteroids.component.Position;
import frigo.asteroids.component.Vector;
import frigo.asteroids.components.Attractable;
import frigo.asteroids.core.Aspect;
import frigo.asteroids.core.Entity;
import frigo.asteroids.core.Logic;
import frigo.asteroids.core.World;

public class GravitySystem implements Logic {

    private static final double G = 6.6738480 * pow(10, -11);

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
            for( Entity affected : attractables ){
                handlePairwiseGravity(attractor, affected);
            }
        }
    }

    private void handlePairwiseGravity (Entity attractor, Entity attracted) {
        Vector vector = attractor.get(Position.class).sub(attracted.get(Position.class));
        double mAttractor = attractor.get(Mass.class).mass;
        double mAttracted = attracted.get(Mass.class).mass;
        double rDistance = vector.length();
        double F = rDistance == 0 ? 0 : G * mAttracted * mAttractor / rDistance;
        double a = F / mAttracted;
        attracted.set(attracted.get(Acceleration.class).add(vector.mul(a)));
    }

}
