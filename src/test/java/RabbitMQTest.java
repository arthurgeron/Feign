import org.junit.After;
import org.junit.Test;

import java.io.IOException;
import java.util.concurrent.TimeoutException;

/**
 * Created by arthurgeron on 11/05/17.
 */
public class RabbitMQTest {

    private Thread thread;

    private void StartRabbitReceiver() {
            thread = new Thread(new RabbitMQReceiver());
        thread.start();
    }

    @Test
    public void ReceiverCanStart() throws IOException,TimeoutException{
        StartRabbitReceiver();
    }
    @Test
    public void SenderCanSendMessages() throws IOException,TimeoutException {
        StartRabbitReceiver();
        RabbitMQSender sender = new RabbitMQSender();
        for(int i = 0; i < 100; i ++)
            sender.SendMessage("Test Message" + i);

    }

    @After
    public void killThread() {
        thread.interrupt();
    }

}
