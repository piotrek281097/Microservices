package registration.controller;

import io.micronaut.http.HttpResponse;
import io.micronaut.http.annotation.*;
import io.micronaut.security.annotation.Secured;
import io.micronaut.security.rules.SecurityRule;
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
    @Secured(SecurityRule.IS_ANONYMOUS)
    public HttpResponse register(@Body UserData userData) {
        userDataService.register(userData);

        return HttpResponse.ok(Collections.singletonMap("msg", "OK"));
    }

    @Get("/users")
    @Secured("ADMIN")
    public HttpResponse<List<UserData>> getUsers() {
        return HttpResponse.ok(userDataService.getAllUsers());
    }

    @Get("/users/{username}")
    @Secured(SecurityRule.IS_AUTHENTICATED)
    public HttpResponse<UserData> getUserByUsername(@PathVariable String username) {
        return HttpResponse.ok(userDataService.getUserByUsername(username));
    }

    @Delete("/users/delete/{userId}")
    @Secured("ADMIN")
    public HttpResponse<?> deleteUserById(@PathVariable long userId) {
        userDataService.deleteUserById(userId);
        return HttpResponse.ok(Collections.singletonMap("msg", "OK"));
    }

    @Post("/users/update")
    @Secured("USER")
    public HttpResponse updateUser(@Body UserData userData) {
        userDataService.updateUser(userData);
        return HttpResponse.ok(Collections.singletonMap("msg", "OK"));
    }

}
