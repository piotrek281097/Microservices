package registration.controller;

import io.micronaut.http.HttpResponse;
import io.micronaut.http.annotation.Body;
import io.micronaut.http.annotation.Controller;
import io.micronaut.http.annotation.Get;
import io.micronaut.http.annotation.Post;
import registration.domain.UserData;
import registration.service.UserDataService;

import java.util.Collections;
import java.util.List;

@Controller
public class UserDataController {

    protected final UserDataService userDataService;

    public UserDataController(UserDataService userDataService) {
        this.userDataService = userDataService;
    }

    @Post("/register")
    public HttpResponse register(@Body UserData userData) {
        userDataService.register(userData);

        return HttpResponse.ok(Collections.singletonMap("msg", "OK"));
    }

    @Get("/users")
    public HttpResponse<List<UserData>> getUsers() {
        return HttpResponse.ok(userDataService.getAllUsers());
    }

//    @Post("/test-admin")
//    public HttpResponse testAdmin() {
//        System.out.println("TEST____________");
//        return HttpResponse.ok(Collections.singletonMap("msg", "admin-test"));
//    }
//
//
//    @Post("/test-user")
//    public HttpResponse testUser() {
//        System.out.println("TEST____________");
//        return HttpResponse.ok(Collections.singletonMap("msg", "user-test"));
//    }
}
