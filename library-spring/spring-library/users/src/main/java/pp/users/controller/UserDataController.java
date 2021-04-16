package pp.users.controller;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import pp.users.domain.UserData;
import pp.users.service.UserDataService;

@CrossOrigin
@RestController
public class UserDataController {

    private UserDataService userDataService;

    public UserDataController(UserDataService userDataService) {
        this.userDataService = userDataService;
    }

    @GetMapping("/users-list")
    String getUsers() {
        return String.format("users hello!");
    }

    @PostMapping("/register")
    public ResponseEntity<UserData> register(@RequestBody UserData userData) {
        userDataService.register(userData);
        return new ResponseEntity<>(HttpStatus.OK);
    }
}


