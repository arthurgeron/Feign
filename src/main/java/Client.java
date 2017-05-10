import feign.Feign;
import org.json.simple.JSONObject;

import java.util.HashMap;
import java.util.Map;


/**
 * Created by arthurgeron on 10/05/17.
 */
public class Client {

    IClient voipClient = Feign.builder()
            .client(new RibbonClient())
            .target(IClient.class, "http://ribbonclient");


    public String getUsers() {
        String response = voipClient.getUsers();
        return response;
    }

    public String makeCall() {
        Map<String, Object> calls = new HashMap<String, Object>();
        calls.put("from", "Noreg");
        calls.put("to", "Anon2");
        JSONObject response = voipClient.makeCall(calls);
        return response.toString();
    }

}
