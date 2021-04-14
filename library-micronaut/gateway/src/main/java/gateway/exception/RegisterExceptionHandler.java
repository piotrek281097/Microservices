package gateway.exception;

import io.micronaut.context.annotation.Requires;
import io.micronaut.http.HttpRequest;
import io.micronaut.http.HttpResponse;
import io.micronaut.http.HttpStatus;
import io.micronaut.http.annotation.Produces;
import io.micronaut.http.server.exceptions.ExceptionHandler;

import javax.inject.Singleton;

@Produces
@Singleton
@Requires(classes = {RegisterException.class, ExceptionHandler.class})
public class RegisterExceptionHandler implements ExceptionHandler<RegisterException, HttpResponse> {

    @Override
    public HttpResponse handle(HttpRequest request, RegisterException exception) {
        if (HttpStatus.CONFLICT.getReason().equals(exception.getMessage())) {
            return HttpResponse.status(HttpStatus.CONFLICT);
        } else if (HttpStatus.BAD_REQUEST.getReason().equals(exception.getMessage())) {
            return HttpResponse.status(HttpStatus.BAD_REQUEST);
        } else {
            return HttpResponse.status(HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }
}
