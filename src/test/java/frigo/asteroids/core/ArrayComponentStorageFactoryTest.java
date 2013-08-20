
package frigo.asteroids.core;

import static org.hamcrest.Matchers.instanceOf;
import static org.junit.Assert.assertThat;

import org.junit.Test;

public class ArrayComponentStorageFactoryTest {

    @Test
    public void factory_creates_storage () {
        ComponentStorageFactory factory = new ArrayComponentStorageFactory();
        assertThat(factory.create(0), instanceOf(ArrayComponentStorage.class));
    }

}
