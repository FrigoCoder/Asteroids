
package frigo.asteroids.jogl;

import java.io.IOException;
import java.net.URL;
import java.util.HashMap;
import java.util.Map;

import com.google.common.annotations.VisibleForTesting;
import com.google.common.base.Throwables;
import com.jogamp.opengl.util.texture.Texture;
import com.jogamp.opengl.util.texture.TextureIO;

public class TextureBuffer {

    private Map<String, Texture> textures = new HashMap<>();

    public Texture get (String filename) {
        if( !textures.containsKey(filename) ){
            textures.put(filename, newTexture(ResourceLoader.getUrl(filename)));
        }
        return textures.get(filename);
    }

    @VisibleForTesting
    Texture newTexture (URL url) {
        try{
            return TextureIO.newTexture(url, true, null);
        }catch( IOException e ){
            throw Throwables.propagate(e);
        }
    }

}
