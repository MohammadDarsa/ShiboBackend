package com.darsa.shibo.service;

import com.darsa.shibo.exception_handler.PasswordNotMatchException;
import com.darsa.shibo.exception_handler.UserNotFound;
import com.darsa.shibo.model.User;
import com.darsa.shibo.model.UserDAO;
import com.fasterxml.jackson.databind.ser.FilterProvider;
import com.fasterxml.jackson.databind.ser.impl.SimpleBeanPropertyFilter;
import com.fasterxml.jackson.databind.ser.impl.SimpleFilterProvider;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.http.converter.json.MappingJacksonValue;
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
    public ResponseEntity<Object> getAllUsers() {
        List<User> allUsers = userService.getAllUsers();

        SimpleBeanPropertyFilter filter = SimpleBeanPropertyFilter.filterOutAllExcept("userName", "email", "isLoggedIn", "userID");
        FilterProvider filterProvider = new SimpleFilterProvider().addFilter("UserFilter", filter);

        MappingJacksonValue mappingJacksonValue = new MappingJacksonValue(allUsers);
        mappingJacksonValue.setFilters(filterProvider);

        return new ResponseEntity<>(mappingJacksonValue, HttpStatus.FOUND);
    }

    @PostMapping(value = "/login")
    public ResponseEntity<Object> login(@Valid @RequestBody User user) {

        User user1 = userService.userLogin(userService.getUserByUserName(user.getUserName()));

        if(user1 == null) {
            //throw exception
            throw new UserNotFound("user not found");
        }

        if(!user.checkPassword(user1.getPassword())) {
            //throw exception
            throw new PasswordNotMatchException("password not match");
        }

        SimpleBeanPropertyFilter filter = SimpleBeanPropertyFilter.filterOutAllExcept("userName", "email", "isLoggedIn", "userID");
        FilterProvider filters = new SimpleFilterProvider().addFilter("UserFilter", filter);

        MappingJacksonValue mappingJacksonValue = new MappingJacksonValue(user1);
        mappingJacksonValue.setFilters(filters);

        return new ResponseEntity<>(mappingJacksonValue, HttpStatus.FOUND);
    }

    @PostMapping("/register")
    public ResponseEntity<Object> register(@Valid @RequestBody User user) {
        User created = userService.createUser(user);

        if(created == null) throw new UserNotFound("user couldn't be registered");

        FilterProvider filterProvider = new SimpleFilterProvider().addFilter("UserFilter", SimpleBeanPropertyFilter.filterOutAllExcept("userID", "email", "userName"));
        MappingJacksonValue mappingJacksonValue = new MappingJacksonValue(created);
        mappingJacksonValue.setFilters(filterProvider);

        return new ResponseEntity<>(mappingJacksonValue, HttpStatus.CREATED);
    }
}
