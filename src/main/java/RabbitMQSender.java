/**
 * Created by arthurgeron on 11/05/17.
 * Summary: Implements RabbitMQ to send messages to a channel, workers watching that channel will grab
 * the message and do something with it.
 */
import com.rabbitmq.client.Channel;
import com.rabbitmq.client.Connection;
import com.rabbitmq.client.ConnectionFactory;

import java.io.IOException;
import java.util.concurrent.TimeoutException;

public class RabbitMQSender extends  AbstractRabbitMQ{

    private Connection connection;
    private Channel channel;

    private void OpenConnection() throws IOException, TimeoutException {
        ConnectionFactory factory = new ConnectionFactory();
        factory.setHost("localhost");
        connection = factory.newConnection();
        channel = connection.createChannel();
        System.out.println("RabbitMQ:Connection to server opened");
    }

    public void SendMessage(String message) throws  IOException, TimeoutException{
        OpenConnection();
        channel.queueDeclare(QueueName, false, false, false, null);
        channel.basicPublish("", QueueName, null, message.getBytes());
        System.out.println("[X] Sent");
        CloseConnection();
    }

    private void CloseConnection() throws  IOException, TimeoutException {
        channel.close();
        connection.close();
        System.out.println("RabbitMQ-Sender:Connection to server closed");
    }
}
