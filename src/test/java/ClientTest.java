import com.google.gson.JsonObject;
import org.junit.Assert;
import org.junit.Test;

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
        JsonObject response = client.makeCall() ;
        Assert.assertNotEquals(response.toString(), "");
    }
}
