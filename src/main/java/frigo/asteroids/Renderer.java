
package frigo.asteroids;

import static frigo.asteroids.Rethrow.unchecked;

import org.lwjgl.LWJGLException;
import org.lwjgl.opengl.Display;
import org.lwjgl.opengl.DisplayMode;
import org.lwjgl.opengl.GL11;

public class Renderer extends Updater {

    @Override
    public void init (World world) {
        int width = 1024;
        int height = 768;
        try{
            DisplayMode displayMode = new DisplayMode(width, height);
            Display.setDisplayMode(displayMode);
            Display.create();
            // Display.makeCurrent();
        }catch( LWJGLException e ){
            throw unchecked(e);
        }

        GL11.glMatrixMode(GL11.GL_PROJECTION);
        GL11.glLoadIdentity();
        GL11.glOrtho(-1, 1, 1, -1, -1, 1);

        GL11.glEnable(GL11.GL_POINT_SMOOTH);
        GL11.glHint(GL11.GL_POINT_SMOOTH_HINT, GL11.GL_NICEST);
    }

    @Override
    public void update (World world) {
        GL11.glClear(GL11.GL_COLOR_BUFFER_BIT);

        GL11.glMatrixMode(GL11.GL_MODELVIEW);
        GL11.glLoadIdentity();

        GL11.glPointSize(10.0f);
        GL11.glBegin(GL11.GL_POINTS);
        for( Entity entity : world.getEntities() ){
            Position position = entity.getComponent(Position.class);
            GL11.glVertex2d(position.x, position.y);
        }
        GL11.glEnd();

        Display.update();
    }
}
