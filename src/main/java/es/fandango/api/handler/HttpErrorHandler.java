package es.fandango.api.handler;

import io.micronaut.context.annotation.Requires;
import io.micronaut.http.HttpRequest;
import io.micronaut.http.HttpResponse;
import io.micronaut.http.annotation.Produces;
import io.micronaut.http.server.exceptions.ExceptionHandler;

import javax.inject.Singleton;

/**
 * The Generic error handler
 */
@Produces
@Singleton
@Requires(classes = {Exception.class, ExceptionHandler.class})
public class HttpErrorHandler implements ExceptionHandler<Exception, HttpResponse<Object>> {

    @Override
    public HttpResponse<Object> handle(HttpRequest request, Exception exception) {
        return HttpResponse.notFound();
    }
}
