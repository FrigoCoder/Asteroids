
package frigo.asteroids.component;

public class AngularAcceleration extends Scalar {

    public AngularAcceleration (double acceleration) {
        super(acceleration);
    }

    @SuppressWarnings("unchecked")
    @Override
    public AngularAcceleration add (Scalar addend) {
        return super.add(addend);
    }

    @SuppressWarnings("unchecked")
    @Override
    public AngularAcceleration add (double addend) {
        return super.add(addend);
    }

    @SuppressWarnings("unchecked")
    @Override
    public AngularAcceleration mul (Scalar multiplicand) {
        return super.mul(multiplicand);
    }

    @SuppressWarnings("unchecked")
    @Override
    public AngularAcceleration mul (double multiplicand) {
        return super.mul(multiplicand);
    }

    @SuppressWarnings("unchecked")
    @Override
    protected AngularAcceleration create (double acceleration) {
        return new AngularAcceleration(acceleration);
    }

}
