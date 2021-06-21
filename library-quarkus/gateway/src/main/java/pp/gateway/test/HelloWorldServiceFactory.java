package pp.gateway.test;

import javax.inject.Singleton;

@Singleton
public class HelloWorldServiceFactory {

    @Singleton
    HelloWorldService testService() {
        return new HelloWorldService();
    }
}
