package gateway.auth;

import io.micronaut.http.HttpResponse;
import io.micronaut.http.annotation.Body;

public interface AuthorizationOperations {

    HttpResponse register(@Body String userData);

    HttpResponse getAllUsers();
}
