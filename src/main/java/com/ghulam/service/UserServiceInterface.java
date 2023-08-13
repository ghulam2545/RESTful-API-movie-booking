package com.ghulam.service;

import com.ghulam.model.User;
import java.util.List;

/**
 * ------ expected services ------
 * add a new user
 * get an existing user
 * update user data
 * delete specified user
 * list all user
 */
public interface UserServiceInterface {
    User addUser(User user);
    User getUser(int userId);
    User updateUser(int userId, User newUser);
    boolean deleteUser(int userId);
    List<User> allUsers();
}
