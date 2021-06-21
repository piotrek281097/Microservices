package pp.users.controller;

import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import pp.users.domain.UserData;
import pp.users.service.UserDataService;

import java.util.Collections;
import java.util.List;

@CrossOrigin
@RestController
public class UserDataController {

    private UserDataService userDataService;

    public UserDataController(UserDataService userDataService) {
        this.userDataService = userDataService;
    }

    @PostMapping("/users/register")
    public ResponseEntity<UserData> register(@RequestBody UserData userData) {
        userDataService.register(userData);
        return new ResponseEntity<>(HttpStatus.OK);
    }

    @GetMapping("/users/users")
    public ResponseEntity<List<UserData>> getUsers() {
        return new ResponseEntity<>(userDataService.getAllUsers(), HttpStatus.OK);
    }

    @GetMapping("/users/{username}")
    public ResponseEntity<UserData> getUserByUsername(@PathVariable String username) {
        return new ResponseEntity<>(userDataService.getUserByUsername(username), HttpStatus.OK);
    }

    @DeleteMapping("/users/delete/{userId}")
    public ResponseEntity<?> deleteUserById(@PathVariable long userId) {
        userDataService.deleteUserById(userId);
        return new ResponseEntity<>(Collections.singletonMap("msg", "OK"), HttpStatus.OK);
    }

    @PostMapping("/users/update")
    public ResponseEntity<?> updateUser(@RequestBody UserData userData) {
        userDataService.updateUser(userData);
        return new ResponseEntity<>(Collections.singletonMap("msg", "OK"), HttpStatus.OK);
    }

    @GetMapping(value = "/save-test-data", produces = MediaType.TEXT_PLAIN_VALUE)
    public String saveTestData() {
        return userDataService.saveTestData();
    }
}


