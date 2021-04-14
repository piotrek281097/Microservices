package registration.exception;

import io.micronaut.context.annotation.Requires;
import io.micronaut.http.HttpRequest;
import io.micronaut.http.HttpResponse;
import io.micronaut.http.HttpStatus;
import io.micronaut.http.annotation.Produces;
import io.micronaut.http.server.exceptions.ExceptionHandler;

import javax.inject.Singleton;

@Produces
@Singleton
@Requires(classes = {UsernameAlreadyExistsException.class, ExceptionHandler.class})
public class UserEmailAlreadyExistsExceptionHandler implements ExceptionHandler<UserEmailAlreadyExistsException, HttpResponse> {

    @Override
    public HttpResponse handle(HttpRequest request, UserEmailAlreadyExistsException exception) {
        return HttpResponse.status(HttpStatus.BAD_REQUEST);
    }
}
