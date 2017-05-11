/**
 * Created by arthurgeron on 11/05/17.
 */

import com.rabbitmq.client.*;

import java.io.IOException;
import java.util.concurrent.TimeoutException;

public class RabbitMQWorker extends AbstractRabbitMQServer {

    public static void main(String[] args) throws IOException, TimeoutException {
        ConnectionFactory factory = new ConnectionFactory();
        factory.setHost("localhost");
        Connection connection = factory.newConnection();
        Channel channel = connection.createChannel();
        System.out.println("RabbitMQ-Receiver: Connected to server" );

        channel.queueDeclare(QueueName, false, false, false, null);
        Consumer consumer = new DefaultConsumer(channel) {
            @Override
            public void handleDelivery(String consumerTag, Envelope envelope,
                                       AMQP.BasicProperties properties, byte[] body)
                    throws IOException {
                String message = new String(body, "UTF-8");
                System.out.println(" [x] Received '" + message + "'");
            }
        };
        System.out.println("RabbitMQ-Receiver: Started to consume queue: " + QueueName );
        channel.basicConsume(QueueName, true, consumer);
    }

    public void run() {
    }
}
