package com.dream.week.service;

import com.dream.week.mapper.RentInfoMapper;
import com.dream.week.pojo.RentInfo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class RentinfoService {
    @Autowired
    private RentInfoMapper rentInfoMapper;

    public List<RentInfo> getRentInfo(){
        return rentInfoMapper.selectAll();
    }

    public void addRentInfo(RentInfo rentInfo){
         rentInfoMapper.insert(rentInfo);
    }

}
