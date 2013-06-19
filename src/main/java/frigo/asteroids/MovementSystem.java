
package frigo.asteroids;

public class MovementSystem extends Updater {

    @Override
    public void update (World world) {
        for( Entity entity : world.getEntities() ){
            Speed speed = entity.getComponent(Speed.class);
            Position position = entity.getComponent(Position.class);

        }
    }
}
