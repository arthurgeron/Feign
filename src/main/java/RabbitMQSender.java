/**
 * Created by arthurgeron on 11/05/17.
 * Summary: Implements RabbitMQ to send messages to a channel, workers watching that channel will grab
 * the message and do something with it.
 */
import com.google.inject.Guice;
import com.google.inject.Inject;
import com.google.inject.Injector;
import com.rabbitmq.client.Channel;
import com.rabbitmq.client.Connection;

import java.io.IOException;
import java.util.concurrent.TimeoutException;

public class RabbitMQSender{

    RabbitMQAdapter adapter;
    @Inject
    public RabbitMQSender(RabbitMQAdapter adapter) {
        this.adapter = adapter;
    }

    public void SendMessage(String message) throws IOException, TimeoutException {
        adapter.SendMessage(message);
    }

    public void CloseConnect() throws TimeoutException, IOException {
        adapter.CloseConnection();
    }
}
