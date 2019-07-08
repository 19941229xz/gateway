package com.example.gateway.dao;

import com.example.gateway.common.HttpResp;
import com.example.gateway.model.User;
import com.netflix.ribbon.proxy.annotation.Http;
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
    public HttpResp getUserByUserId(int userId) {
        return HttpResp.fail();
    }


    @Override
    public User userLogin(User user) {
        return new User();
    }
}
