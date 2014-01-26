
package frigo.asteroids.logic.gravity;

import static frigo.asteroids.component.VectorCloseTo.closeTo;
import static frigo.asteroids.core.Vector.NULL;
import static frigo.asteroids.core.Vector.vector;
import static frigo.asteroids.logic.gravity.NewtonianGravity.G;
import static org.hamcrest.Matchers.is;
import static org.junit.Assert.assertThat;

import org.junit.Before;
import org.junit.Test;

import frigo.asteroids.component.Attractable;
import frigo.asteroids.component.Attractor;
import frigo.asteroids.component.Mass;
import frigo.asteroids.component.Planar;
import frigo.asteroids.core.Entity;
import frigo.asteroids.core.World;

public class GravitySystemTest {

    private World world = new World();
    private Entity attracted1;
    private Entity attracted2;

    @Before
    public void setUp () {
        attracted1 = world.createEntity(Attractable.ATTRACTABLE, new Mass(10), new Planar(vector(0.1, 0), NULL, NULL));
        attracted2 = world.createEntity(Attractable.ATTRACTABLE, new Mass(1), new Planar(vector(0, 0.1), NULL, NULL));
        world.addLogic(new GravitySystem(new NewtonianGravity()));
        world.init();
    }

    @Test
    public void does_nothing_without_attractors () {
        world.update(1);

        assertThat(attracted1.get(Planar.class).acceleration, is(NULL));
        assertThat(attracted2.get(Planar.class).acceleration, is(NULL));
    }

    @Test
    public void attractor_attracts_two_attractables () {
        world.createEntity(new Attractor(), new Mass(100), new Planar(vector(-0.1, 0), NULL, NULL));

        world.update(1);

        assertThat(attracted1.get(Planar.class).acceleration, closeTo(vector(-0.2, 0).mul(G * 100 / 0.04), 1E-22));
        assertThat(attracted2.get(Planar.class).acceleration, closeTo(vector(-0.1, -0.1).mul(G * 100 / 0.02), 1E-22));
    }

}
