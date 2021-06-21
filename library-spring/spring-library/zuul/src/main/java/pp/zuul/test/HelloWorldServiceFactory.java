package pp.zuul.test;


import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class HelloWorldServiceFactory {

    @Bean
    HelloWorldService testService() {
        return new HelloWorldService();
    }
}
