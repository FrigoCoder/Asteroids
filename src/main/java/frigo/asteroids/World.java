
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

    public Set<Entity> getEntities () {
        return entities;
    }

    public void addUpdater (Updater updater) {
        updaters.add(updater);
    }

    public void init () {
        for( Updater updater : updaters ){
            updater.init(this);
        }
    }

    public void update () {
        for( Updater updater : updaters ){
            updater.update(this);
        }
    }

}
