package registration.controller;

import io.micronaut.http.HttpResponse;
import io.micronaut.http.HttpStatus;
import io.micronaut.http.annotation.*;
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

//    @Get("/users/{userId}")
//    public HttpResponse<UserData> getUserById(@PathVariable long userId) {
//        return HttpResponse.ok(userDataService.getUserById(userId));
//    }

    @Get("/users/{username}")
    public HttpResponse<UserData> getUserByUsername(@PathVariable String username) {
        return HttpResponse.ok(userDataService.getUserByUsername(username));
    }

    @Delete("/users/delete/{userId}")
    public HttpResponse<?> deleteUserById(@PathVariable long userId) {
        userDataService.deleteUserById(userId);
        return HttpResponse.ok(Collections.singletonMap("msg", "OK"));
    }

    @Post("users/update")
    public HttpResponse updateUser(@Body UserData userData) {
        userDataService.updateUser(userData);
        return HttpResponse.ok(Collections.singletonMap("msg", "OK"));
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
