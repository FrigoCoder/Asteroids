
package frigo.asteroids.logic.gravity;

import static frigo.asteroids.component.Planar.planar;
import static frigo.asteroids.component.Vector.NULL;
import static frigo.asteroids.component.Vector.vector;
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
        attracted1 = world.createEntity(new Attractable(), new Mass(10), planar().position(0.1, 0));
        attracted2 = world.createEntity(new Attractable(), new Mass(1), planar().position(0, 0.1));
        world.addLogic(new GravitySystem(new NewtonianGravity(world)));
        world.init();
    }

    @Test
    public void does_nothing_without_attractors () {
        world.update(1);

        assertThat(world.get(attracted1, Planar.class).acceleration, is(NULL));
        assertThat(world.get(attracted2, Planar.class).acceleration, is(NULL));
    }

    @Test
    public void attractor_attracts_two_attractables () {
        world.createEntity(new Attractor(), new Mass(100), planar().position(-0.1, 0));

        world.update(1);

        assertThat(world.get(attracted1, Planar.class).acceleration, is(vector(-0.2, 0).mul(G * 100 / 0.04)));
        assertThat(world.get(attracted2, Planar.class).acceleration, is(vector(-0.1, -0.1).mul(G * 100 / 0.02)));
    }

}
