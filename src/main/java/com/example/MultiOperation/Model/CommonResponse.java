package com.example.MultiOperation.Model;

import com.example.MultiOperation.Entity.Users;

import java.util.List;
import java.util.Optional;

public class CommonResponse {
    public String message;
    public String code;

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    public String getCode() {
        return code;
    }

    public void setCode(String code) {
        this.code = code;
    }

    public Optional<Users> getUsers() {
        return users;
    }

    public void setUsers(Optional<Users> users) {
        this.users = users;
    }

    public Optional<Users> users;
}
