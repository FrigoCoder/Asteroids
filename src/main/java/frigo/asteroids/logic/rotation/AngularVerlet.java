
package frigo.asteroids.logic.rotation;

import frigo.asteroids.component.Angular;

public class AngularVerlet {

    private Angular angular;

    public AngularVerlet (Angular angular) {
        this.angular = angular;
    }

    public double getVelocity (double elapsed) {
        return angular.velocity + angular.acceleration * elapsed;
    }

    public double getDisplacement (double elapsed) {
        return angular.position + (getVelocity(elapsed) + angular.velocity) * 0.5 * elapsed;
    }

}
