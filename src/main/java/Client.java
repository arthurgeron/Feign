import feign.Feign;
import feign.ribbon.RibbonClient;
import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;

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
        String response = voipClient.makeCall(calls);
        JSONParser parser = new JSONParser();
//        JSONObject json = (JSONObject) parser.parse(response);
        return response;
    }

}
