
package frigo.asteroids.core;

import java.util.LinkedList;
import java.util.List;

public class SystemManager {

    private List<Logic> logics = new LinkedList<>();

    public void addLogic (Logic logic) {
        logics.add(logic);
    }

    public void init (World world) {
        for( Logic logic : logics ){
            logic.init(world);
        }
    }

    public void update (World world, double elapsedSeconds) {
        for( Logic logic : logics ){
            logic.update(world, elapsedSeconds);
        }
    }

}
