
package frigo.asteroids.jogl;

import java.io.IOException;
import java.io.InputStream;
import java.net.URL;

import com.google.common.base.Throwables;

public class ResourceLoader {

    public static URL getUrl (String filename) {
        URL resource = ClassLoader.getSystemResource(filename);
        if( resource == null ){
            throw new IllegalArgumentException("File " + filename + " does not exist");
        }
        return resource;
    }

    public static InputStream getInputStream (String filename) {
        try{
            return getUrl(filename).openStream();
        }catch( IOException e ){
            throw Throwables.propagate(e);
        }
    }

}
