
package frigo.asteroids.core.storage;

import frigo.asteroids.core.Component;

public class TroveComponentStorageFactory {

    public <T extends Component> TroveComponentStorage<T> create (int entities) {
        return new TroveComponentStorage<>();
    }

}
