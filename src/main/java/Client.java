import feign.Feign;
import feign.Logger;
import feign.okhttp.OkHttpClient;
import feign.slf4j.Slf4jLogger;
import org.json.simple.JSONObject;

import java.util.HashMap;
import java.util.Map;

/**
 * Created by arthurgeron on 10/05/17.
 */
public class Client {

    IClient voipClient = Feign.builder()
            .client(new OkHttpClient())
            .logger(new Slf4jLogger(IClient.class))
            .logLevel(Logger.Level.FULL)
            .target(IClient.class, "http://localhost:8080");

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
