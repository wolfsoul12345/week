package com.dream.week.service;

import com.dream.week.mapper.UserMapper;
import com.dream.week.pojo.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class UserService {
    @Autowired
    private UserMapper userMapper;

    public List<User> getUser(){
        return userMapper.selectAll();
    }

    public void addUser(User user){
        userMapper.insert(user);
    }
}
