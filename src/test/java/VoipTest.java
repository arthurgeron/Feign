import com.google.inject.Guice;
import com.google.inject.Injector;
import feign.Feign;
import feign.Logger;
import feign.okhttp.OkHttpClient;
import feign.slf4j.Slf4jLogger;
import org.junit.After;
import org.junit.Assert;
import org.junit.Test;

import java.util.HashMap;
import java.util.Map;


/**
 * Created by arthurgeron on 09/05/17.
 */
public class VoipTest {

    private IClient voipClient;
    private Thread thread;

    public VoipTest() {
        Injector injector = Guice.createInjector(new AppModule());

        App  app = injector.getInstance(App.class);
        thread = new Thread(app);
        thread.start();

    }

    public IClient getVoipCLient() {
        return voipClient;
    }


    @Test
    public void testGetUsers() throws Exception {
        voipClient = Feign.builder()
                .client(new OkHttpClient())
                .logger(new Slf4jLogger(IClient.class))
                .logLevel(Logger.Level.FULL)
                .target(IClient.class, "http://localhost:8080");
        String response = voipClient.getUsers();
        Assert.assertTrue(response != "");
        System.out.println(response);


    }

    @Test
    public void testMakeCall() throws Exception {
        voipClient = Feign.builder()
                .client(new OkHttpClient())
                .logger(new Slf4jLogger(IClient.class))
                .logLevel(Logger.Level.FULL)
                .target(IClient.class, "http://localhost:8080");
        Map<String, Object> calls = new HashMap<String, Object>();
        calls.put("from", "Noreg");
        calls.put("to", "Anon2");
        String response = voipClient.makeCall(calls);
        Assert.assertTrue(response != "");
        System.out.println(response);
    }

    @After
    public void killThread() {
        thread.interrupt();
    }

}
