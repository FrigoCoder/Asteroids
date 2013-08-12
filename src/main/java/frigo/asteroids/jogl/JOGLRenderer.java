
package frigo.asteroids.jogl;

import javax.media.opengl.GL;
import javax.media.opengl.GL2;
import javax.media.opengl.GL2ES1;
import javax.media.opengl.GL2GL3;
import javax.media.opengl.GLAutoDrawable;
import javax.media.opengl.GLEventListener;
import javax.media.opengl.fixedfunc.GLMatrixFunc;

import com.jogamp.opengl.util.texture.Texture;

import frigo.asteroids.component.Circle;
import frigo.asteroids.component.Position;
import frigo.asteroids.component.Size;
import frigo.asteroids.component.TextureName;
import frigo.asteroids.core.Aspect;
import frigo.asteroids.core.Entity;
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
        drawCircles(gl);
        drawTextures(gl);
    }

    private void drawCircles (GL2 gl) {
        Aspect aspect = Aspect.allOf(Position.class, Size.class, Circle.class);
        for( Entity entity : world.getEntitiesFor(aspect) ){
            Position position = world.get(entity, Position.class);
            Size size = world.get(entity, Size.class);
            drawCircle(gl, position, size.size);
        }
    }

    private void drawCircle (GL2 gl, Position position, double size) {
        gl.glPointSize((float) size);
        gl.glBegin(GL.GL_POINTS);
        gl.glVertex2d(position.x, position.y);
        gl.glEnd();
    }

    private void drawTextures (GL2 gl) {
        Aspect aspect = Aspect.allOf(Position.class, Size.class, TextureName.class);
        for( Entity entity : world.getEntitiesFor(aspect) ){
            Position position = world.get(entity, Position.class);
            Size size = world.get(entity, Size.class);
            TextureName textureName = world.get(entity, TextureName.class);
            drawTexture(gl, position, size.size, textureName.filename);
        }
    }

    private void drawTexture (GL2 gl, Position position, double size, String texture) {
        Texture tex = textures.get(texture);
        tex.enable(gl);
        tex.bind(gl);
        gl.glBegin(GL2GL3.GL_QUADS);
        gl.glTexCoord2d(0.5 - 0.5, 0.5 - 0.5);
        gl.glVertex2d(position.x - size / 2, position.y - size / 2);
        gl.glTexCoord2d(0.5 - 0.5, 0.5 + 0.5);
        gl.glVertex2d(position.x - size / 2, position.y + size / 2);
        gl.glTexCoord2d(0.5 + 0.5, 0.5 + 0.5);
        gl.glVertex2d(position.x + size / 2, position.y + size / 2);
        gl.glTexCoord2d(0.5 + 0.5, 0.5 - 0.5);
        gl.glVertex2d(position.x + size / 2, position.y - size / 2);
        gl.glEnd();
        tex.disable(gl);
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
