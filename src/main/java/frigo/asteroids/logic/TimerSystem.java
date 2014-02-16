
package frigo.asteroids.logic;

import frigo.asteroids.component.Timer;
import frigo.asteroids.core.Aspect;
import frigo.asteroids.core.Entity;
import frigo.asteroids.core.Logic;

public class TimerSystem extends Logic {

    private Aspect aspect = Aspect.allOf(Timer.ID);

    @Override
    public void update (double elapsedSeconds) {
        for( Entity entity : world.getEntitiesFor(aspect) ){
            Timer timer = entity.get(Timer.ID);
            timer.countDown(elapsedSeconds);
            if( timer.elapsed() ){
                entity.add(timer.id, timer.emitted());
                entity.remove(Timer.ID);
            }
        }
    }
}
