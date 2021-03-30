package gateway.auth;


import io.micronaut.http.HttpResponse;
import io.micronaut.http.annotation.Body;
import io.micronaut.http.annotation.Get;
import io.micronaut.http.annotation.Post;
import io.micronaut.http.client.annotation.Client;
import io.micronaut.retry.annotation.Recoverable;

@Client("http://localhost:8080")
@Recoverable(api = AuthorizationOperations.class)
public interface AuthorizationClient extends AuthorizationOperations {

    @Post("/register")
    HttpResponse register(@Body String userData);

    @Get("/users")
    HttpResponse getAllUsers();
}
