package com.dream.week.controller;

import com.dream.week.entity.PageResult;
import com.dream.week.pojo.RentInfo;
import com.dream.week.pojo.User;
import com.dream.week.service.RentInfoService;
import com.dream.week.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.List;
import java.util.Map;

@Controller
public class CommonController {

    //自动注入
    @Autowired
    private RentInfoService rentinfoService;

    //自动注入
    @Autowired
    private UserService userService;

    //请求路径
    @RequestMapping({"/","/index.html"})    //跳转到首页
    public String index(){
        return "index";
    }
    //跳转到注册页面
    @RequestMapping("to_register")
    public String to_register(){
        return "register";
    }


    //跳转到主页
    @RequestMapping("to_main")
    public String to_main(Model model  , @RequestParam Map<String,Object> map){
        String userName = (String) map.get("userName");
        model.addAttribute("userName",userName);
        //调用rentInfoService获取所有租房信息
        PageResult<RentInfo> rentInfo = rentinfoService.getRentInfo();
        //data存放数据信息
        model.addAttribute("data",rentInfo.getItems());
        //this_page存放当前页
        model.addAttribute("this_page",1);
        model.addAttribute("total_page",rentInfo.getTotalData()/10+(rentInfo.getTotalData()%10==0?0:1));
        model.addAttribute("user",userName);
        return "user/main";
    }

    //跳转到发布页面
    @RequestMapping("to_public")
    public String to_public(Model model , @RequestParam Map<String,Object> map){
        String userName = (String) map.get("userName");
        model.addAttribute("userName",userName);
        return "user/public";
}
    //去我的信息页面
    @RequestMapping("to_my_info")
    public String to_my_info(Model model , @RequestParam Map<String,Object> map){
        String userName = (String) map.get("userName");
        model.addAttribute("userName",userName);
        return "user/my_info";
    }

    //去我的发布页面
    @RequestMapping("to_my_public")
    public String to_my_public(Model model , @RequestParam Map<String,Object> map){
        String userName = (String) map.get("userName");
        model.addAttribute("userName",userName);
        List<RentInfo> myPublic = rentinfoService.getMyPublic(userName);
        model.addAttribute("data",myPublic);
        return "user/my_public";
    }
    //跳转到审核页面
    @RequestMapping("to_check")
    public String to_check(Model model ){
        List<User> noCheckUser = userService.getNoCheckUser();
        model.addAttribute("no_check_users",noCheckUser);
        return "admin/check";
    }
    //跳转到admin主页
    @RequestMapping("to_admin_main")
    public String to_admin_main(Model model ){
        PageResult<RentInfo> rentInfo = rentinfoService.getRentInfo();
        model.addAttribute("data",rentInfo.getItems());
        model.addAttribute("this_page",1);
        model.addAttribute("total_page",rentInfo.getTotalData()/10+(rentInfo.getTotalData()%10==0?0:1));
        return "admin/main";
    }
    @RequestMapping("to_user_info")
    public String to_user_info(Model model){
        model.addAttribute("data",userService.getUserInfo());
        return "admin/user_info";
    }

}
