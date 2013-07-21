
package frigo.asteroids.logic;

import static frigo.asteroids.logic.GravitySystem.G;
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
import frigo.asteroids.core.Component;
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
        attractor1 = createAttractor(new Mass(100), new Position(-0.1, 0.0));
        attracted1 = createattracted(new Mass(10), new Position(0.1, 0.0));
        attracted2 = createattracted(new Mass(1), new Position(0.0, 0.1));
        gravitySystem.init(world);
    }

    private Entity createattracted (Mass mass, Position position) {
        return createEntity(mass, position, new Attractable());
    }

    private Entity createAttractor (Mass mass, Position position) {
        return createEntity(mass, position, new Attractor());
    }

    private Entity createEntity (Mass mass, Position position, Component extra) {
        Entity attracted = new Entity();
        attracted.set(mass);
        attracted.set(new Acceleration(0.0, 0.0));
        attracted.set(position);
        attracted.set(extra);
        return attracted;
    }

    @Test
    public void attractive_force_is_calculated_properly () {
        assertThat(gravitySystem.getAttractiveForce(attractor1, attracted1), closeTo(G * 100 * 10 / 0.04, 1E-21));
        assertThat(gravitySystem.getAttractiveForce(attractor1, attracted2), closeTo(G * 100 * 1 / 0.02, 1E-21));
    }

    @Test
    public void acceleration_is_calculated_properly () {
        assertThat(gravitySystem.getAcceleration(attractor1, attracted1), closeTo(G * 100 * 10 / 0.04 / 10, 1E-22));
        assertThat(gravitySystem.getAcceleration(attractor1, attracted2), closeTo(G * 100 * 1 / 0.02 / 1, 1E-21));

    }

    @Test
    public void directional_acceleration_is_calculated_properly () {
        assertDirectionalAcceleration(attracted1, new Vector(-0.2, 0.0).mul(G * 100 * 10 / 0.04 / 10));
        assertDirectionalAcceleration(attracted2, new Vector(-0.1, -0.1).mul(G * 100 * 1 / 0.02 / 1));
    }

    private void assertDirectionalAcceleration (Entity attracted, Vector expected) {
        Vector actual = gravitySystem.getDirectionalAcceleration(attractor1, attracted);
        assertThat(actual.x, closeTo(expected.x, 1E-22));
        assertThat(actual.y, closeTo(expected.y, 1E-22));
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

        assertAcceleration(attracted1, new Vector(-0.2, 0.0).mul(G * 100 * 10 / 0.04 / 10));
        assertAcceleration(attracted2, new Vector(-0.1, -0.1).mul(G * 100 * 1 / 0.02 / 1));
    }

    private void assertAcceleration (Entity attracted, Vector expected) {
        Acceleration acceleration = attracted.get(Acceleration.class);
        assertThat(acceleration.ddx, closeTo(expected.x, 1E-22));
        assertThat(acceleration.ddy, closeTo(expected.y, 1E-22));
    }

}
