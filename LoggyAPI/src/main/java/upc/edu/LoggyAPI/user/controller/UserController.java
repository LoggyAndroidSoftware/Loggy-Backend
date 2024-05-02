package upc.edu.LoggyAPI.user.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.*;
import upc.edu.LoggyAPI.user.dto.UserRequest;
import upc.edu.LoggyAPI.user.dto.UserResponse;
import upc.edu.LoggyAPI.user.dto.mapper.UserMapper;
import upc.edu.LoggyAPI.user.service.UserService;

import java.util.List;

@RestController
@CrossOrigin(origins = "*")
@RequestMapping("/api/v1")
public class UserController {
    @Autowired
    private UserService userService;

    @Transactional
    @GetMapping(value = "/users")
    public ResponseEntity<List<UserResponse>> getAllUsers(){
        var usersResponse = UserMapper.INSTANCE.userListToUserResponseList(userService.getAllUsers());
        return new ResponseEntity<List<UserResponse>>(usersResponse, HttpStatus.OK);
    }

    @Transactional
    @GetMapping(value = "/users/{id}")
    public ResponseEntity<UserResponse> getUserById(@PathVariable("id") Long id){
        var userResponse = UserMapper.INSTANCE.userToUserResponse(userService.getUserById(id));
        return new ResponseEntity<UserResponse>(userResponse, HttpStatus.OK);
    }

    @Transactional
    @GetMapping(value = "/users/filterByUsername")
    public ResponseEntity<Long> getUserByUsername(@RequestParam("username") String username){
        return new ResponseEntity<>(userService.getUserIdByUsername(username), HttpStatus.OK);
    }

    @Transactional
    @PostMapping(value = "/users")
    public ResponseEntity<UserResponse> createUser(@RequestBody UserRequest userRequest){
        var user = UserMapper.INSTANCE.userRequestToUser(userRequest);
        var userResponse = UserMapper.INSTANCE.userToUserResponse(userService.createUser(user));
        return new ResponseEntity<UserResponse>(userResponse, HttpStatus.CREATED);
    }


}
