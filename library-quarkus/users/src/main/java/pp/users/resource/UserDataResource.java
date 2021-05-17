package pp.users.resource;


import pp.users.domain.UserData;
import pp.users.service.UserDataService;

import javax.inject.Inject;
import javax.ws.rs.*;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;
import java.util.Collections;
import org.jboss.resteasy.annotations.jaxrs.PathParam;

@Path("/")
//@Consumes(MediaType.APPLICATION_JSON)
//@Produces(MediaType.APPLICATION_JSON)
public class UserDataResource {
    @Inject
    UserDataService userDataService;

    @POST
    @Path("/register")
    public Response register(UserData userData) {
        userDataService.register(userData);
        return Response.ok().entity("User register successful").build();
    }

    @GET
    @Path("/users")
//    @Secured("ROLE_ADMIN")
    @Produces({MediaType.APPLICATION_JSON})
    public Response getUsers() {
        return Response.ok().entity(userDataService.getAllUsers()).build();
    }
    @GET
    @Path("/{username}")
//    @Secured({ "ROLE_USER", "ROLE_ADMIN" })
    @Produces({MediaType.APPLICATION_JSON})
    public Response getUserByUsername(@PathParam String username) {
        return Response.ok().entity(userDataService.getUserByUsername(username)).build();
    }

    @DELETE
    @Path("/delete/{userId}")
//    @Secured("ROLE_ADMIN")
    public Response deleteUserById(@PathParam long userId) {
        userDataService.deleteUserById(userId);
        return Response.ok(Collections.singletonMap("msg", "OK")).build();
    }

    @POST
    @Path("/update")
//    @Secured("ROLE_USER")
    public Response updateUser(UserData userData) {
        userDataService.updateUser(userData);
        return Response.ok(Collections.singletonMap("msg", "OK")).build();
    }

    @GET
    @Path("/hello-world")
//    @Produces({MediaType.APPLICATION_JSON})
//    @Consumes({MediaType.APPLICATION_JSON})
    public String helloWorld() {
        return "hello-world";
    }

    @GET
    @Path("/test-user")
//    @Produces({MediaType.APPLICATION_JSON})
//    @Consumes({MediaType.APPLICATION_JSON})
    public String testUser() {
        return "hello-user secured";
    }

    @GET
    @Path("/test-admin")
//    @Produces({MediaType.APPLICATION_JSON})
//    @Consumes({MediaType.APPLICATION_JSON})
    public String testAdmin() {
        return "hello-admin secured";
    }

    @GET
    @Path("/test-all")
//    @Produces({MediaType.APPLICATION_JSON})
//    @Consumes({MediaType.APPLICATION_JSON})
    public String testAlll() {
        return "hello-all not secured";
    }
}
