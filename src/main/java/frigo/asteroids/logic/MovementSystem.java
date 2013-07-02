
package frigo.asteroids.logic;

import frigo.asteroids.component.Position;
import frigo.asteroids.component.Speed;
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
            Speed speed = entity.get(Speed.class);
            Position position = entity.get(Position.class);
            position.add(speed);
        }
    }

}
