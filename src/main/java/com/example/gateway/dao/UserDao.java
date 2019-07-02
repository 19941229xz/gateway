package com.example.gateway.dao;


import com.example.gateway.model.User;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;

//
@FeignClient(value = "user-service",fallback = UserDaoFallback.class)
public interface UserDao {

    @GetMapping("/hello")
    String test();

    @GetMapping("/getUserByUsername")
    User getUserByUsername(String username);

    @PostMapping("/userLogin")
    User userLogin(@RequestBody User user);
}
