
package frigo.asteroids.jogl;

import java.util.HashMap;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;

import javax.media.opengl.GL;
import javax.media.opengl.GL2;
import javax.media.opengl.GL2GL3;
import javax.media.opengl.GLAutoDrawable;
import javax.media.opengl.GLEventListener;
import javax.media.opengl.fixedfunc.GLMatrixFunc;

import com.jogamp.opengl.util.texture.Texture;

import frigo.asteroids.component.Angular;
import frigo.asteroids.component.Background;
import frigo.asteroids.component.Image;
import frigo.asteroids.component.Planar;
import frigo.asteroids.component.Size;
import frigo.asteroids.component.Thrustable;
import frigo.asteroids.core.Aspect;
import frigo.asteroids.core.Entity;
import frigo.asteroids.core.Vector;
import frigo.asteroids.core.World;

public class JOGLRenderer implements GLEventListener {

    private World world;
    private TextureBuffer textures = new TextureBuffer();

    public JOGLRenderer (World world) {
        this.world = world;
    }

    @Override
    public void init (GLAutoDrawable drawable) {
        GL2 gl = drawable.getGL().getGL2();

        gl.glEnable(GL.GL_TEXTURE_2D);

        gl.glEnable(GL.GL_BLEND);
        gl.glBlendFunc(GL.GL_SRC_ALPHA, GL.GL_ONE_MINUS_SRC_ALPHA);
    }

    @Override
    public void display (GLAutoDrawable drawable) {
        GL2 gl = drawable.getGL().getGL2();
        gl.glClear(GL.GL_COLOR_BUFFER_BIT);

        gl.glMatrixMode(GLMatrixFunc.GL_MODELVIEW);
        gl.glLoadIdentity();

        drawBackground(gl);
        focusOnPlayer(gl);
        drawNonBackgroundEntities(gl);
    }

    private void focusOnPlayer (GL2 gl) {
        List<Entity> entities = world.getEntitiesFor(Aspect.allOf(Thrustable.class));
        if( entities.size() == 1 ){
            Entity entity = entities.get(0);
            Vector position = entity.get(Planar.class).position;
            gl.glTranslated(-position.x, -position.y, 0);
        }
    }

    private void drawBackground (GL2 gl) {
        Aspect aspect = Aspect.allOf(Planar.class, Size.class, Image.class, Background.class);
        drawEntities(gl, world.getEntitiesFor(aspect));
    }

    private void drawNonBackgroundEntities (GL2 gl) {
        Aspect aspect = Aspect.allOf(Planar.class, Size.class, Image.class).andNoneOf(Background.class);
        drawEntities(gl, world.getEntitiesFor(aspect));
    }

    private void drawEntities (GL2 gl, List<Entity> entities) {
        Map<String, List<Entity>> entitiesByImageName = new HashMap<>();
        for( Entity entity : entities ){
            String image = entity.get(Image.class).filename;
            if( !entitiesByImageName.containsKey(image) ){
                entitiesByImageName.put(image, new LinkedList<Entity>());
            }
            entitiesByImageName.get(image).add(entity);
        }
        for( String image : entitiesByImageName.keySet() ){
            Texture texture = textures.get(image);
            texture.enable(gl);
            texture.bind(gl);
            drawSameTextureEntities(gl, entitiesByImageName.get(image));
            texture.disable(gl);
        }
    }

    private void drawSameTextureEntities (GL2 gl, List<Entity> entities) {
        for( Entity entity : entities ){
            drawEntity(gl, entity);
        }
    }

    private void drawEntity (GL2 gl, Entity entity) {
        Vector position = entity.get(Planar.class).position;
        double angular = entity.has(Angular.class) ? entity.get(Angular.class).position : 0;
        double size = entity.get(Size.class).size;

        gl.glMatrixMode(GLMatrixFunc.GL_MODELVIEW);
        gl.glPushMatrix();
        gl.glTranslated(position.x, position.y, 0);
        gl.glScaled(size, size, 1);
        gl.glRotated(angular * 180 / Math.PI, 0, 0, 1);

        gl.glBegin(GL2GL3.GL_QUADS);
        vertex(gl, 0, 0, -0.5, +0.5);
        vertex(gl, 0, 1, -0.5, -0.5);
        vertex(gl, 1, 1, +0.5, -0.5);
        vertex(gl, 1, 0, +0.5, +0.5);
        gl.glEnd();

        gl.glPopMatrix();
    }

    private void vertex (GL2 gl, double s, double t, double x, double y) {
        gl.glTexCoord2d(s, t);
        gl.glVertex2d(x, y);
    }

    @Override
    public void dispose (GLAutoDrawable drawable) {
    }

    @Override
    public void reshape (GLAutoDrawable drawable, int x, int y, int width, int height) {
        GL2 gl = drawable.getGL().getGL2();

        double ratio = (double) width / height;
        double left = -ratio;
        double right = +ratio;
        double bottom = -1;
        double top = +1;
        double near = -1;
        double far = 1;

        gl.glMatrixMode(GLMatrixFunc.GL_PROJECTION);
        gl.glLoadIdentity();
        gl.glOrtho(left, right, bottom, top, near, far);
    }
}
