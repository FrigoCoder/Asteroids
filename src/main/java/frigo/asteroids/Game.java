
package frigo.asteroids;

import javax.media.opengl.GLAutoDrawable;
import javax.media.opengl.GLEventListener;

import com.jogamp.newt.event.KeyEvent;
import com.jogamp.newt.event.KeyListener;

import frigo.asteroids.core.World;
import frigo.asteroids.jogl.JOGLKeyListener;
import frigo.asteroids.jogl.JOGLRenderer;
import frigo.asteroids.jogl.JOGLRunner;
import frigo.asteroids.jogl.JOGLWorldUpdater;

public class Game implements GLEventListener, KeyListener {

    public static void main (String[] args) {
        Game game = new Game();
        JOGLRunner runner = new JOGLRunner(game, 1024, 768, 100);
        runner.init();
    }

    private World world;
    private JOGLKeyListener keyListener;
    private JOGLWorldUpdater worldUpdater;
    private JOGLRenderer renderer;

    public Game () {
        world = new AsteroidsWorldFactory().createWorld();

        keyListener = new JOGLKeyListener(world);
        worldUpdater = new JOGLWorldUpdater(world);
        renderer = new JOGLRenderer(world);
    }

    @Override
    public void keyPressed (KeyEvent e) {
        keyListener.keyPressed(e);
    }

    @Override
    public void keyReleased (KeyEvent e) {
        keyListener.keyReleased(e);
    }

    @Override
    public void init (GLAutoDrawable drawable) {
        keyListener.init(drawable);
        worldUpdater.init(drawable);
        renderer.init(drawable);
    }

    @Override
    public void dispose (GLAutoDrawable drawable) {
        renderer.dispose(drawable);
        worldUpdater.dispose(drawable);
        keyListener.dispose(drawable);
    }

    @Override
    public void display (GLAutoDrawable drawable) {
        keyListener.display(drawable);
        worldUpdater.display(drawable);
        renderer.display(drawable);
    }

    @Override
    public void reshape (GLAutoDrawable drawable, int x, int y, int width, int height) {
        keyListener.reshape(drawable, x, y, width, height);
        worldUpdater.reshape(drawable, x, y, width, height);
        renderer.reshape(drawable, x, y, width, height);
    }

}
