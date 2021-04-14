package gateway.auth;


import io.micronaut.http.HttpResponse;
import io.micronaut.http.annotation.*;
import io.micronaut.http.client.annotation.Client;
import io.micronaut.retry.annotation.Recoverable;

import java.util.Collections;

@Client(id = "registration",
        errorType=Object.class)
@Recoverable(api = AuthorizationOperations.class)
public interface AuthorizationClient extends AuthorizationOperations {

    @Post("/register")
    HttpResponse register(@Body String userData);

    @Get("/users")
    HttpResponse getAllUsers();

//    @Get("/users/{userId}")
//    HttpResponse getUserById(@PathVariable long userId);

    @Get("/users/{username}")
    HttpResponse getUserByUsername(@PathVariable String username);

    @Delete("/users/delete/{userId}")
    HttpResponse deleteUserById(@PathVariable long userId);

    @Post("/users/update")
    HttpResponse updateUser(@Body String userData);

}
