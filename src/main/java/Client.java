import feign.Feign;
import feign.Logger;
import feign.okhttp.OkHttpClient;
import feign.slf4j.Slf4jLogger;
import org.junit.Assert;

import java.util.HashMap;
import java.util.Map;

/**
 * Created by arthurgeron on 10/05/17.
 */
public class Client {


    public String getUsers() {
        IClient voipClient = Feign.builder()
                .client(new OkHttpClient())
                .logger(new Slf4jLogger(IClient.class))
                .logLevel(Logger.Level.FULL)
                .target(IClient.class, "http://localhost:8080");
        String response = voipClient.getUsers();
        return response;
    }

    public String makeCall() {
        IClient voipClient = Feign.builder()
                .client(new OkHttpClient())
                .logger(new Slf4jLogger(IClient.class))
                .logLevel(Logger.Level.FULL)
                .target(IClient.class, "http://localhost:8080");
        Map<String, Object> calls = new HashMap<String, Object>();
        calls.put("from", "Noreg");
        calls.put("to", "Anon2");
        String response = voipClient.makeCall(calls);
        return response;
    }

}
