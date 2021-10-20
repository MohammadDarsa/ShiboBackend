package com.darsa.shibo.model;

import com.fasterxml.jackson.annotation.JsonFilter;
import org.springframework.stereotype.Repository;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.validation.constraints.Email;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;
import java.util.UUID;

@Entity
@JsonFilter("UserFilter")
public class User {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private UUID userID;

    @NotNull
    @NotEmpty
    private String userName;
    @NotNull
    @NotEmpty
    private String password;
    @Email
    private String email;
    private boolean isLoggedIn;

    public User() {
    }

    public User(UUID userID, String userName, String password, String email, boolean isLoggedIn) {
        this.userID = userID;
        this.userName = userName;
        this.password = password;
        this.email = email;
        this.isLoggedIn = isLoggedIn;
    }

    public User(String userName, String password, String email, boolean isLoggedIn) {
        this.userName = userName;
        this.password = password;
        this.email = email;
        this.isLoggedIn = isLoggedIn;
    }


    public UUID getUserID() {
        return userID;
    }

    public void setUserID(UUID userID) {
        this.userID = userID;
    }

    public String getUserName() {
        return userName;
    }

    public void setUserName(String userName) {
        this.userName = userName;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getEmail() {
        return email;
    }

    public boolean getIsLoggedIn() {
        return isLoggedIn;
    }

    public void setIsLoggedIn(boolean loggedIn) {
        isLoggedIn = loggedIn;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public boolean checkPassword(String password) {
        return this.password.equals(password);
    }

}
