
package frigo.asteroids.jogl;

import static com.google.common.base.Preconditions.checkArgument;

import java.nio.DoubleBuffer;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;
import java.util.TreeMap;

import com.jogamp.opengl.GL;
import com.jogamp.opengl.GL2;
import com.jogamp.opengl.GL2GL3;
import com.jogamp.opengl.GLAutoDrawable;
import com.jogamp.opengl.GLEventListener;
import com.jogamp.opengl.fixedfunc.GLMatrixFunc;
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
    private Entity player;

    public JOGLRenderer (World world) {
        this.world = world;
        player = getPlayer();
    }

    private Entity getPlayer () {
        List<Entity> entities = world.getEntitiesFor(Aspect.allOf(Thrustable.ID));
        checkArgument(entities.size() == 1);
        return entities.get(0);
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

        drawEntities(gl, world.getEntitiesFor(Aspect.allOf(Planar.ID, Size.ID, Image.ID)));
    }

    private void drawEntities (GL2 gl, List<Entity> entities) {
        for( Map.Entry<Image, List<Entity>> entry : separateEntitiesByImage(entities).entrySet() ){
            drawEntitiesByImage(gl, entry.getKey(), entry.getValue());
        }
    }

    private Map<Image, List<Entity>> separateEntitiesByImage (List<Entity> entities) {
        Map<Image, List<Entity>> entitiesByImageName = new TreeMap<>();
        for( Entity entity : entities ){
            Image image = entity.get(Image.ID);
            try{
                entitiesByImageName.get(image).add(entity);
            }catch( NullPointerException e ){
                entitiesByImageName.put(image, new LinkedList<Entity>());
                entitiesByImageName.get(image).add(entity);
            }
        }
        return entitiesByImageName;
    }

    private void drawEntitiesByImage (GL2 gl, Image Image, List<Entity> entities) {
        Texture texture = textures.get(Image.filename);
        texture.enable(gl);
        texture.bind(gl);

        DoubleBuffer textureBuffer = DoubleBuffer.allocate(entities.size() * 4 * 2);
        DoubleBuffer vertexBuffer = DoubleBuffer.allocate(entities.size() * 4 * 2);

        for( Entity entity : entities ){
            addVertex(textureBuffer, vertexBuffer, entity, 0, 0);
            addVertex(textureBuffer, vertexBuffer, entity, 0, 1);
            addVertex(textureBuffer, vertexBuffer, entity, 1, 1);
            addVertex(textureBuffer, vertexBuffer, entity, 1, 0);
        }

        gl.glBegin(GL2GL3.GL_QUADS);
        for( int i = 0; i < entities.size() * 4; i++ ){
            gl.glTexCoord2d(textureBuffer.get(2 * i), textureBuffer.get(2 * i + 1));
            gl.glVertex2d(vertexBuffer.get(2 * i), vertexBuffer.get(2 * i + 1));
        }
        gl.glEnd();

        texture.disable(gl);
    }

    private void addVertex (DoubleBuffer textureBuffer, DoubleBuffer vertexBuffer, Entity entity, double u, double v) {
        textureBuffer.put(u);
        textureBuffer.put(v);
        Vector vertexSpace = textureSpaceToVertexSpace(entity, u, v);
        vertexBuffer.put(vertexSpace.x);
        vertexBuffer.put(vertexSpace.y);
    }

    private Vector textureSpaceToVertexSpace (Entity entity, double u, double v) {
        Vector center = entity.has(Background.ID) ? Vector.vector(0, 0) : player.get(Planar.ID).position;
        Vector position = entity.get(Planar.ID).position;
        double size = entity.get(Size.ID).size;
        double angle = getAngularPosition(entity);

        Vector vector = Vector.vector(2 * u - 1, -(2 * v - 1));
        vector = vector.rotate(angle);
        vector = vector.mul(size);
        vector = vector.add(position);
        vector = vector.sub(center);
        return vector;
    }

    private double getAngularPosition (Entity entity) {
        try{
            return entity.get(Angular.ID).position;
        }catch( NullPointerException e ){
            return 0;
        }
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
        gl.glOrtho(-ratio, +ratio, -1, 1, -1, 1);
    }

}
