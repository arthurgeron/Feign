import com.google.gson.JsonObject;
import com.google.inject.Guice;
import com.google.inject.Injector;
import org.json.simple.JSONObject;
import org.junit.After;
import org.junit.Assert;
import org.junit.Test;


/**
 * Created by arthurgeron on 09/05/17.
 */
public class AppTest {

    private IClient voipClient;
    private Thread thread;

    public AppTest() {
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
        Client client = new Client();
        String response = client.getUsers();
        Assert.assertTrue(response != "");
        System.out.println(response);


    }

    @Test
    public void testMakeCall() throws Exception {
        Client client = new Client();
        JsonObject response = client.makeCall() ;
        Assert.assertNotNull(response);
        System.out.println(response.toString());
    }

    @After
    public void killThread() {
        thread.interrupt();
    }

}
