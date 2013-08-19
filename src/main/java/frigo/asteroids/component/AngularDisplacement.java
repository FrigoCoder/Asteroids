
package frigo.asteroids.component;

public class AngularDisplacement extends Scalar {

    public AngularDisplacement (double radians) {
        super(radians);
    }

    @Override
    public AngularDisplacement add (Scalar addend) {
        return (AngularDisplacement) super.add(addend);
    }

    @Override
    public AngularDisplacement add (double addend) {
        return (AngularDisplacement) super.add(addend);
    }

    @Override
    public AngularDisplacement mul (Scalar multiplicand) {
        return (AngularDisplacement) super.mul(multiplicand);
    }

    @Override
    public AngularDisplacement mul (double multiplicand) {
        return (AngularDisplacement) super.mul(multiplicand);
    }

    @Override
    protected AngularDisplacement create (double radians) {
        return new AngularDisplacement(radians);
    }

}
