package com.ghulam.controller;

import com.ghulam.dto.UserDto;
import com.ghulam.model.User;
import com.ghulam.response.Response;
import com.ghulam.service.impl.UserService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;

/**
 * ------ expected user end-points ------
 * add a new user
 * get an existing user
 * update user data
 * delete specified user
 * list all user
 */

@RestController
@RequestMapping("/api")
public class UserController {
    final UserService userService;
    private User user;
    private UserDto userDto;

    public UserController(UserService userService) {
        this.userService = userService;
        user = new User();
        userDto = new UserDto();
    }

    @PostMapping("/add_user")
    public ResponseEntity<Object> addUser(@RequestBody UserDto dto) {
        user = user.toUser(dto);

        try {
            user = userService.addUser(user);
            userDto = userDto.toUserDto(user);

            return Response.handler("A new user added successfully.", HttpStatus.OK, userDto);
        } catch (Exception e) {
            System.out.println("Could not add a new user.");
            e.printStackTrace();
        }
        return Response.handler("Could not add a new user.", HttpStatus.BAD_REQUEST, null);
    }

    @GetMapping("/get_user/{userId}")
    public ResponseEntity<Object> getUser(@PathVariable int userId) {
        try {
            user = userService.getUser(userId);
            userDto = userDto.toUserDto(user);
            return Response.handler("Getting specified user.", HttpStatus.OK, userDto);
        } catch (Exception e) {
            System.out.println("Can not find user by id: " + userId);
            e.printStackTrace();
        }
        return Response.handler("Can not find user.", HttpStatus.BAD_REQUEST, null);
    }

    @PutMapping("/update_user/{userId}")
    public ResponseEntity<Object> updateUser(@PathVariable int userId, @RequestBody UserDto dto) {
        user = user.toUser(dto);
        try {
            user = userService.updateUser(userId, user);
            userDto = userDto.toUserDto(user);
            return Response.handler("User data updated successfully.", HttpStatus.OK, userDto);
        } catch (Exception e) {
            System.out.println("Could not update user by id: " + userId);
            e.printStackTrace();
        }
        return Response.handler("Can\'t update user data.", HttpStatus.BAD_REQUEST, null);
    }

    @DeleteMapping("/delete_user/{userId}")
    public ResponseEntity<Object> deleteUser(@PathVariable int userId) {
        try {
            if(userService.deleteUser(userId)) {
                return Response.handler("User data deleted successfully.", HttpStatus.OK, null);
            }
        } catch (Exception e) {
            System.out.println("Could not delete user by id: " + userId);
            e.printStackTrace();
        }
        return Response.handler("Can\'t delete user data.", HttpStatus.BAD_REQUEST, null);
    }

    @GetMapping("/all_users")
    public ResponseEntity<Object> listAllUser() {
        try {
            List<User> users = userService.allUsers();
            List<UserDto> allUsers = new ArrayList<>();
            for(User u:users) {
                UserDto dto = userDto.toUserDto(u);
                // System.out.println(dto.toString());
                /**
                 * here is some issue with all users
                 */

                allUsers.add(dto);
            }

            return Response.handler("Getting all users data.", HttpStatus.OK, allUsers);
        } catch (Exception e) {
            System.out.println("Can not get all users");
            e.printStackTrace();
        }
        return Response.handler("Can\'t get users data.", HttpStatus.BAD_REQUEST, null);
    }
}
