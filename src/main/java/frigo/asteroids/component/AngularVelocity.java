
package frigo.asteroids.component;

public class AngularVelocity extends Scalar {

    public AngularVelocity (double velocity) {
        super(velocity);
    }

    @SuppressWarnings("unchecked")
    @Override
    public AngularVelocity add (Scalar addend) {
        return super.add(addend);
    }

    @SuppressWarnings("unchecked")
    @Override
    public AngularVelocity add (double addend) {
        return super.add(addend);
    }

    @SuppressWarnings("unchecked")
    @Override
    public AngularVelocity mul (Scalar multiplicand) {
        return super.mul(multiplicand);
    }

    @SuppressWarnings("unchecked")
    @Override
    public AngularVelocity mul (double multiplicand) {
        return super.mul(multiplicand);
    }

    @SuppressWarnings("unchecked")
    @Override
    protected AngularVelocity create (double velocity) {
        return new AngularVelocity(velocity);
    }

}
