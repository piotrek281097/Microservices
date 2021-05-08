package pp.users.controller;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.annotation.Secured;
import org.springframework.web.bind.annotation.*;
import pp.users.domain.UserData;
import pp.users.service.UserDataService;

import java.net.http.HttpResponse;
import java.util.Collections;
import java.util.List;

@CrossOrigin
@RestController
public class UserDataController {

    private UserDataService userDataService;

    public UserDataController(UserDataService userDataService) {
        this.userDataService = userDataService;
    }

    @Secured("ROLE_USER")
//    @GetMapping("/users/users-list")
    String getUsersTest() {
        return String.format("users hello!");
    }

    @Secured("ROLE_ADMIN")
//    @GetMapping("/users/admin-list")
    String getAdminsTest() {
        return String.format("admins hello!");
    }

    //////////////
    @PostMapping("/users/register")
    public ResponseEntity<UserData> register(@RequestBody UserData userData) {
        userDataService.register(userData);
        return new ResponseEntity<>(HttpStatus.OK);
    }

    @GetMapping("/users/users")
//    @Secured("ROLE_ADMIN")
    public ResponseEntity<List<UserData>> getUsers() {
        return new ResponseEntity<>(userDataService.getAllUsers(), HttpStatus.OK);
    }

//    @Get("/users/{userId}")
//    public HttpResponse<UserData> getUserById(@PathVariable long userId) {
//        return HttpResponse.ok(userDataService.getUserById(userId));
//    }

    @GetMapping("/users/{username}")
//    @Secured({ "ROLE_USER", "ROLE_ADMIN" })
    public ResponseEntity<UserData> getUserByUsername(@PathVariable String username) {
        return new ResponseEntity<>(userDataService.getUserByUsername(username), HttpStatus.OK);
    }

    @DeleteMapping("/users/delete/{userId}")
//    @Secured("ROLE_ADMIN")
    public ResponseEntity<?> deleteUserById(@PathVariable long userId) {
        userDataService.deleteUserById(userId);
        return new ResponseEntity<>(Collections.singletonMap("msg", "OK"), HttpStatus.OK);
    }

    @PostMapping("/users/update")
//    @Secured("ROLE_USER")
    public ResponseEntity<?> updateUser(@RequestBody UserData userData) {
        userDataService.updateUser(userData);
        return new ResponseEntity<>(Collections.singletonMap("msg", "OK"), HttpStatus.OK);
    }
}


