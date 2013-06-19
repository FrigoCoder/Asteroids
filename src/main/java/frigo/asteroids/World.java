
package frigo.asteroids;

import java.util.HashSet;
import java.util.LinkedList;
import java.util.List;
import java.util.Set;

public class World {

    private Set<Entity> entities = new HashSet<>();
    private List<Updater> updaters = new LinkedList<>();

    public void addEntity (Entity entity) {
        entities.add(entity);
    }

    public void addUpdater (Updater updater) {
        updaters.add(updater);
    }

    public void update () {
        for( Updater updater : updaters ){
            updater.update(this);
        }
    }

    public Set<Entity> getEntities () {
        return entities;
    }

}
