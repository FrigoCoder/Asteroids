
package frigo.asteroids.logic.gravity;

import static frigo.asteroids.component.VectorCloseTo.closeTo;
import static frigo.asteroids.core.Vector.ZERO;
import static frigo.asteroids.core.Vector.vector;
import static frigo.asteroids.logic.gravity.NewtonianGravity.G;
import static org.hamcrest.Matchers.is;
import static org.junit.Assert.assertThat;

import org.hamcrest.Matcher;
import org.junit.Before;
import org.junit.Test;

import frigo.asteroids.component.Attractable;
import frigo.asteroids.component.Attractor;
import frigo.asteroids.component.Mass;
import frigo.asteroids.component.Planar;
import frigo.asteroids.core.Entity;
import frigo.asteroids.core.Vector;
import frigo.asteroids.core.World;

public class GravitySystemTest {

    private World world = new World();
    private Entity attracted1;
    private Entity attracted2;

    @Before
    public void setUp () {
        world.register(Attractable.class);
        world.register(Attractor.class);
        world.register(Mass.class);
        world.register(Planar.class);

        attracted1 = world.createEntity();
        attracted1.setFlag(Attractable.ID);
        attracted1.setDouble(Mass.ID, 10);
        attracted1.set(Planar.ID, new Planar(vector(0.1, 0), ZERO, ZERO));

        attracted2 = world.createEntity();
        attracted2.setFlag(Attractable.ID);
        attracted2.setDouble(Mass.ID, 1);
        attracted2.set(Planar.ID, new Planar(vector(0, 0.1), ZERO, ZERO));

        world.addLogic(new GravitySystem(new NewtonianGravity()));
        world.init();
    }

    @Test
    public void does_nothing_without_attractors () {
        world.update(1);

        assertAcceleration(attracted1, is(ZERO));
        assertAcceleration(attracted2, is(ZERO));
    }

    @Test
    public void attractor_attracts_two_attractables () {
        Entity entity = world.createEntity();
        entity.setFlag(Attractor.ID);
        entity.setDouble(Mass.ID, 100);
        entity.set(Planar.ID, new Planar(vector(-0.1, 0), ZERO, ZERO));

        world.update(1);

        assertAcceleration(attracted1, closeTo(vector(-0.2, 0).mul(G * 100 / 0.04), 1E-22));
        assertAcceleration(attracted2, closeTo(vector(-0.1, -0.1).mul(G * 100 / 0.02), 1E-22));
    }

    private void assertAcceleration (Entity entity, Matcher<Vector> matcher) {
        Planar planar = entity.get(Planar.ID);
        assertThat(planar.acceleration, matcher);
    }

}
