
package frigo.asteroids.core;

public class ArrayComponentStorageFactory implements ComponentStorageFactory {

    @Override
    public <T extends Component> ComponentStorage<T> create () {
        return new ArrayComponentStorage<>();
    }

}
