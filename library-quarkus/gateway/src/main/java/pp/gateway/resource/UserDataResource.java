package pp.gateway.resource;

import org.eclipse.microprofile.jwt.JsonWebToken;
import org.eclipse.microprofile.rest.client.inject.RestClient;
import pp.gateway.client.UserDataService;

import javax.annotation.security.PermitAll;
import javax.annotation.security.RolesAllowed;
import javax.enterprise.context.RequestScoped;
import javax.inject.Inject;
import javax.ws.rs.*;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;

@Path("/users")
@RequestScoped
//@Consumes(MediaType.APPLICATION_JSON)
//@Produces(MediaType.APPLICATION_JSON)
public class UserDataResource {

    @Inject
    @RestClient
    UserDataService userDataService;

    @GET
    @Path("/hello-world")
//    @Produces({MediaType.APPLICATION_JSON})
//    @Consumes({MediaType.APPLICATION_JSON})
    public Response helloWorld() {
        String output = userDataService.helloWorld();
        return Response.ok().entity(output).build();
    }

    @GET
    @Path("/test-user")
    @RolesAllowed({"ROLE_USER"})
//    @Produces({MediaType.APPLICATION_JSON})
//    @Consumes({MediaType.APPLICATION_JSON})
    public Response testUser() {
        String output = userDataService.testUser();
        return Response.ok().entity(output).build();
    }

    @GET
    @Path("/test-admin")
    @RolesAllowed({"ROLE_ADMIN"})

//    @Produces({MediaType.APPLICATION_JSON})
//    @Consumes({MediaType.APPLICATION_JSON})
    public Response testAdmin() {
        String output = userDataService.testAdmin();
        return Response.ok().entity(output).build();
    }

    @GET
    @Path("/test-all")
    @PermitAll
//    @Produces({MediaType.APPLICATION_JSON})
//    @Consumes({MediaType.APPLICATION_JSON})
    public Response testAll() {
        String output = userDataService.testAll();
        return Response.ok().entity(output).build();
    }

    @POST
    @PermitAll
    @Path("/register")
//    @Produces(MediaType.APPLICATION_JSON)
    public Response register(String userData) {
        userDataService.register(userData);
        return Response.ok().entity("User register successful").build();
    }
}
