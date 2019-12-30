package com.dream.week.service;

import com.dream.week.mapper.UserMapper;
import com.dream.week.pojo.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.RequestParam;
import tk.mybatis.mapper.entity.Example;

import java.util.List;

@Service
public class UserService {
    @Autowired
    private UserMapper userMapper;

    public List<User> getUser(){
        return userMapper.selectAll();
    }


    public int login(@RequestParam User user) {
        Example example = new Example(User.class);
        example.createCriteria().andEqualTo("userName",user.getUserName()).andEqualTo("password",user.getPassword());
        List<User> users = userMapper.selectByExample(example);
        if (users.size() < 1 ) return 0;
        else if (users.get(0).getRole() == 0 ) return 1;    //管理员
        else if (users.get(0).getRole() == 1 ) return  2 ; //用户
        else {  //中介
            if (users.get(0).getStatus() ==  0 ) return  3 ; //中介账号未审核
            else return 4 ;//中介机构登录
        }
    }

    public int register(User user) {
        Example example = new Example(User.class);
        example.createCriteria().andEqualTo("userName",user.getUserName());
        List<User> users = userMapper.selectByExample(example);
        if (users.size()>0) return -1;
        else {
            if (user.getRole() == 2 ) user.setStatus(0);
        }
        userMapper.insert(user);
        return  1;
    }

    public String userExist(String userName) {
        Example example = new Example(User.class);
        example.createCriteria().andEqualTo("userName",userName);
        List<User> users = userMapper.selectByExample(example);
        if (users.size() > 0 ) return "用户名已存在";
        else return "用户名可用";
    }

    public void changePassword(User user) {
        userMapper.updateByPrimaryKeySelective(user);
    }

    public List<User> getNoCheckUser() {
        Example example = new Example(User.class);
        example.createCriteria().andEqualTo("role",2).andEqualTo("status",0);
        return userMapper.selectByExample(example);
    }


    public List<User> getUserInfo() {
        Example example = new Example(User.class);
        example.createCriteria().andNotEqualTo("role",0);
        return userMapper.selectByExample(example);

    }
}
