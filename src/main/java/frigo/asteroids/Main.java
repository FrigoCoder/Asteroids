
package frigo.asteroids;

import frigo.asteroids.core.World;
import frigo.asteroids.jogl.JOGLRunner;

public class Main {

    public static void main (String[] args) {
        World world = new AsteroidsWorldFactory().createWorld();
        JOGLRunner runner = new JOGLRunner(world, 1024, 768, 100);
        runner.start();
    }

}
