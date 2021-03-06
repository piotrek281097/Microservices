package pp.gateway.resource;

import pp.gateway.domain.UserData;
import pp.gateway.security.JwtDto;
import pp.gateway.security.ResponseEntity;
import pp.gateway.security.ResponseEntityService;
import pp.gateway.security.TokenService;

import javax.inject.Inject;
import javax.ws.rs.*;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;
import java.util.Collections;

@Path("/login")
@Consumes(MediaType.APPLICATION_JSON)
@Produces(MediaType.APPLICATION_JSON)
public class AuthResource {

    private static final String ADMIN_USERNAME = "admin";
    public static final String ADMIN_PASSWORD = "nimda";

    @Inject
    TokenService tokenService;

    @Inject
    ResponseEntityService responseEntityService;

    @POST
    @Path("/")
    public Response login(UserData userData) throws Exception {
        if (ADMIN_USERNAME.equals(userData.getUsername())
            && ADMIN_PASSWORD.equals(userData.getPassword())) {
            return Response.ok(new JwtDto(tokenService.generateUserToken(userData.getUsername(), "ROLE_ADMIN"), Collections.singletonList("ROLE_ADMIN"))).build();
        }
        ResponseEntity responseEntity = responseEntityService.checkForUser(userData);
        if(responseEntity.isValid()){
            return Response.ok(new JwtDto(tokenService.generateUserToken(userData.getUsername(), "ROLE_USER"), Collections.singletonList("ROLE_USER"))).build();
        } else {
            throw new WebApplicationException(Response.status(404).entity(responseEntity.getMessage()).build());
        }
    }

}
