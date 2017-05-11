/**
 * Created by arthurgeron on 09/05/17.
 */

import org.json.simple.JSONObject;

import javax.ws.rs.*;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;

@Path("/api")
public class Voip {


    @GET
    @Path("/users")
    @Produces(MediaType.TEXT_PLAIN)
    public Response getMsg() {

        String output = "List of users is empty.\n";

        return Response.status(200).entity(output).build();

    }

    @POST
    @Path("/call")
    @Produces(MediaType.TEXT_PLAIN)
    public Response getMsg(@DefaultValue("") @QueryParam( value = "from") final String from,
                           @DefaultValue("") @QueryParam( value = "to") final String to
    ) {
        JSONObject json = new JSONObject();
        json.put("from", from);
        json.put("to", to);
        return Response.status(200).entity(json.toString()).build();
    }
}
