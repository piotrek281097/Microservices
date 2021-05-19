package gateway;

import gateway.auth.AuthorizationOperations;
import io.micronaut.http.HttpResponse;
import io.micronaut.http.annotation.Body;
import io.micronaut.http.annotation.Controller;
import io.micronaut.http.annotation.Post;
import io.micronaut.security.annotation.Secured;
import io.micronaut.security.rules.SecurityRule;

@Controller("/")
public class GatewayController {
    protected final AuthorizationOperations authorizationOperations;

    public GatewayController(AuthorizationOperations authorizationOperations) {
        this.authorizationOperations = authorizationOperations;
    }

    @Post("/users/register")
    @Secured(SecurityRule.IS_ANONYMOUS)
    public HttpResponse register(@Body String userData) {
        System.out.println("XD");
        return authorizationOperations.register(userData);
    }
}
