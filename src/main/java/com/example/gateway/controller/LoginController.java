package com.example.gateway.controller;


import com.example.gateway.common.HttpResp;
import com.example.gateway.common.JwtUtil;
import com.example.gateway.dao.UserDao;
import com.example.gateway.model.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class LoginController {

    @Autowired
    UserDao userDao;


    @RequestMapping("/login/user")
    public Object userLogin(@RequestBody User user){
        User u=userDao.userLogin(user);
        return u==null? HttpResp.fail():HttpResp.success(JwtUtil.sign(u.getUserName(),
                u.getPassword()));
    }

}
