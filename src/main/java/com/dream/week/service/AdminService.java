package com.dream.week.service;

import com.dream.week.entity.PageResult;
import com.dream.week.mapper.RentInfoMapper;
import com.dream.week.mapper.UserMapper;
import com.dream.week.pojo.RentInfo;
import com.dream.week.pojo.User;
import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.util.StringUtils;
import tk.mybatis.mapper.entity.Example;

import java.util.List;
import java.util.Map;

@Service
public class AdminService {


    @Autowired
    private RentInfoMapper rentInfoMapper;
    @Autowired
    private UserMapper userMapper;

    public PageResult<RentInfo> find_info(Map<String,Object> map){
        PageHelper.startPage(1,10);
        String userName = (String) map.get("userName");
        String startDate = (String) map.get("startDate");
        if (StringUtils.isEmpty(startDate)) startDate ="1970-01-01";
        String endDate = (String) map.get("endDate");
        if (StringUtils.isEmpty(endDate)) endDate ="2100-01-01";

        Example example = new Example(RentInfo.class);
        example.createCriteria().andLike("user","%"+userName+"%").andBetween("date",startDate,endDate);
        List<RentInfo> rentInfos = rentInfoMapper.selectByExample(example);
        PageInfo<RentInfo> rentInfoPageInfo = new PageInfo<>(rentInfos);
        return new PageResult<>(rentInfoPageInfo.getTotal(),rentInfoPageInfo.getList());
    }

    public void check(Integer status, String userName) {
        if (status == 1 ){
            User user = new User();
            user.setUserName(userName);
            user.setStatus(1);
            userMapper.updateByPrimaryKeySelective(user);
        }else {
            userMapper.deleteByPrimaryKey(userName);
        }
    }
}
