
package frigo.asteroids.component;

import static org.hamcrest.Matchers.is;
import static org.junit.Assert.assertThat;

import org.junit.Test;

public class AngularTest {

    private double zeroAcceleration = 0;
    private double acceleration = 0.6;
    private double velocity = 0.2;
    private double position = 0.4;
    private Angular angular;

    @Test
    public void accelerate_adds_accelerations () {
        angular = new Angular(position, velocity, zeroAcceleration);
        assertThat(angular.accelerate(acceleration), is(new Angular(position, velocity, acceleration)));
    }

    @Test
    public void zero_acceleration_and_one_elapsed_seconds () {
        angular = new Angular(position, velocity, zeroAcceleration);
        assertThat(angular.update(1).acceleration, is(0.0));
        assertThat(angular.update(1).velocity, is(velocity));
        assertThat(angular.update(1).position, is(position + velocity));
    }

    @Test
    public void zero_acceleration_and_tenth_elapsed_seconds () {
        angular = new Angular(position, velocity, zeroAcceleration);
        assertThat(angular.update(0.1).acceleration, is(0.0));
        assertThat(angular.update(0.1).velocity, is(velocity));
        assertThat(angular.update(0.1).position, is(position + velocity * 0.1));
    }

    @Test
    public void nonzero_acceleration_and_one_elapsed_seconds () {
        angular = new Angular(position, velocity, acceleration);
        assertThat(angular.update(1).acceleration, is(0.0));
        assertThat(angular.update(1).velocity, is(velocity + acceleration));
        assertThat(angular.update(1).position, is(position + (velocity + acceleration * 0.5)));
    }

    @Test
    public void nonzero_acceleration_and_tenth_elapsed_seconds () {
        angular = new Angular(position, velocity, acceleration);
        assertThat(angular.update(0.1).acceleration, is(0.0));
        assertThat(angular.update(0.1).velocity, is(velocity + acceleration * 0.1));
        assertThat(angular.update(0.1).position, is(position + (velocity * 0.1 + acceleration * 0.5 * 0.1 * 0.1)));
    }

}
