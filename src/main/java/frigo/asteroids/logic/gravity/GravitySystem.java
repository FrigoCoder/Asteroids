
package frigo.asteroids.logic.gravity;

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

    private static final Aspect ATTRACTOR_ASPECT = Aspect.allOf(Attractor.class, Mass.class, Position.class);
    private static final Aspect ATTRACTABLE_ASPECT = Aspect.allOf(Acceleration.class, Attractable.class, Mass.class,
        Position.class);

    private GravityCalculator calculator;

    public GravitySystem (GravityCalculator calculator) {
        this.calculator = calculator;
    }

    @Override
    public void init (World world) {
    }

    @Override
    public void update (World world, double elapsedSeconds) {
        Set<Entity> attractors = world.getEntitiesFor(ATTRACTOR_ASPECT);
        Set<Entity> attractables = world.getEntitiesFor(ATTRACTABLE_ASPECT);
        for( Entity attractor : attractors ){
            for( Entity attracted : attractables ){
                Vector acceleration = calculator.getDirectionalAcceleration(attractor, attracted);
                world.setComponent(attracted, world.getComponent(attracted, Acceleration.class).add(acceleration));
            }
        }
    }

}
