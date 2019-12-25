package com.dream.week.controller;

import com.dream.week.pojo.RentInfo;
import com.dream.week.service.RentinfoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;

import java.util.List;

@Controller
public class RentInfoController {

    @Autowired
    private RentinfoService rentinfoService ;

    //添加信息
    @PostMapping("/rent_info_add")
    public String add(RentInfo rentInfo){
        rentinfoService.addRentInfo(rentInfo);
        return "";
    }

    //获取信息
    @GetMapping("/rent_info_get")
    public String getRentInfo(Model model){
        List<RentInfo> rentInfos = rentinfoService.getRentInfo();
        model.addAttribute("rentinfos",rentInfos);
        return "rentinfos";
    }
}
