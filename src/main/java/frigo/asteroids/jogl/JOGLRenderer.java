
package frigo.asteroids.jogl;

import javax.media.opengl.GL;
import javax.media.opengl.GL2;
import javax.media.opengl.GL2ES1;
import javax.media.opengl.GL2GL3;
import javax.media.opengl.GLAutoDrawable;
import javax.media.opengl.GLEventListener;
import javax.media.opengl.fixedfunc.GLMatrixFunc;

import com.jogamp.opengl.util.texture.Texture;

import frigo.asteroids.component.Position;
import frigo.asteroids.component.Renderable;
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

        gl.glMatrixMode(GLMatrixFunc.GL_PROJECTION);
        gl.glLoadIdentity();
        gl.glOrtho(-1, 1, 1, -1, -1, 1);

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

        Aspect aspect = Aspect.allOf(Position.class, Renderable.class);
        for( Entity entity : world.getEntitiesFor(aspect) ){
            Position position = entity.get(Position.class);
            Renderable renderable = entity.get(Renderable.class);
            double size = renderable.size;

            String texture = renderable.texture;
            if( texture == null ){
                gl.glPointSize((float) size);
                gl.glColor3d(renderable.r, renderable.g, renderable.b);
                gl.glBegin(GL.GL_POINTS);
                gl.glVertex2d(position.x, position.y);
                gl.glEnd();
            }else{
                Texture tex = textures.get(texture);
                tex.enable(gl);
                tex.bind(gl);

                gl.glBegin(GL2GL3.GL_QUADS);
                gl.glColor3d(1, 1, 1);
                gl.glTexCoord2d(0.0, 0.0);
                gl.glVertex2d(-size / 500, -size / 500);
                gl.glTexCoord2d(1.0, 0.0);
                gl.glVertex2d(size / 500, -size / 500);
                gl.glTexCoord2d(1.0, 1.0);
                gl.glVertex2d(size / 500, size / 500);
                gl.glTexCoord2d(0.0, 1.0);
                gl.glVertex2d(-size / 500, size / 500);
                gl.glEnd();

                tex.disable(gl);
            }
        }
    }

    @Override
    public void dispose (GLAutoDrawable drawable) {
    }

    @Override
    public void reshape (GLAutoDrawable drawable, int x, int y, int width, int height) {
    }

}
