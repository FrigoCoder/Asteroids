
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

public class PairwiseGravitationTest {

    private Entity attractor1;
    private Entity attracted1;
    private Entity attracted2;
    private Entity attracted3;

    private PairwiseGravitation gravitation1;
    private PairwiseGravitation gravitation2;
    private PairwiseGravitation gravitation3;

    @Before
    public void setUp () {
        attractor1 = new Entity(new Attractor(), new Mass(100), new Acceleration(0, 0), new Position(-0.1, 0.0));
        attracted1 = new Entity(new Attractable(), new Mass(10), new Acceleration(0, 0), new Position(0.1, 0.0));
        attracted2 = new Entity(new Attractable(), new Mass(1), new Acceleration(0, 0), new Position(0.0, 0.1));
        attracted3 = new Entity(new Attractable(), new Mass(1), new Acceleration(0, 0), new Position(-0.1, 0.0));

        gravitation1 = new PairwiseGravitation(attractor1, attracted1);
        gravitation2 = new PairwiseGravitation(attractor1, attracted2);
        gravitation3 = new PairwiseGravitation(attractor1, attracted3);
    }

    @Test
    public void attractive_force_is_calculated_properly () {
        assertThat(gravitation1.getAttractiveForce(), closeTo(G * 100 * 10 / 0.04, 1E-21));
        assertThat(gravitation2.getAttractiveForce(), closeTo(G * 100 * 1 / 0.02, 1E-21));
    }

    @Test
    public void acceleration_is_calculated_properly () {
        assertThat(gravitation1.getAcceleration(), closeTo(G * 100 / 0.04, 1E-22));
        assertThat(gravitation2.getAcceleration(), closeTo(G * 100 / 0.02, 1E-21));
    }

    @Test
    public void directional_acceleration_is_calculated_properly () {
        assertThat(gravitation1.getDirectionalAcceleration(), is(new Vector(-0.2, 0.0).mul(G * 100 / 0.04)));
        assertThat(gravitation2.getDirectionalAcceleration(), is(new Vector(-0.1, -0.1).mul(G * 100 / 0.02)));
    }

    @Test
    public void attractive_force_is_zero_for_objects_at_same_place () {
        assertThat(gravitation3.getAttractiveForce(), is(0.0));
    }

    @Test
    public void acceleration_is_zero_for_objects_at_same_place () {
        assertThat(gravitation3.getAcceleration(), is(0.0));
    }

    @Test
    public void direction_acceleration_is_zero_for_objects_at_same_place () {
        assertThat(gravitation3.getDirectionalAcceleration(), is(new Vector(0, 0)));
    }

}
