package com.dream.week.service;

import com.dream.week.mapper.DemoMapper;
import com.dream.week.pojo.Demo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class DemoService {

    @Autowired
    private DemoMapper demoMapper;

    public List<Demo> getDemo() {
        return demoMapper.selectAll();
    }
}
