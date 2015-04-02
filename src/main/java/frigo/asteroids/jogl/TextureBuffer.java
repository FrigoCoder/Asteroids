
package frigo.asteroids.jogl;

import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStream;
import java.util.Collection;
import java.util.HashMap;
import java.util.Map;

import com.google.common.annotations.VisibleForTesting;
import com.jogamp.opengl.GLException;
import com.jogamp.opengl.util.texture.Texture;
import com.jogamp.opengl.util.texture.TextureIO;

import frigo.asteroids.util.Undeclared;

public class TextureBuffer {

    private Map<String, Texture> textures = new HashMap<>();

    public Texture get (String filename) {
        if( !textures.containsKey(filename) ){
            textures.put(filename, newTexture(filename));
        }
        return textures.get(filename);
    }

    public Collection<Texture> getTextures () {
        return textures.values();
    }

    @VisibleForTesting
    Texture newTexture (String filename) {
        return newTexture(getInputStream(filename));
    }

    private InputStream getInputStream (String filename) {
        InputStream stream = getClass().getClassLoader().getResourceAsStream(filename);
        if( stream == null ){
            throw Undeclared.propagate(new FileNotFoundException(filename));
        }
        return stream;
    }

    private Texture newTexture (InputStream stream) {
        try{
            return TextureIO.newTexture(stream, true, null);
        }catch( GLException | IOException e ){
            throw Undeclared.propagate(e);
        }
    }

}
