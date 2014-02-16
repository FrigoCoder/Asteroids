
package frigo.asteroids.logic.gravity;

import static frigo.asteroids.component.VectorCloseTo.closeTo;
import static frigo.asteroids.core.Vector.ZERO;
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
        attracted1 = world.createEntity();
        attracted1.add(Attractable.ID, Attractable.ATTRACTABLE);
        attracted1.add(Mass.ID, new Mass(10));
        attracted1.add(Planar.ID, new Planar(vector(0.1, 0), ZERO, ZERO));

        attracted2 = world.createEntity();
        attracted2.add(Attractable.ID, Attractable.ATTRACTABLE);
        attracted2.add(Mass.ID, new Mass(1));
        attracted2.add(Planar.ID, new Planar(vector(0, 0.1), ZERO, ZERO));

        world.addLogic(new GravitySystem(new NewtonianGravity()));
        world.init();
    }

    @Test
    public void does_nothing_without_attractors () {
        world.update(1);

        assertThat(attracted1.get(Planar.ID).acceleration, is(ZERO));
        assertThat(attracted2.get(Planar.ID).acceleration, is(ZERO));
    }

    @Test
    public void attractor_attracts_two_attractables () {
        Entity entity = world.createEntity();
        entity.add(Attractor.ID, Attractor.ATTRACTOR);
        entity.add(Mass.ID, new Mass(100));
        entity.add(Planar.ID, new Planar(vector(-0.1, 0), ZERO, ZERO));

        world.update(1);

        assertThat(attracted1.get(Planar.ID).acceleration, closeTo(vector(-0.2, 0).mul(G * 100 / 0.04), 1E-22));
        assertThat(attracted2.get(Planar.ID).acceleration, closeTo(vector(-0.1, -0.1).mul(G * 100 / 0.02), 1E-22));
    }

}
