/**
 * Created by arthurgeron on 09/05/17.
 */
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
        String output = "From: " + from + ". To: " + to + ".\n";
        return Response.status(200).entity(output).build();
    }
}
