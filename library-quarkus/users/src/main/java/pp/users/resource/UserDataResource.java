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
    @Produces({MediaType.APPLICATION_JSON})
    public Response getUsers() {
        return Response.ok().entity(userDataService.getAllUsers()).build();
    }

    @GET
    @Path("/{username}")
    @Produces({MediaType.APPLICATION_JSON})
    public Response getUserByUsername(@PathParam String username) {
        return Response.ok().entity(userDataService.getUserByUsername(username)).build();
    }

    @DELETE
    @Path("/delete/{userId}")
    public Response deleteUserById(@PathParam long userId) {
        userDataService.deleteUserById(userId);
        return Response.ok(Collections.singletonMap("msg", "OK")).build();
    }

    @POST
    @Path("/update")
    public Response updateUser(UserData userData) {
        userDataService.updateUser(userData);
        return Response.ok(Collections.singletonMap("msg", "OK")).build();
    }

}
