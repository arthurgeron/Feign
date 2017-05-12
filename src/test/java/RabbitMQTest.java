import com.google.inject.Guice;
import com.google.inject.Injector;
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
            Injector injector = Guice.createInjector(new AppModule());
            RabbitMQWorker worker = injector.getInstance(RabbitMQWorker.class);
            thread = new Thread(worker);
            thread.start();

    }

    @Test
    public void ReceiverCanStart() throws IOException,TimeoutException{
        StartRabbitReceiver();
        killThread();
    }
    @Test
    public void SenderCanSendMessages() throws IOException,TimeoutException {
        Injector injector = Guice.createInjector(new AppModule());
        RabbitMQSender sender = injector.getInstance(RabbitMQSender.class);
        for(int i = 0; i < 100; i ++)
            sender.SendMessage("Test Message" + i);

    }

    public void killThread() {
        thread.interrupt();
    }

}
