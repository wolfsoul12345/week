package com.dream.week.service;

import com.dream.week.entity.PageResult;
import com.dream.week.mapper.RentInfoMapper;
import com.dream.week.pojo.RentInfo;
import com.dream.week.utils.UpLoadUtils;

import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;
import tk.mybatis.mapper.entity.Example;

import java.io.IOException;
import java.util.List;

@Service
public class RentInfoService {
    @Autowired
    private RentInfoMapper rentInfoMapper;

    public PageResult<RentInfo> getRentInfo(){
        PageHelper.startPage(1,10);
        List<RentInfo> rentInfos = rentInfoMapper.selectAll();
        PageInfo<RentInfo> pageInfo = new PageInfo<>(rentInfos);
        return new PageResult<>(pageInfo.getTotal(),pageInfo.getList());
    }

    public void addRentInfo(RentInfo rentInfo ,MultipartFile file){
        rentInfo.setImg(file.getOriginalFilename());
        try {
            UpLoadUtils.upload( file,rentInfo.getImg(),"C:/img");
        } catch (IOException e) {
            e.printStackTrace();
        }
        rentInfoMapper.insert(rentInfo);
    }

    public RentInfo getDetail(Integer id) {
        return rentInfoMapper.selectByPrimaryKey(id);
    }

    public List<RentInfo> getMyPublic(String userName) {
        Example example = new Example(RentInfo.class);
        example.createCriteria().andEqualTo("user",userName);
        return rentInfoMapper.selectByExample(example);
    }

    public void delete(Integer id) {
        rentInfoMapper.deleteByPrimaryKey(id);
    }

    public PageResult<RentInfo> getPageInfo(Integer page) {
        PageHelper.startPage(page,10);
        PageInfo<RentInfo> pageInfo = new PageInfo<>(rentInfoMapper.selectAll());
        return new PageResult<>(pageInfo.getTotal(),pageInfo.getList());
    }
}
