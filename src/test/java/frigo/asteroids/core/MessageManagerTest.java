
package frigo.asteroids.core;

import static org.hamcrest.Matchers.hasSize;
import static org.hamcrest.Matchers.sameInstance;
import static org.junit.Assert.assertThat;

import java.util.List;

import org.junit.Test;

public class MessageManagerTest {

    private MessageManager messageManager = new MessageManager();
    private Message message = new Message();

    @Test
    public void added_message_can_be_retrieved () {
        messageManager.add(message);
        List<Message> list = messageManager.get(Message.class);
        assertThat(list, hasSize(1));
        assertThat(list.get(0), sameInstance(message));
    }

    @Test
    public void two_added_messages_can_be_retrieved () {
        Message message2 = new Message();
        messageManager.add(message);
        messageManager.add(message2);
        List<Message> list = messageManager.get(Message.class);
        assertThat(list, hasSize(2));
        assertThat(list.get(0), sameInstance(message));
        assertThat(list.get(1), sameInstance(message2));
    }

    @Test
    public void no_added_messages_return_empty_list () {
        List<Message> list = messageManager.get(Message.class);
        assertThat(list, hasSize(0));
    }

}
