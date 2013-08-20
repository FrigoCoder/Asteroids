
package frigo.asteroids.core;

public class TroveComponentStorageFactory implements ComponentStorageFactory {

    @Override
    public <T extends Component> ComponentStorage<T> create (int entities) {
        return new TroveComponentStorage<>();
    }

}
