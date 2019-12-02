package com.example.user.controller;

import com.example.user.model.User;
import com.example.user.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/user")
public class UserController {

    @Autowired
    private UserService userService;

    //public UserController(UserService userService) {
      // this.userService = userService;
    //}

    // https://howtodoinjava.com/spring-boot2/rest/rest-api-example/
    @PostMapping("/create") // It helps to configure path on method level
    public ResponseEntity<?> createUser(@RequestBody User user) {
       // User user1 = new User();
        User userServiceDb = userService.create(user); // calling create method of user service class
        return new ResponseEntity<>(userServiceDb, HttpStatus.CREATED);
        //return user;
    }

    @GetMapping("/all")
    public ResponseEntity<?> getAllUsers() {
        List<User> userList = userService.findAll();
        return new ResponseEntity<>(userList, HttpStatus.OK);
    }

    @PutMapping("/update")
    public ResponseEntity<?> updateUser(@RequestBody User user) {
        User userDb = userService.update(user);  //userDb local variable which is coming from update method
        return new ResponseEntity<>(userDb, HttpStatus.OK);
        //return new ResponseEntity<>(userService.update(user), HttpStatus.OK);
    }

    @DeleteMapping("/delete/{id}")
    public ResponseEntity<?> deleteUser(@PathVariable String id){
        boolean isDeleted = userService.isDelete(id);
        return new ResponseEntity<>(isDeleted, HttpStatus.OK);
        //return new ResponseEntity<>(userService.isDelete(id), HttpStatus.OK);
    }

}
