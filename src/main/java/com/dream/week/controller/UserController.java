package com.dream.week.controller;

import com.dream.week.entity.PageResult;
import com.dream.week.pojo.RentInfo;
import com.dream.week.pojo.User;
import com.dream.week.service.RentInfoService;
import com.dream.week.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Controller
public class UserController {

    @Autowired
    private UserService userService;
    @Autowired
    private RentInfoService rentinfoService;

    //登录
    @PostMapping("/login")
    public String login(User user,Model model){
        int status = userService.login(user);
        if (status == 0 ) {
            model.addAttribute("msg","用户名或密码错误");
            return "index";
        }else if (status == 1 ){
            PageResult<RentInfo> rentInfo = rentinfoService.getRentInfo();
            model.addAttribute("data",rentInfo.getItems());
            model.addAttribute("this_page",1);
            model.addAttribute("total_page",rentInfo.getTotalData()/10+rentInfo.getTotalData()/10==0?0:1);
            model.addAttribute("user",user.getUserName());
            return "admin/main";
        }else if (status == 3 ) {
            model.addAttribute("msg","账号未审核，请联系管理员");
            return "index";
        }else {
            PageResult<RentInfo> rentInfo = rentinfoService.getRentInfo();
            model.addAttribute("data",rentInfo.getItems());
            model.addAttribute("this_page",1);
            model.addAttribute("total_page",rentInfo.getTotalData()/10+rentInfo.getTotalData()/10==0?0:1);
            model.addAttribute("user",user.getUserName());
            return "user/main";
        }
    }
    //注册
    @PostMapping("/register")
    public String  getUser( User user ){
        int status = userService.register(user);
        if (status == -1) {
            return "register";
        }
        return "index";
    }

    //判断用户名是否存在
    @GetMapping("user_exist")
    @ResponseBody
    public Map<String,Object> userExist( @RequestParam Map<String,Object> map){
        String userName = (String) map.get("userName");
        String s = userService.userExist(userName);
        HashMap<String, Object> map1 = new HashMap<>();
        map1.put("msg",s);
        return map1;
    }
    //修改密码
    @PostMapping("change_password")
    public String change_password(Model model , User user){
        userService.changePassword(user);
        model.addAttribute("userName",user.getUserName());
        model.addAttribute("msg","修改成功");
        return "user/my_info";
    }

}
