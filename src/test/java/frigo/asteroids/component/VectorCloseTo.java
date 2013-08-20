
package frigo.asteroids.component;

import org.hamcrest.Description;
import org.hamcrest.Factory;
import org.hamcrest.TypeSafeMatcher;

public class VectorCloseTo extends TypeSafeMatcher<Vector> {

    @Factory
    public static TypeSafeMatcher<Vector> closeTo (Vector expected) {
        return new VectorCloseTo(expected, 0);
    }

    @Factory
    public static TypeSafeMatcher<Vector> closeTo (Vector expected, double delta) {
        return new VectorCloseTo(expected, delta);
    }

    private Vector expected;
    private double delta;

    public VectorCloseTo (Vector expected, double delta) {
        this.expected = expected;
        this.delta = delta;
    }

    @Override
    protected boolean matchesSafely (Vector actual) {
        return distance(actual) <= delta;
    }

    @Override
    public void describeTo (Description description) {
        description.appendText("a Vector within ").appendValue(delta).appendText(" radius of ").appendValue(expected);
    }

    @Override
    protected void describeMismatchSafely (Vector actual, Description description) {
        description.appendValue(actual).appendText(" differed by ").appendValue(distance(actual));
    }

    private double distance (Vector actual) {
        return expected.sub(actual).length();
    }

}
