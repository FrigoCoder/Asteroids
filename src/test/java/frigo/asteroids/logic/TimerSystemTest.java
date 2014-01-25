
package frigo.asteroids.logic;

import static org.hamcrest.Matchers.is;
import static org.junit.Assert.assertThat;

import org.junit.Before;
import org.junit.Test;

import frigo.asteroids.component.SelfDestruct;
import frigo.asteroids.component.Timer;
import frigo.asteroids.core.Entity;
import frigo.asteroids.core.World;

public class TimerSystemTest {

    private World world = new World();
    private Entity entity;

    @Before
    public void setUp () {
        world.addLogic(new TimerSystem());
        world.init();
        entity = world.createEntity();
        entity.add(new Timer(new SelfDestruct(), 1.0));
    }

    @Test
    public void non_elapsed_timer_does_not_emit_component () {
        world.update(0.9);
        assertThat(entity.has(SelfDestruct.class), is(false));
    }

    @Test
    public void non_elapsed_timer_is_not_deleted () {
        world.update(0.9);
        assertThat(entity.has(Timer.class), is(true));
    }

    @Test
    public void elapsed_timer_emits_component () {
        world.update(1.0);
        assertThat(entity.has(SelfDestruct.class), is(true));
    }

    @Test
    public void elapsed_timer_is_deleted () {
        world.update(1.0);
        assertThat(entity.has(Timer.class), is(false));
    }

    @Test
    public void timer_is_elapsed_in_two_turns () {
        world.update(0.5);
        world.update(0.5);
        assertThat(entity.has(SelfDestruct.class), is(true));
    }

    @Test
    public void elapsed_timer_emits_component_only_once () {
        world.update(1.0);
        entity.remove(SelfDestruct.class);
        world.update(1.0);
        assertThat(entity.has(SelfDestruct.class), is(false));
    }

}
