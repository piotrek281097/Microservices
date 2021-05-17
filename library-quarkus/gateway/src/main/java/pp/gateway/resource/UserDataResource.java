package pp.gateway.resource;

import org.eclipse.microprofile.jwt.JsonWebToken;
import org.eclipse.microprofile.rest.client.inject.RestClient;
import org.jboss.resteasy.annotations.jaxrs.PathParam;
import pp.gateway.client.UserDataService;

import javax.annotation.security.PermitAll;
import javax.annotation.security.RolesAllowed;
import javax.enterprise.context.RequestScoped;
import javax.inject.Inject;
import javax.json.JsonArray;
import javax.json.JsonStructure;
import javax.ws.rs.*;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;
import java.util.Collections;

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

    @POST
    @PermitAll
    @Path("/register")
    public Response register(String userData) {
        userDataService.register(userData);
        return Response.ok().build();
    }

    @GET
    @Path("/users")
    @RolesAllowed("ROLE_ADMIN")
    public Response getUsers() {
        return userDataService.getUsers();
    }

    @GET
    @Path("/{username}")
    @RolesAllowed({ "ROLE_USER", "ROLE_ADMIN" })
    public Response getUserByUsername(@PathParam String username) {
        return userDataService.getUserByUsername(username);
    }

    @DELETE
    @Path("/delete/{userId}")
    @RolesAllowed("ROLE_ADMIN")
    public Response deleteUserById(@PathParam long userId) {
        userDataService.deleteUserById(userId);
        return Response.ok(Collections.singletonMap("msg", "OK")).build();
    }

    @POST
    @Path("/update")
    @RolesAllowed("ROLE_USER")
    public Response updateUser(String userData) {
        userDataService.updateUser(userData);
        return Response.ok(Collections.singletonMap("msg", "OK")).build();
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


}
