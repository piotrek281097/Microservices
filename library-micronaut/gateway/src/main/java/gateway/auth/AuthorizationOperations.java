package gateway.auth;

import io.micronaut.http.HttpResponse;
import io.micronaut.http.annotation.Body;
import io.micronaut.http.annotation.PathVariable;

public interface AuthorizationOperations {

    HttpResponse register(@Body String userData);

    HttpResponse getAllUsers();

    HttpResponse deleteUserById(@PathVariable long userId);

    HttpResponse getUserByUsername(@PathVariable String username);

    HttpResponse updateUser(@Body String userData);
}
