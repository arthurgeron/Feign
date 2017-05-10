import org.json.simple.JSONObject;
import org.junit.Test;
import org.junit.Assert;

/**
 * Created by arthurgeron on 10/05/17.
 */
public class ClientTest {

    @Test
    public void clientCanGetUsers() {
        Client client = new Client();
        String response = client.getUsers();
        Assert.assertNotEquals(response, "");
    }

    @Test
    public void clientCanMakeCalls() {
        Client client = new Client();
        JSONObject response = client.makeCall() ;
        Assert.assertNotNull(response);
    }
}
