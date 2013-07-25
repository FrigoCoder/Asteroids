
package frigo.asteroids.jogl;

import javax.media.opengl.GLAutoDrawable;
import javax.media.opengl.GLEventListener;

import frigo.asteroids.core.World;

public class JOGLWorldUpdater implements GLEventListener {

    private World world;
    private long lastMillis;

    public JOGLWorldUpdater (World world) {
        this.world = world;
    }

    @Override
    public void init (GLAutoDrawable drawable) {
        world.init();
        lastMillis = System.nanoTime();
    }

    @Override
    public void display (GLAutoDrawable drawable) {
        long currentMillis = System.nanoTime();
        double elapsedSeconds = (currentMillis - lastMillis) / 1_000_000_000.0;
        world.update(elapsedSeconds);
        lastMillis = currentMillis;
    }

    @Override
    public void dispose (GLAutoDrawable drawable) {
    }

    @Override
    public void reshape (GLAutoDrawable drawable, int x, int y, int width, int height) {
    }

}
