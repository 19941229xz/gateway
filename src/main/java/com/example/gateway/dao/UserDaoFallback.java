package com.example.gateway.dao;

import com.example.gateway.model.User;
import org.springframework.stereotype.Component;

@Component
public class UserDaoFallback implements UserDao {
    @Override
    public String test() {
        return "order service  visit  fail";
    }

    @Override
    public User getUserByUsername(String username) {
        return new User();
    }

    @Override
    public User userLogin(User user) {
        return new User();
    }
}
