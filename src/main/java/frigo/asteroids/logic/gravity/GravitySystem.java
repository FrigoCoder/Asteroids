
package frigo.asteroids.logic.gravity;

import java.util.List;

import frigo.asteroids.component.Attractable;
import frigo.asteroids.component.Attractor;
import frigo.asteroids.component.Mass;
import frigo.asteroids.component.Planar;
import frigo.asteroids.core.Aspect;
import frigo.asteroids.core.Entity;
import frigo.asteroids.core.Logic;
import frigo.asteroids.core.Vector;

public class GravitySystem extends Logic {

    private GravityCalculator calculator;
    private Aspect attractorAspect = Aspect.allOf(Attractor.ID, Mass.ID, Planar.ID);
    private Aspect attractedAspect = Aspect.allOf(Attractable.ID, Mass.ID, Planar.ID);

    public GravitySystem (GravityCalculator calculator) {
        this.calculator = calculator;
    }

    @Override
    public void update (double elapsedSeconds) {
        List<Entity> attractors = world.getEntitiesFor(attractorAspect);
        List<Entity> attractables = world.getEntitiesFor(attractedAspect);
        for( Entity attractor : attractors ){
            for( Entity attracted : attractables ){
                Vector acceleration = calculator.getDirectionalAcceleration(attractor, attracted);
                Planar planar = attracted.get(Planar.ID);
                planar.accelerate(acceleration);
            }
        }
    }

}
