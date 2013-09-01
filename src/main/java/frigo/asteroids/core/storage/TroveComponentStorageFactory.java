
package frigo.asteroids.core.storage;

import frigo.asteroids.core.Component;
import frigo.asteroids.core.ComponentStorage;
import frigo.asteroids.core.ComponentStorageFactory;

public class TroveComponentStorageFactory implements ComponentStorageFactory {

    @Override
    public <T extends Component> ComponentStorage<T> create (int entities) {
        return new TroveComponentStorage<>();
    }

}
