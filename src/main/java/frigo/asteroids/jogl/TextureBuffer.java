
package frigo.asteroids.jogl;

import java.io.IOException;
import java.io.InputStream;
import java.util.HashMap;
import java.util.Map;

import javax.media.opengl.GLException;

import com.google.common.annotations.VisibleForTesting;
import com.google.common.base.Throwables;
import com.jogamp.opengl.util.texture.Texture;
import com.jogamp.opengl.util.texture.TextureIO;

public class TextureBuffer {

    private Map<String, Texture> textures = new HashMap<>();

    public Texture get (String filename) {
        if( !textures.containsKey(filename) ){
            textures.put(filename, newTexture(filename));
        }
        return textures.get(filename);
    }

    @VisibleForTesting
    Texture newTexture (String filename) {
        return newTexture(getInputStream(filename));
    }

    private Texture newTexture (InputStream stream) {
        try{
            return TextureIO.newTexture(stream, true, null);
        }catch( GLException | IOException e ){
            throw Throwables.propagate(e);
        }
    }

    private InputStream getInputStream (String filename) {
        return getClass().getClassLoader().getResourceAsStream(filename);
    }
}
