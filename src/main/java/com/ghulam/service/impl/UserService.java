package com.ghulam.service.impl;

import com.ghulam.model.User;
import com.ghulam.repository.UserRepository;
import com.ghulam.service.UserServiceInterface;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.NoSuchElementException;

@Service
public class UserService implements UserServiceInterface {
    final UserRepository userRepo;

    public UserService(UserRepository userRepo) {
        this.userRepo = userRepo;
    }

    @Override
    public User addUser(User user) {
        try {
            return userRepo.save(user);
        } catch (Exception e) {
            System.out.println("Could not add a new user.");
            e.printStackTrace();
        }
        return null;
    }

    @Override
    public User getUser(int userId) {
        try {
           var userData =  userRepo.findById(userId);
           if(userData.isPresent()) {
               return userData.get();
           } else {
               throw new NoSuchElementException();
           }
        } catch (Exception e) {
            System.out.println("Can not find user by id: " + userId);
            e.printStackTrace();
        }
        return null;
    }

    @Override
    public User updateUser(int userId, User newUser) {
        try {
            var userData = userRepo.findById(userId);
            if(userData.isPresent()) {
                var user = userData.get();
                user.setFirstName(newUser.getFirstName());
                user.setLastName(newUser.getLastName());
                user.setGender(newUser.getGender());
                user.setContactNo(newUser.getContactNo());

               return userRepo.save(user);
            } else {
                throw new NoSuchElementException();
            }
        } catch (Exception e) {
            System.out.println("Could not update user by id: " + userId);
            e.printStackTrace();
        }
        return null;
    }

    @Override
    public boolean deleteUser(int userId) {
        try {
            userRepo.deleteById(userId);
            return true;
        } catch (Exception e) {
            System.out.println("Could not delete user by id: " + userId);
            e.printStackTrace();
        }
        return false;
    }

    @Override
    public List<User> allUsers() {
        try {
            return userRepo.findAll();
        } catch (Exception e) {
            System.out.println("Can not get all users");
            e.printStackTrace();
        }
        return null;
    }
}
