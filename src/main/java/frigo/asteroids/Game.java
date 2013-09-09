
package frigo.asteroids;

import java.applet.Applet;

import com.google.common.base.Throwables;
import com.jogamp.common.jvm.JNILibLoaderBase;

import frigo.asteroids.core.World;
import frigo.asteroids.jogl.JOGLRunner;
import frigo.asteroids.jogl.JoglNativeLoader;
import frigo.asteroids.jogl.ResourceLoader;

public class Game extends Applet {

    static{
        System.setOut(new LoggingPrintStream(System.out));
        System.setErr(new LoggingPrintStream(System.err));

        try{
            System.getProperties().load(ResourceLoader.getInputStream("asteroids.properties"));
        }catch( Exception e ){
            throw Throwables.propagate(e);
        }

        JNILibLoaderBase.setLoadingAction(new JoglNativeLoader());
    }

    public static void main (String[] args) {
        Game game = new Game();
        game.init();
        game.start();
    }

    private JOGLRunner runner;

    @Override
    public void init () {
        World world = new AsteroidsWorldFactory().createWorld();
        runner = new JOGLRunner(world, 1024, 768, 1000);
    }

    @Override
    public void destroy () {

    }

    @Override
    public void start () {
        runner.start();
    }

    @Override
    public void stop () {
        runner.stop();
    }

}
