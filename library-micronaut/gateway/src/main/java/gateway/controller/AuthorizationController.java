package gateway.controller;

import gateway.auth.AuthorizationOperations;
import io.micronaut.http.HttpResponse;
import io.micronaut.http.annotation.Body;
import io.micronaut.http.annotation.Controller;
import io.micronaut.http.annotation.Get;
import io.micronaut.http.annotation.Post;
import io.micronaut.security.annotation.Secured;
import io.micronaut.security.rules.SecurityRule;

@Controller("/")
public class AuthorizationController {
    protected final AuthorizationOperations authorizationOperations;

    public AuthorizationController(AuthorizationOperations authorizationOperations) {
        this.authorizationOperations = authorizationOperations;
    }

    @Post("/register")
    @Secured(SecurityRule.IS_ANONYMOUS)
    public HttpResponse register(@Body String userData) {
        return authorizationOperations.register(userData);
    }

    @Get("/users")
    @Secured("ADMIN")
    public HttpResponse getAllUsers() {
        return authorizationOperations.getAllUsers();
    }
}
