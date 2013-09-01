
package frigo.asteroids.component;

import static frigo.asteroids.component.Vector.NULL;
import static frigo.asteroids.component.Vector.vector;
import static frigo.asteroids.component.VectorCloseTo.closeTo;
import static org.hamcrest.Matchers.is;
import static org.junit.Assert.assertThat;

import org.junit.Test;

public class PlanarTest {

    private Vector acceleration = vector(0.5, 0.6);
    private Vector velocity = vector(0.1, 0.2);
    private Vector position = vector(0.3, 0.4);
    private Planar planar;

    @Test
    public void accelerate_adds_accelerations () {
        planar = new Planar(position, velocity, NULL);
        assertThat(planar.accelerate(acceleration), is(new Planar(position, velocity, acceleration)));
    }

    @Test
    public void zero_acceleration_and_one_elapsed_seconds () {
        planar = new Planar(position, velocity, NULL);
        assertThat(planar.update(1).acceleration, is(NULL));
        assertThat(planar.update(1).velocity, is(velocity));
        assertThat(planar.update(1).position, is(position.add(velocity)));
    }

    @Test
    public void zero_acceleration_and_tenth_elapsed_seconds () {
        planar = new Planar(position, velocity, NULL);
        assertThat(planar.update(0.1).acceleration, is(NULL));
        assertThat(planar.update(0.1).velocity, is(velocity));
        assertThat(planar.update(0.1).position, is(position.add(velocity.mul(0.1))));
    }

    @Test
    public void nonzero_acceleration_and_one_elapsed_seconds () {
        planar = new Planar(position, velocity, acceleration);
        assertThat(planar.update(1).acceleration, is(NULL));
        assertThat(planar.update(1).velocity, is(velocity.add(acceleration)));
        assertThat(planar.update(1).position, closeTo(position.add(velocity).add(acceleration.mul(0.5)), 1E-15));
    }

    @Test
    public void nonzero_acceleration_and_tenth_elapsed_seconds () {
        planar = new Planar(position, velocity, acceleration);
        assertThat(planar.update(0.1).acceleration, is(NULL));
        assertThat(planar.update(0.1).velocity, is(velocity.add(acceleration.mul(0.1))));
        assertThat(planar.update(0.1).position,
            closeTo(position.add(velocity.mul(0.1)).add(acceleration.mul(0.5 * 0.1 * 0.1)), 1E-15));
    }

}
