
package frigo.asteroids.core.storage;

import static org.hamcrest.Matchers.instanceOf;
import static org.junit.Assert.assertThat;

import org.junit.Test;

import frigo.asteroids.core.ComponentStorageFactory;
import frigo.asteroids.core.storage.ArrayComponentStorage;
import frigo.asteroids.core.storage.ArrayComponentStorageFactory;

public class ArrayComponentStorageFactoryTest {

    @Test
    public void factory_creates_storage () {
        ComponentStorageFactory factory = new ArrayComponentStorageFactory();
        assertThat(factory.create(0), instanceOf(ArrayComponentStorage.class));
    }

}