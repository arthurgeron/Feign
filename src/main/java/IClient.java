import feign.QueryMap;
import feign.RequestLine;

import java.util.Map;

/**
 * Created by arthurgeron on 10/05/17.
 */
public interface IClient {
    @RequestLine("GET /api/users")
    String getUsers();

    @RequestLine("POST /api/call")
    String makeCall(@QueryMap Map<String, Object> queryMap);

}
    