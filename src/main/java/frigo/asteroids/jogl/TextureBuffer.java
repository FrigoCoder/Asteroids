
package frigo.asteroids.jogl;

import java.io.File;
import java.io.IOException;
import java.util.HashMap;
import java.util.Map;

import javax.media.opengl.GLException;

import com.google.common.base.Throwables;
import com.jogamp.opengl.util.texture.Texture;
import com.jogamp.opengl.util.texture.TextureIO;

public class TextureBuffer {

    private Map<String, Texture> textures = new HashMap<>();

    public Texture get (String filename) {
        if( !textures.containsKey(filename) ){
            File file = new File(ClassLoader.getSystemResource(filename).getFile());
            try{
                Texture texture = TextureIO.newTexture(file, true);
                textures.put(filename, texture);
            }catch( GLException | IOException e ){
                throw Throwables.propagate(e);
            }
        }
        return textures.get(filename);
    }
}
