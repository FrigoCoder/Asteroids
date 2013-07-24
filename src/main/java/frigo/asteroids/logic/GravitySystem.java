
package frigo.asteroids.logic;

import java.util.Set;

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

    private Aspect attractorAspect = new Aspect().all(Attractor.class, Mass.class, Position.class);
    private Aspect attractableAspect = new Aspect().all(Acceleration.class, Attractable.class, Mass.class,
        Position.class);

    @Override
    public void init (World world) {
    }

    @Override
    public void update (World world, double elapsedSeconds) {
        Set<Entity> attractors = world.getEntitiesFor(attractorAspect);
        Set<Entity> attractables = world.getEntitiesFor(attractableAspect);
        for( Entity attractor : attractors ){
            for( Entity attracted : attractables ){
                Vector acceleration = new PairwiseGravitation(attractor, attracted).getDirectionalAcceleration();
                attracted.set(attracted.get(Acceleration.class).add(acceleration));
            }
        }
    }

}
