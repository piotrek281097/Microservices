package pp.users.resource;


import pp.users.domain.UserData;
import pp.users.service.UserDataService;

import javax.inject.Inject;
import javax.transaction.Transactional;
import javax.ws.rs.*;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;

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
