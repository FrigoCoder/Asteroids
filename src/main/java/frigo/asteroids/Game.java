
package frigo.asteroids;

import java.io.FileWriter;
import java.io.IOException;

import com.thoughtworks.xstream.XStream;
import com.thoughtworks.xstream.io.json.JsonHierarchicalStreamDriver;

import frigo.asteroids.core.World;
import frigo.asteroids.jogl.JOGLRunner;

public class Game {

    static{
        System.setProperty("jogamp.debug", "whatever");
    }

    public static void main (String[] args) {
        World world = new AsteroidsWorldFactory().createWorld();

        JOGLRunner runner = new JOGLRunner(world, 1024, 768, 100);
        runner.start();
    }

    protected static void dump (World world, String filename) throws IOException {
        XStream xstream = new XStream(new JsonHierarchicalStreamDriver());
        try( FileWriter writer = new FileWriter(filename) ){
            xstream.toXML(world, writer);
        }
    }

}
