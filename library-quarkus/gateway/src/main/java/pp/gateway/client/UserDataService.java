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

    @GET
    @Path("/hello-world")
//    @Produces({MediaType.APPLICATION_JSON})
//    @Consumes({MediaType.APPLICATION_JSON})
    String helloWorld();

    @POST
    @Path("/register")
//    @Produces({MediaType.APPLICATION_JSON})
    Response register(String userData);

    @GET
    @Path("/users")
//    @Secured("ROLE_ADMIN")
    Response getUsers();


    @GET
    @Path("/{username}")
//    @Secured({ "ROLE_USER", "ROLE_ADMIN" })
    Response getUserByUsername(@PathParam String username);

    @DELETE
    @Path("/delete/{userId}")
    Response deleteUserById(@PathParam long userId);

    @POST
    @Path("/update")
    Response updateUser(String userData);


    @GET
    @Path("/test-user")
    String testUser();

    @GET
    @Path("/test-admin")
    String testAdmin();

    @GET
    @Path("/test-all")
    String testAll();
}
