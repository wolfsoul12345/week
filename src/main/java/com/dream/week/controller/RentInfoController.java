package com.dream.week.controller;

import com.dream.week.entity.PageResult;
import com.dream.week.pojo.RentInfo;
import com.dream.week.service.RentInfoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.multipart.MultipartFile;

import java.io.File;
import java.util.List;
import java.util.Map;

@Controller
public class RentInfoController {

    @Autowired
    private RentInfoService rentinfoService ;

    //发布租房信息
    @PostMapping("public")
    public String add(RentInfo rentInfo , MultipartFile file, Model model){
        rentinfoService.addRentInfo(rentInfo , file);
        PageResult<RentInfo> rentInfos = rentinfoService.getRentInfo();
        model.addAttribute("data",rentInfos.getItems());
        model.addAttribute("this_page",1);
        model.addAttribute("total_page",rentInfos.getTotalData()/10+rentInfos.getTotalData()/10==0?0:1);
        model.addAttribute("user",rentInfo.getUser());
        return "user/main";
    }

    //获取详细信息
    @GetMapping("/detail")
    public String getDetail(Model model , @RequestParam Map<String,Object> map){
        Integer id = Integer.valueOf(String.valueOf(map.get("id")));
        model.addAttribute("detail",rentinfoService.getDetail(id));
        return "user/detail";
    }

    @GetMapping("/delete")
    public String delete(Model model , @RequestParam Map<String,Object> map){
        String userName = (String) map.get("userName");
        model.addAttribute("userName",userName);
        Integer id = Integer.valueOf(String.valueOf(map.get("id")));
        rentinfoService.delete(id);
        List<RentInfo> myPublic = rentinfoService.getMyPublic(userName);
        model.addAttribute("data",myPublic);
        return "user/my_public";
    }

    @GetMapping("/admin_delete")
    public String admin_delete(Model model , @RequestParam Map<String,Object> map){
        Integer id = Integer.valueOf(String.valueOf(map.get("id")));
        rentinfoService.delete(id);
        PageResult<RentInfo> rentInfo = rentinfoService.getRentInfo();
        model.addAttribute("data",rentInfo.getItems());
        model.addAttribute("this_page",1);
        model.addAttribute("total_page",rentInfo.getTotalData()/10+rentInfo.getTotalData()/10==0?0:1);
        return "admin/main";
    }

    @GetMapping("page_info")
    public String page_info(@RequestParam Integer page , Model model){
        PageResult<RentInfo> pageInfo = rentinfoService.getPageInfo(page);
        model.addAttribute("data",pageInfo.getItems());
        model.addAttribute("this_page",page);
        model.addAttribute("total_page",pageInfo.getTotalData()/10+pageInfo.getTotalData()/10==0?0:1);
        return "/user/main";
    }
    @GetMapping("admin_page_info")
    public String admin_page_info(@RequestParam Integer page , Model model){
        PageResult<RentInfo> pageInfo = rentinfoService.getPageInfo(page);
        model.addAttribute("data",pageInfo.getItems());
        model.addAttribute("this_page",page);
        model.addAttribute("total_page",pageInfo.getTotalData()/10+pageInfo.getTotalData()/10==0?0:1);
        return "/admin/main";
    }

}
