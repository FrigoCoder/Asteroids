
package frigo.asteroids.logic.gravity;

import java.util.List;

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

    private GravityCalculator calculator;
    private Aspect attractorAspect = Aspect.allOf(Attractor.class, Mass.class, Position.class);
    private Aspect attractedAspect = Aspect.allOf(Attractable.class, Mass.class, Position.class, Acceleration.class);

    public GravitySystem (GravityCalculator calculator) {
        this.calculator = calculator;
    }

    @Override
    public void init (World world) {
    }

    @Override
    public void update (World world, double elapsedSeconds) {
        List<Entity> attractors = world.getEntitiesFor(attractorAspect);
        List<Entity> attractables = world.getEntitiesFor(attractedAspect);
        for( Entity attractor : attractors ){
            for( Entity attracted : attractables ){
                Vector acceleration = calculator.getDirectionalAcceleration(attractor, attracted);
                world.set(attracted, world.get(attracted, Acceleration.class).add(acceleration));
            }
        }
    }

}
