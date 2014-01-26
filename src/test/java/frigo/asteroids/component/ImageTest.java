
package frigo.asteroids.component;

import static org.hamcrest.Matchers.is;
import static org.junit.Assert.assertThat;

import org.junit.Test;

public class ImageTest {

    @Test
    public void image_file_name_is_stored () {
        String filename = "doesnotexist.png";
        Image image = new Image(filename);
        assertThat(image.filename, is(filename));
    }

}
