
package frigo.asteroids.component;

public class AngularAcceleration extends Scalar {

    public AngularAcceleration (double acceleration) {
        super(acceleration);
    }

    @Override
    public AngularAcceleration add (Scalar addend) {
        return (AngularAcceleration) super.add(addend);
    }

    @Override
    public AngularAcceleration add (double addend) {
        return (AngularAcceleration) super.add(addend);
    }

    @Override
    public AngularAcceleration mul (Scalar multiplicand) {
        return (AngularAcceleration) super.mul(multiplicand);
    }

    @Override
    public AngularAcceleration mul (double multiplicand) {
        return (AngularAcceleration) super.mul(multiplicand);
    }

    @Override
    protected AngularAcceleration create (double acceleration) {
        return new AngularAcceleration(acceleration);
    }

}
