
package frigo.asteroids.core;

public interface ComponentStorageFactory {

    <T extends Component> ComponentStorage<T> create (int entities);

}
