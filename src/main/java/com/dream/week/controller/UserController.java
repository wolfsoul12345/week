package com.dream.week.controller;

import com.dream.week.pojo.User;
import com.dream.week.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;

import java.util.List;

@Controller
public class UserController {

    @Autowired
    private UserService userService;

    //添加用户信息
    @PostMapping("/user_add")
    public String addUser(User user){
        userService.addUser(user);
        return "";
    }

    //获取用户信息
    @GetMapping("/user_get")
    public String  getUser(Model model){
        List<User> users = userService.getUser();
        model.addAttribute("user",users);
        return "users";

    }
}
