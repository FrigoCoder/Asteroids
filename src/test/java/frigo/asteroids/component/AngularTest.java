
package frigo.asteroids.component;

import static org.hamcrest.Matchers.is;
import static org.junit.Assert.assertThat;

import org.junit.Test;

public class AngularTest {

    private double acceleration = 0.6;
    private double velocity = 0.2;
    private double position = 0.4;

    @Test
    public void accelerate_adds_accelerations () {
        Angular actual = new Angular(position, velocity, 0);
        actual.accelerate(acceleration);

        Angular expected = new Angular(position, velocity, acceleration);
        assertThat(actual, is(expected));
    }

    @Test
    public void zero_acceleration_and_one_elapsed_seconds () {
        Angular actual = new Angular(position, velocity, 0);
        actual.update(1);

        Angular expected = new Angular(position + velocity, velocity, 0);
        assertThat(actual, is(expected));
    }

    @Test
    public void zero_acceleration_and_tenth_elapsed_seconds () {
        Angular actual = new Angular(position, velocity, 0);
        actual.update(0.1);

        Angular expected = new Angular(position + velocity * 0.1, velocity, 0);
        assertThat(actual, is(expected));
    }

    @Test
    public void nonzero_acceleration_and_one_elapsed_seconds () {
        Angular actual = new Angular(position, velocity, acceleration);
        actual.update(1);

        Angular expected = new Angular(position + (velocity + acceleration * 0.5), velocity + acceleration, 0);
        assertThat(actual, is(expected));
    }

    @Test
    public void nonzero_acceleration_and_tenth_elapsed_seconds () {
        Angular actual = new Angular(position, velocity, acceleration);
        actual.update(0.1);

        Angular expected =
            new Angular(position + (velocity * 0.1 + acceleration * 0.5 * 0.1 * 0.1), velocity + acceleration * 0.1, 0);
        assertThat(actual, is(expected));
    }

}
