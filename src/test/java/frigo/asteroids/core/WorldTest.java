
package frigo.asteroids.core;

import static java.util.Arrays.asList;
import static org.hamcrest.Matchers.hasItem;
import static org.hamcrest.Matchers.hasSize;
import static org.hamcrest.Matchers.is;
import static org.hamcrest.Matchers.sameInstance;
import static org.junit.Assert.assertThat;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.verify;

import java.util.LinkedList;
import java.util.List;

import org.junit.Test;

import frigo.asteroids.component.Planar;
import frigo.asteroids.component.Vector;

public class WorldTest {

    private World world = new World();
    private Entity entity = world.createEntity();
    private Logic logic = mock(Logic.class);
    private Message message = new Message();

    @Test
    public void added_entity_is_present () {
        assertThat(world.getEntities(), hasItem(entity));
    }

    @Test
    public void entities_matching_aspect_are_returned () {
        world.set(entity, new Planar(new Vector(1, 1), new Vector(1, 1)));

        world.createEntity();

        Aspect aspect = Aspect.allOf(Planar.class);

        List<Entity> expected = new LinkedList<>(asList(entity));
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
