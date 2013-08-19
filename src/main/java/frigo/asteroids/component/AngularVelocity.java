
package frigo.asteroids.component;

public class AngularVelocity extends Scalar {

    public AngularVelocity (double velocity) {
        super(velocity);
    }

    @Override
    public AngularVelocity add (Scalar addend) {
        return (AngularVelocity) super.add(addend);
    }

    @Override
    public AngularVelocity add (double addend) {
        return (AngularVelocity) super.add(addend);
    }

    @Override
    public AngularVelocity mul (Scalar multiplicand) {
        return (AngularVelocity) super.mul(multiplicand);
    }

    @Override
    public AngularVelocity mul (double multiplicand) {
        return (AngularVelocity) super.mul(multiplicand);
    }

    @Override
    protected AngularVelocity create (double velocity) {
        return new AngularVelocity(velocity);
    }

}
