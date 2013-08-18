
package frigo.asteroids.component;

public class AngularDisplacement extends Scalar {

    public AngularDisplacement (double radians) {
        super(radians);
    }

    @SuppressWarnings("unchecked")
    @Override
    public AngularDisplacement add (Scalar addend) {
        return super.add(addend);
    }

    @SuppressWarnings("unchecked")
    @Override
    public AngularDisplacement add (double addend) {
        return super.add(addend);
    }

    @SuppressWarnings("unchecked")
    @Override
    public AngularDisplacement mul (Scalar multiplicand) {
        return super.mul(multiplicand);
    }

    @SuppressWarnings("unchecked")
    @Override
    public AngularDisplacement mul (double multiplicand) {
        return super.mul(multiplicand);
    }

    @SuppressWarnings("unchecked")
    @Override
    protected AngularDisplacement create (double radians) {
        return new AngularDisplacement(radians);
    }

}
