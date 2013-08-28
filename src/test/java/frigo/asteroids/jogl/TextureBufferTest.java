
package frigo.asteroids.jogl;

import static org.hamcrest.Matchers.is;
import static org.junit.Assert.assertThat;
import static org.mockito.Mockito.doReturn;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.spy;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;

import java.net.URL;

import org.junit.Before;
import org.junit.Rule;
import org.junit.Test;
import org.junit.rules.ExpectedException;

import com.jogamp.opengl.util.texture.Texture;

public class TextureBufferTest {

    private static final String SUN_PNG = "sun.png";

    @Rule
    public ExpectedException thrown = ExpectedException.none();

    private TextureBuffer buffer = spy(new TextureBuffer());
    private Texture texture = mock(Texture.class);
    private URL url;

    @Before
    public void setUp () {
        url = buffer.getUrl(SUN_PNG);
        doReturn(texture).when(buffer).newTexture(url);
    }

    @Test
    public void get_loads_correct_texture () {
        assertThat(buffer.get(SUN_PNG), is(texture));
    }

    @Test
    public void get_throws_exception_if_texture_file_is_not_present () {
        thrown.expect(IllegalArgumentException.class);
        buffer.get("doesnotexist.png");
    }

    @Test
    public void get_loads_texture_if_it_is_not_present_in_buffer () {
        buffer.get(SUN_PNG);
        verify(buffer).newTexture(url);
    }

    @Test
    public void get_loads_texture_from_buffer_if_it_is_present () {
        buffer.get(SUN_PNG);
        buffer.get(SUN_PNG);
        verify(buffer, times(1)).newTexture(url);
    }

    @Test
    public void getUrl_returns_proper_address_for_sun_png () {
        String expected = getFileNameFromSystemResource(SUN_PNG);
        assertThat(url.getFile(), is(expected));
    }

    private String getFileNameFromSystemResource (String filename) {
        return ClassLoader.getSystemResource(filename).getFile();
    }

}
