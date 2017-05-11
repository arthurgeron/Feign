/**
 * Created by arthurgeron on 11/05/17.
 */

import com.google.inject.Guice;
import com.google.inject.Inject;
import com.google.inject.Injector;
import com.rabbitmq.client.*;

import java.io.IOException;
import java.util.concurrent.TimeoutException;

public class RabbitMQWorker extends AbstractRabbitMQ {

    private Channel channel;

    @Inject
    public RabbitMQWorker(RabbitMQAdapter adapter) {
        this.channel = adapter.channel;
    }



    public static void main(String[] args) throws IOException, TimeoutException {
        Injector injector = Guice.createInjector(new AppModule());
        RabbitMQWorker worker = injector.getInstance(RabbitMQWorker.class);
        Channel channel = worker.channel;
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
