
package frigo.asteroids;

import java.util.HashSet;
import java.util.LinkedList;
import java.util.List;
import java.util.Set;

public class World {

    private Set<Entity> entities = new HashSet<>();
    private List<Logic> updaters = new LinkedList<>();

    public void addEntity (Entity entity) {
        entities.add(entity);
    }

    public Set<Entity> getEntities () {
        return entities;
    }

    public void addUpdater (Logic updater) {
        updaters.add(updater);
    }

    public void init () {
        for( Logic updater : updaters ){
            updater.init(this);
        }
    }

    public void update () {
        for( Logic updater : updaters ){
            updater.update(this);
        }
    }

}
