package com.example.securitypo4.controller;

import com.example.securitypo4.dto.UserListRequest;
import com.example.securitypo4.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api")
public class DemoController {

    @Autowired
    private UserService userService;

    @PostMapping("/addUser")
    public String addUser(@RequestBody UserListRequest request){
        userService.saveUser(request.getUsers());
        return "User added successfully";
    }
    @GetMapping("/public")
    public String publicMethod(){
        System.out.println("public method called");
        return "public method called";
    }
    @GetMapping("/admin")
    public String adminMethod(){
        System.out.println("admin method called");
        return "This is admin method";
    }
    @GetMapping
    public String userMethod(){
        System.out.println("User method called");
        return "This is user method";
    }
}
