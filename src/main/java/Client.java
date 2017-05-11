import com.google.gson.JsonObject;
import com.google.gson.JsonParser;
import com.netflix.config.ConfigurationManager;
import feign.Feign;
import feign.ribbon.RibbonClient;

import java.io.IOException;
import java.util.HashMap;
import java.util.Map;


/**
 * Created by arthurgeron on 10/05/17.
 */
public class Client {

    public Client() {
        try {
            ConfigurationManager.loadPropertiesFromResources("voipClient.properties");
        }
        catch (IOException e) {
            e.printStackTrace();
        }
        voipClient = Feign.builder()
                .client(new RibbonClient())
                .target(IClient.class, "http://voipClient");
    }

    IClient voipClient;

    public String getUsers() {
        String response = voipClient.getUsers();
        return response;
    }

    public JsonObject makeCall() {
        Map<String, Object> calls = new HashMap<String, Object>();
        calls.put("from", "Noreg");
        calls.put("to", "Anon2");
        String result = voipClient.makeCall(calls);
        return new JsonParser().parse(result).getAsJsonObject();
    }

}
