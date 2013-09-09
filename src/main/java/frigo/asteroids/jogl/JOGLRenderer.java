
package frigo.asteroids.jogl;

import static frigo.asteroids.core.Vector.vector;

import java.util.List;

import javax.media.opengl.GL;
import javax.media.opengl.GL2;
import javax.media.opengl.GL2ES1;
import javax.media.opengl.GL2GL3;
import javax.media.opengl.GLAutoDrawable;
import javax.media.opengl.GLEventListener;
import javax.media.opengl.fixedfunc.GLMatrixFunc;

import com.jogamp.opengl.util.texture.Texture;

import frigo.asteroids.component.Angular;
import frigo.asteroids.component.Planar;
import frigo.asteroids.component.PlayerControllable;
import frigo.asteroids.component.Point;
import frigo.asteroids.component.Size;
import frigo.asteroids.component.TextureName;
import frigo.asteroids.core.Aspect;
import frigo.asteroids.core.Entity;
import frigo.asteroids.core.Vector;
import frigo.asteroids.core.World;

public class JoglRenderer implements GLEventListener {

    private static final Aspect PLAYER_CONTROLLABLE = Aspect.allOf(PlayerControllable.class);

    private World world;
    private TextureBuffer textures = new TextureBuffer();

    public JoglRenderer (World world) {
        this.world = world;
    }

    @Override
    public void init (GLAutoDrawable drawable) {
        GL2 gl = drawable.getGL().getGL2();

        gl.glEnable(GL2ES1.GL_POINT_SMOOTH);
        gl.glHint(GL2ES1.GL_POINT_SMOOTH, GL.GL_NICEST);

        gl.glEnable(GL.GL_TEXTURE_2D);

        gl.glEnable(GL.GL_BLEND);
        gl.glBlendFunc(GL.GL_SRC_ALPHA, GL.GL_ONE_MINUS_SRC_ALPHA);
    }

    @Override
    public void display (GLAutoDrawable drawable) {
        GL2 gl = drawable.getGL().getGL2();
        gl.glClear(GL.GL_COLOR_BUFFER_BIT | GL.GL_DEPTH_BUFFER_BIT);

        gl.glMatrixMode(GLMatrixFunc.GL_MODELVIEW);
        gl.glLoadIdentity();

        drawPoints(gl);

        focusOnPlayer(gl);

        drawTextures(gl);
    }

    private void focusOnPlayer (GL2 gl) {
        List<Entity> entities = world.getEntitiesFor(PLAYER_CONTROLLABLE);
        if( entities.size() == 1 ){
            Entity entity = entities.get(0);
            Vector position = world.get(entity, Planar.class).position;
            gl.glTranslated(-position.x, -position.y, 0);
        }
    }

    private void drawPoints (GL2 gl) {
        gl.glPointSize(1.0f);
        gl.glBegin(GL.GL_POINTS);
        Aspect aspect = Aspect.allOf(Planar.class, Point.class);
        for( Entity entity : world.getEntitiesFor(aspect) ){
            Vector position = world.get(entity, Planar.class).position;
            gl.glVertex2d(position.x, position.y);
        }
        gl.glEnd();
    }

    private void drawTextures (GL2 gl) {
        Aspect aspect = Aspect.allOf(Planar.class, Size.class, TextureName.class);
        for( Entity entity : world.getEntitiesFor(aspect) ){
            Vector position = world.get(entity, Planar.class).position;
            Size size = world.get(entity, Size.class);
            TextureName textureName = world.get(entity, TextureName.class);
            double angularDisplacement =
                world.has(entity, Angular.class) ? world.get(entity, Angular.class).position : 0;
            drawTexture(gl, position, angularDisplacement, size.size, textureName.filename);
        }
    }

    private void drawTexture (GL2 gl, Vector position, double radians, double size, String texture) {
        Texture tex = textures.get(texture);
        tex.enable(gl);
        tex.bind(gl);

        double scale = size / 2;
        gl.glBegin(GL2GL3.GL_QUADS);
        gl.glTexCoord2d(0, 0);
        vertex(gl, position.add(vector(-scale, -scale).rotate(radians)));
        gl.glTexCoord2d(0, 1);
        vertex(gl, position.add(vector(-scale, +scale).rotate(radians)));
        gl.glTexCoord2d(1, 1);
        vertex(gl, position.add(vector(+scale, +scale).rotate(radians)));
        gl.glTexCoord2d(1, 0);
        vertex(gl, position.add(vector(+scale, -scale).rotate(radians)));
        gl.glEnd();

        tex.disable(gl);
    }

    private void vertex (GL2 gl, Vector position) {
        gl.glVertex2d(position.x, position.y);
    }

    @Override
    public void dispose (GLAutoDrawable drawable) {
    }

    @Override
    public void reshape (GLAutoDrawable drawable, int x, int y, int width, int height) {
        GL2 gl = drawable.getGL().getGL2();

        double ratio = (double) width / height;

        gl.glMatrixMode(GLMatrixFunc.GL_PROJECTION);
        gl.glLoadIdentity();
        gl.glOrtho(-ratio, ratio, -1, 1, -1, 1);
    }
}
