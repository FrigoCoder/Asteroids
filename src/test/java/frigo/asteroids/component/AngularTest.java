
package frigo.asteroids.component;

import static frigo.asteroids.component.Angular.angular;
import static org.hamcrest.Matchers.is;
import static org.junit.Assert.assertThat;

import org.junit.Test;

public class AngularTest {

    private double zeroAcceleration = 0;
    private double acceleration = 0.6;
    private double velocity = 0.2;
    private double position = 0.4;
    private Angular angular = angular().position(position).velocity(velocity).acceleration(acceleration);

    @Test
    public void accelerate_adds_accelerations () {
        angular = angular.acceleration(zeroAcceleration);
        assertThat(angular.accelerate(acceleration), is(angular().position(position).velocity(velocity).acceleration(
            acceleration)));
    }

    @Test
    public void zero_acceleration_and_one_elapsed_seconds () {
        angular = angular.acceleration(zeroAcceleration);
        Angular actual = angular.update(1);
        Angular expected = angular().position(position + velocity).velocity(velocity);
        assertThat(actual, is(expected));
    }

    @Test
    public void zero_acceleration_and_tenth_elapsed_seconds () {
        angular = angular.acceleration(zeroAcceleration);
        Angular actual = angular.update(0.1);
        Angular expected = angular().position(position + velocity * 0.1).velocity(velocity);
        assertThat(actual, is(expected));
    }

    @Test
    public void nonzero_acceleration_and_one_elapsed_seconds () {
        Angular actual = angular.update(1);
        Angular expected =
            angular().position(position + (velocity + acceleration * 0.5)).velocity(velocity + acceleration);
        assertThat(actual, is(expected));
    }

    @Test
    public void nonzero_acceleration_and_tenth_elapsed_seconds () {
        Angular actual = angular.update(0.1);
        Angular expected =
            angular().position(position + (velocity * 0.1 + acceleration * 0.5 * 0.1 * 0.1)).velocity(
                velocity + acceleration * 0.1);
        assertThat(actual, is(expected));
    }

}
