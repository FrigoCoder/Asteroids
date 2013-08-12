
package frigo.asteroids.component;

import static org.hamcrest.Matchers.is;
import static org.junit.Assert.assertThat;

import org.junit.Test;

public class TextureNameTest {

    @Test
    public void texture_file_name_is_stored () {
        String filename = "doesnotexist.png";
        TextureName textureName = new TextureName(filename);
        assertThat(textureName.filename, is(filename));
    }

}
