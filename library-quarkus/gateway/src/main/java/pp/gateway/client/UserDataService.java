package pp.gateway.client;

import org.eclipse.microprofile.rest.client.inject.RegisterRestClient;
import org.jboss.resteasy.annotations.jaxrs.PathParam;

import javax.inject.Singleton;
import javax.json.JsonArray;
import javax.json.JsonStructure;
import javax.ws.rs.*;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;
import java.util.Collections;

@Singleton
@RegisterRestClient
public interface UserDataService {

    @POST
    @Path("/register")
    Response register(String userData);

    @GET
    @Path("/users")
    Response getUsers();

    @GET
    @Path("/{username}")
    Response getUserByUsername(@PathParam String username);

    @DELETE
    @Path("/delete/{userId}")
    Response deleteUserById(@PathParam long userId);

    @POST
    @Path("/update")
    Response updateUser(String userData);

}
