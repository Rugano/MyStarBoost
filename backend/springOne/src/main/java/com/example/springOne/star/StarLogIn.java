package com.example.springOne.star;

import org.springframework.data.mongodb.core.mapping.Document;

@Document
public class StarLogIn {
    private String username;
    private String hashedPassword;

    public StarLogIn(String username, String hashedPassword){
        this.username = username;
        this.hashedPassword = hashedPassword;
    }

    public String getHashedPassword() {
        return hashedPassword;
    }

    public void setHashedPassword(String hashedPassword) {
        this.hashedPassword = hashedPassword;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }
}
