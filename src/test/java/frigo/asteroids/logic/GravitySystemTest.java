
package frigo.asteroids.logic;

import static frigo.asteroids.logic.PairwiseGravitation.G;
import static org.hamcrest.Matchers.closeTo;
import static org.hamcrest.Matchers.is;
import static org.junit.Assert.assertThat;

import org.junit.Before;
import org.junit.Test;

import frigo.asteroids.component.Acceleration;
import frigo.asteroids.component.Attractable;
import frigo.asteroids.component.Attractor;
import frigo.asteroids.component.Mass;
import frigo.asteroids.component.Position;
import frigo.asteroids.component.Vector;
import frigo.asteroids.core.Entity;
import frigo.asteroids.core.World;

public class GravitySystemTest {

    private World world = new World();
    private GravitySystem gravitySystem = new GravitySystem();
    private Entity attractor1;
    private Entity attracted1;
    private Entity attracted2;

    @Before
    public void setUp () {
        attractor1 = new Entity(new Attractor(), new Mass(100), new Acceleration(0, 0), new Position(-0.1, 0.0));
        attracted1 = new Entity(new Attractable(), new Mass(10), new Acceleration(0, 0), new Position(0.1, 0.0));
        attracted2 = new Entity(new Attractable(), new Mass(1), new Acceleration(0, 0), new Position(0.0, 0.1));

        gravitySystem.init(world);
    }

    @Test
    public void does_nothing_without_attractors () {
        world.addEntity(attracted1);
        world.addEntity(attracted2);

        gravitySystem.update(world, 1.0);

        assertThat(attracted1.get(Acceleration.class), is(new Acceleration(0.0, 0.0)));
        assertThat(attracted2.get(Acceleration.class), is(new Acceleration(0.0, 0.0)));
    }

    @Test
    public void attractor_attracts_two_attractables () {
        world.addEntity(attractor1);
        world.addEntity(attracted1);
        world.addEntity(attracted2);

        gravitySystem.update(world, 1.0);

        assertAcceleration(attracted1, new Vector(-0.2, 0.0).mul(G * 100 / 0.04));
        assertAcceleration(attracted2, new Vector(-0.1, -0.1).mul(G * 100 / 0.02));
    }

    private void assertAcceleration (Entity attracted, Vector expected) {
        Acceleration acceleration = attracted.get(Acceleration.class);
        assertThat(acceleration.x, closeTo(expected.x, 0));
        assertThat(acceleration.y, closeTo(expected.y, 0));
    }

}
