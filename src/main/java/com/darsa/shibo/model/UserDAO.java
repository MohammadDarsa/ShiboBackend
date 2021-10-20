package com.darsa.shibo.model;

import com.darsa.shibo.model.repositories.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.UUID;

@Service
public class UserDAO {

    @Autowired
    UserRepository userRepository;

    public User getUserByUserName(String userName) {
        return userRepository.findAll().stream().filter(user -> user.getUserName().equals(userName)).findFirst().orElse(null);
    }

    public User getUserByEmail(String email) {
        return userRepository.findAll().stream().filter(temp -> temp.getEmail().equals(email)).findFirst().orElse(null);
    }

    public List<User> getAllUsers() {
        return userRepository.findAll();
    }

    public User createUser(User user) {
        return userRepository.save(user);
    }

    public User userLogin(User user) {
        user.setIsLoggedIn(true);
       return userRepository.save(user);
    }
}
