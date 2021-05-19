package gateway.controller;

import gateway.auth.AuthorizationOperations;
import gateway.exception.RegisterException;
import io.micronaut.http.HttpResponse;
import io.micronaut.http.annotation.*;
import io.micronaut.http.client.exceptions.HttpClientResponseException;
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
        try {
            return authorizationOperations.register(userData);
        } catch (HttpClientResponseException exception) {
            throw new RegisterException(exception.getMessage());
        }
    }

    @Get("/users")
    @Secured("ADMIN")
    public HttpResponse getAllUsers() {
        return authorizationOperations.getAllUsers();
    }

    @Delete("/users/delete/{userId}")
    @Secured("ADMIN")
    public HttpResponse deleteUserById(@PathVariable long userId) {
        return authorizationOperations.deleteUserById(userId);
    }

    @Get("/users/{username}")
    @Secured(SecurityRule.IS_AUTHENTICATED)
    public HttpResponse getUserByUsername(@PathVariable String username) {
        return authorizationOperations.getUserByUsername(username);
    }

    @Post("/users/update")
    @Secured("USER")
    public HttpResponse updateUser(@Body String userData) {
        return authorizationOperations.updateUser(userData);
    }

}
