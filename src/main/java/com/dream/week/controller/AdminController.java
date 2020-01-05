package com.dream.week.controller;

import com.dream.week.entity.PageResult;
import com.dream.week.pojo.RentInfo;
import com.dream.week.pojo.User;
import com.dream.week.service.AdminService;
import com.dream.week.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.List;
import java.util.Map;

@Controller
public class AdminController {

    @Autowired
    private AdminService adminService;
    @Autowired
    private UserService userService;
//
    @PostMapping("find_info")
    public String find_info(@RequestParam Map<String,Object> map , Model model){
        PageResult<RentInfo> info = adminService.find_info(map);
        model.addAttribute("data",info.getItems());
        //this_page存放当前页
        model.addAttribute("this_page",1);
        model.addAttribute("total_page",info.getTotalData()/10+(info.getTotalData()%10==0?0:1));
        model.addAttribute("user",map.get("userName"));
        model.addAttribute("userName",map.get("userName"));
        return "admin/search_result";
    }

    @GetMapping("admin_check")
    public String admin_check(Model model ,@RequestParam Map<String,Object> map){
        Integer status = Integer.valueOf((String) map.get("status"));
        String userName = (String) map.get("userName");
        adminService.check(status,userName);
        List<User> noCheckUser = userService.getNoCheckUser();
        model.addAttribute("no_check_users",noCheckUser);
        return "admin/check";
    }
}
