package gateway.test;

import io.micronaut.context.annotation.Factory;

import javax.inject.Singleton;

@Factory
public class HelloWorldServiceFactory {

    @Singleton
    HelloWorldService testService() {
        return new HelloWorldService();
    }
}
