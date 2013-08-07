
package frigo.asteroids.core;

import static java.util.Arrays.asList;
import static org.hamcrest.Matchers.hasItem;
import static org.hamcrest.Matchers.hasSize;
import static org.hamcrest.Matchers.is;
import static org.hamcrest.Matchers.sameInstance;
import static org.junit.Assert.assertThat;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.verify;

import java.util.HashSet;
import java.util.List;
import java.util.Set;

import org.junit.Test;

import frigo.asteroids.component.Position;
import frigo.asteroids.component.Velocity;

public class WorldTest {

    private World world = new World();
    private Entity entity = world.createEntity();
    private Logic logic = mock(Logic.class);
    private Message message = new Message();

    @Test
    public void added_entity_is_present () {
        world.addEntity(entity);
        assertThat(world.getEntities(), hasItem(entity));
    }

    @Test
    public void entities_matching_aspect_are_returned () {
        world.setComponent(entity, new Position(1, 1));
        world.setComponent(entity, new Velocity(1, 1));
        world.addEntity(entity);

        Entity empty = world.createEntity();
        world.addEntity(empty);

        Aspect aspect = Aspect.allOf(Position.class, Velocity.class);

        Set<Entity> expected = new HashSet<>(asList(entity));
        assertThat(world.getEntitiesFor(aspect), is(expected));
    }

    @Test
    public void logic_is_initialized_at_init_call () {
        world.addLogic(logic);
        world.init();
        verify(logic).init(world);
    }

    @Test
    public void logic_is_updated_at_update_call () {
        world.addLogic(logic);
        world.update(1);
        verify(logic).update(world, 1);
    }

    @Test
    public void logic_is_updated_by_the_elapsed_seconds () {
        world.addLogic(logic);
        world.update(0.01);
        verify(logic).update(world, 0.01);
    }

    @Test
    public void added_message_can_be_retrieved () {
        world.addMessage(message);
        List<Message> list = world.getMessages(message.getClass());
        assertThat(list, hasSize(1));
        assertThat(list.get(0), sameInstance(message));
    }

    @Test
    public void two_added_messages_can_be_retrieved () {
        Message message2 = new Message();
        world.addMessage(message);
        world.addMessage(message2);
        List<Message> list = world.getMessages(message.getClass());
        assertThat(list, hasSize(2));
        assertThat(list.get(0), sameInstance(message));
        assertThat(list.get(1), sameInstance(message2));
    }

    @Test
    public void no_added_messages_return_empty_list () {
        List<Message> list = world.getMessages(Message.class);
        assertThat(list, hasSize(0));

    }
}
