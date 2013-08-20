
package frigo.asteroids.core;

import static org.hamcrest.Matchers.instanceOf;
import static org.junit.Assert.assertThat;

import org.junit.Test;

public class TroveComponentStorageFactoryTest {

    @Test
    public void factory_creates_storage () {
        TroveComponentStorageFactory factory = new TroveComponentStorageFactory();
        assertThat(factory.create(0), instanceOf(TroveComponentStorage.class));
    }

}
