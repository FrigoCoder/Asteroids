
package frigo.asteroids.logic.gravity;

import static frigo.asteroids.logic.gravity.NewtonianGravity.G;
import static org.hamcrest.Matchers.is;
import static org.junit.Assert.assertThat;

import org.junit.Before;
import org.junit.Test;

import frigo.asteroids.component.Acceleration;
import frigo.asteroids.component.Attractable;
import frigo.asteroids.component.Attractor;
import frigo.asteroids.component.Mass;
import frigo.asteroids.component.Position;
import frigo.asteroids.core.Entity;
import frigo.asteroids.core.World;

public class GravitySystemTest {

    private World world = new World();
    private Entity attracted1;
    private Entity attracted2;

    @Before
    public void setUp () {
        attracted1 = createAttracted(new Mass(10), new Position(0.1, 0.0));
        attracted2 = createAttracted(new Mass(1), new Position(0.0, 0.1));
        world.addLogic(new GravitySystem(new NewtonianGravity(world)));
        world.init();
    }

    @Test
    public void does_nothing_without_attractors () {
        world.update(1.0);

        assertThat(world.get(attracted1, Acceleration.class), is(new Acceleration(0.0, 0.0)));
        assertThat(world.get(attracted2, Acceleration.class), is(new Acceleration(0.0, 0.0)));
    }

    @Test
    public void attractor_attracts_two_attractables () {
        createAttractor();

        world.update(1.0);

        assertThat(world.get(attracted1, Acceleration.class), is(new Acceleration(-0.2, 0.0).mul(G * 100 / 0.04)));
        assertThat(world.get(attracted2, Acceleration.class), is(new Acceleration(-0.1, -0.1).mul(G * 100 / 0.02)));
    }

    private Entity createAttractor () {
        return world.createEntity(new Attractor(), new Mass(100), new Acceleration(0, 0), new Position(-0.1, 0.0));
    }

    private Entity createAttracted (Mass mass, Position position) {
        return world.createEntity(new Attractable(), mass, new Acceleration(0, 0), position);
    }

}
