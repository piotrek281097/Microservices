package pp.gateway.test;


import javax.annotation.security.PermitAll;
import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;

@Path("/")
public class HelloWorldResource {

    private final HelloWorldService helloWorldService;

    public HelloWorldResource(HelloWorldService helloWorldService) {
        this.helloWorldService = helloWorldService;
    }

    @GET
    @Path("/test")
    @PermitAll
    @Produces({MediaType.TEXT_PLAIN})
    public String helloWorld() {
        return helloWorldService.helloWorld();
    }
}
