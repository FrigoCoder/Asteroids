
package frigo.asteroids;

import java.io.FileWriter;
import java.io.IOException;

import net.tribe7.opengl.util.GLBootstrap;

import org.apache.commons.lang3.time.StopWatch;

import com.google.common.base.Throwables;
import com.jogamp.common.jvm.JNILibLoaderBase;
import com.thoughtworks.xstream.XStream;
import com.thoughtworks.xstream.io.json.JsonHierarchicalStreamDriver;

import frigo.asteroids.core.World;
import frigo.asteroids.jogl.JOGLRunner;
import frigo.asteroids.jogl.ResourceLoader;

public class Game {

    static{
        System.setOut(new LoggingPrintStream(System.out));
        System.setErr(new LoggingPrintStream(System.err));

        try{
            System.getProperties().load(ResourceLoader.getInputStream("asteroids.properties"));
        }catch( Exception e ){
            throw Throwables.propagate(e);
        }

        JNILibLoaderBase.setLoadingAction(new GLBootstrap());
    }

    public static void main (String[] args) {
        StopWatch watch = new StopWatch();
        watch.start();

        World world = new AsteroidsWorldFactory().createWorld();
        JOGLRunner runner = new JOGLRunner(world, 1024, 768, 1000);
        runner.start();

        watch.stop();
        System.out.println("Startup time: " + watch.toString());
    }

    protected static void dump (World world, String filename) throws IOException {
        XStream xstream = new XStream(new JsonHierarchicalStreamDriver());
        try( FileWriter writer = new FileWriter(filename) ){
            xstream.toXML(world, writer);
        }
    }

}
