
package frigo.asteroids.logic;

import frigo.asteroids.component.Timer;
import frigo.asteroids.core.Aspect;
import frigo.asteroids.core.Entity;
import frigo.asteroids.core.Logic;
import frigo.asteroids.core.World;

public class TimerSystem extends Logic {

    private Aspect aspect = Aspect.allOf(Timer.class);

    @Override
    public void update (World world, double elapsedSeconds) {
        for( Entity entity : world.getEntitiesFor(aspect) ){
            Timer timer = entity.get(Timer.class);
            timer.countDown(elapsedSeconds);
            if( timer.elapsed() ){
                entity.set(timer.emitted());
            }
        }
    }
}
