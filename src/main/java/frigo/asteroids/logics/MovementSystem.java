
package frigo.asteroids.logics;

import frigo.asteroids.components.Position;
import frigo.asteroids.components.Speed;
import frigo.asteroids.core.Aspect;
import frigo.asteroids.core.Entity;
import frigo.asteroids.core.Logic;
import frigo.asteroids.core.World;

public class MovementSystem implements Logic {

    private Aspect aspect = Aspect.all(Speed.class, Position.class);

    @Override
    public void init (World world) {
    }

    @Override
    public void update (World world) {
        for( Entity entity : world.getEntitiesFor(aspect) ){
            Speed speed = entity.getComponent(Speed.class);
            Position position = entity.getComponent(Position.class);
            position.add(speed);
        }
    }

}
