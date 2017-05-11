import com.google.inject.Inject;
import com.rabbitmq.client.Channel;
import com.rabbitmq.client.Connection;
import com.rabbitmq.client.ConnectionFactory;
import org.junit.After;

import java.io.IOException;
import java.util.concurrent.TimeoutException;

/**
 * Created by arthurgeron on 11/05/17.
 */
public class RabbitMQAdapter extends AbstractRabbitMQ {

    Connection connection;
    Channel channel;

    @Inject
    public RabbitMQAdapter(ConnectionFactory factory) throws IOException, TimeoutException {
        factory.setHost("localhost");
        connection = factory.newConnection();
        channel = connection.createChannel();
    }

    public void SendMessage(String message) throws  IOException, TimeoutException{
        channel.queueDeclare(QueueName, false, false, false, null);
        channel.basicPublish("", QueueName, null, message.getBytes());
        System.out.println("[X] Sent");
    }

    public void CloseConnection() throws  IOException, TimeoutException {
        channel.close();
        connection.close();
        System.out.println("RabbitMQ-Sender:Connection to server closed");
    }
}
