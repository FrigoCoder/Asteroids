
package frigo.asteroids.component;

import java.lang.reflect.Constructor;

import com.google.common.base.Throwables;

import frigo.asteroids.core.Component;

public class Scalar extends Component {

    public final double value;

    public Scalar (double value) {
        this.value = value;
    }

    public <T extends Scalar> T add (Scalar addend) {
        return add(addend.value);
    }

    public <T extends Scalar> T add (double addend) {
        return create(value + addend);
    }

    public <T extends Scalar> T mul (Scalar multiplicand) {
        return mul(multiplicand.value);
    }

    public <T extends Scalar> T mul (double multiplicand) {
        return create(value * multiplicand);
    }

    protected <T extends Scalar> T create (double val) {
        try{
            Constructor<? extends Scalar> constructor = getClass().getConstructor(Double.TYPE);
            return (T) constructor.newInstance(val);
        }catch( Exception e ){
            throw Throwables.propagate(e);
        }
    }

}
