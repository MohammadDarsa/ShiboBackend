package com.darsa.shibo.model;

import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
public class UserDAO {
    static List<User> users = new ArrayList<>();

    static {
        users.add(new User("NidalJaafar", "ilovedarsa", "nidal@gmail.com"));
        users.add(new User("MhmdDarsa", "ilovenido", "darsa@gmail.com"));
        users.add(new User("AbbassKhriess", "ilovedarsa", "abbass@gmail.com"));
        users.add(new User("AliKanso<3", "ilovedarsa", "ali@gmail.com"));
    }

    public User getUserByUserName(String userName) {
        return users.stream().filter(temp -> temp.getUserName().equals(userName)).findFirst().orElse(null);
    }

    public User getUserByEmail(String email) {
        return users.stream().filter(temp -> temp.getEmail().equals(email)).findFirst().orElse(null);
    }

    public static List<User> getAllUsers() {
        return users;
    }
}
