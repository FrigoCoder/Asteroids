
package frigo.asteroids.core.storage;

import static org.hamcrest.Matchers.instanceOf;
import static org.junit.Assert.assertThat;

import org.junit.Test;

import frigo.asteroids.core.storage.TroveComponentStorage;
import frigo.asteroids.core.storage.TroveComponentStorageFactory;

public class TroveComponentStorageFactoryTest {

    @Test
    public void factory_creates_storage () {
        TroveComponentStorageFactory factory = new TroveComponentStorageFactory();
        assertThat(factory.create(0), instanceOf(TroveComponentStorage.class));
    }

}
