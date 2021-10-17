package com.darsa.shibo.service;

import com.darsa.shibo.exception_handler.PasswordNotMatchException;
import com.darsa.shibo.exception_handler.UserNotFound;
import com.darsa.shibo.model.User;
import com.darsa.shibo.model.UserDAO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import javax.validation.Valid;
import java.util.List;

@RestController
public class LoginController {

    @Autowired
    UserDAO userService;

    @GetMapping(value = "/users")
    public List<User> getAllUsers() {
        return UserDAO.getAllUsers();
    }

    @PostMapping(value = "/login")
    public ResponseEntity<Object> login(@Valid @RequestBody User user) {
        User user1 = userService.getUserByUserName(user.getUserName());
        if(user1 == null) {
            //throw exception
            throw new UserNotFound("user not found");
        }

        if(!user.checkPassword(user1.getPassword())) {
            //throw exception
            throw new PasswordNotMatchException("password not match");
        }

        return new ResponseEntity<Object>(user1, HttpStatus.FOUND);
    }
}
