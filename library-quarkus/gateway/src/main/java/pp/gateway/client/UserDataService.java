package pp.gateway.client;

import org.eclipse.microprofile.rest.client.inject.RegisterRestClient;

import javax.inject.Singleton;
import javax.ws.rs.*;
import javax.ws.rs.core.MediaType;

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
    String register(String userData);

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
