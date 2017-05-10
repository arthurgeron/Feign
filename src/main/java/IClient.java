import feign.QueryMap;
import feign.RequestLine;
import org.json.simple.JSONObject;

import java.util.Map;

/**
 * Created by arthurgeron on 10/05/17.
 */
public interface IClient {
    @RequestLine("GET /api/users")
    String getUsers();

    @RequestLine("POST /api/call")
    JSONObject makeCall(@QueryMap Map<String, Object> queryMap);

}
    