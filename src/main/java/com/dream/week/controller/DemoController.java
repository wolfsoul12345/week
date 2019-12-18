package com.dream.week.controller;

import com.dream.week.pojo.Demo;
import com.dream.week.service.DemoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@Controller
public class DemoController {

    @Autowired
    private DemoService demoService;

    @GetMapping("/demo")
    public String getDemo(Model model){
        List<Demo> demos = demoService.getDemo();
        model.addAttribute("demo",demos);
        return "index";
    }
}