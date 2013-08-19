
package frigo.asteroids.component;

import frigo.asteroids.core.Component;

public class Scalar extends Component {

    public final double value;

    public Scalar (double value) {
        this.value = value;
    }

    public Scalar add (Scalar addend) {
        return add(addend.value);
    }

    public Scalar add (double addend) {
        return create(value + addend);
    }

    public Scalar mul (Scalar multiplicand) {
        return mul(multiplicand.value);
    }

    public Scalar mul (double multiplicand) {
        return create(value * multiplicand);
    }

    protected Scalar create (double val) {
        return new Scalar(val);
    }

}
