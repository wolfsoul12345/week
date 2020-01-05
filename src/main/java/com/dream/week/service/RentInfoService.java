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
import java.util.Date;
import java.util.List;

@Service
public class RentInfoService {
    @Autowired
    private RentInfoMapper rentInfoMapper;

    //获取所有租房信息
    public void addRentInfo(RentInfo rentInfo ,MultipartFile file){
        rentInfo.setImg("image/"+file.getOriginalFilename());
        rentInfo.setDate(new Date());
        try {
            UpLoadUtils.upload( file,file.getOriginalFilename(),"C:/img");
        } catch (IOException e) {
            e.printStackTrace();
        }
        rentInfoMapper.insert(rentInfo);
    }

    public PageResult<RentInfo> getRentInfo(){
        //调用分页助手开启分页 第一页 10条
        PageHelper.startPage(1,10);
        //调用tkMyBatis的查询所有方法
        List<RentInfo> rentInfos = rentInfoMapper.selectAll();
        //new PageInfo 将查询出来的数据传到pageInfo
        PageInfo<RentInfo> pageInfo = new PageInfo<>(rentInfos);
        //返回PageResult 包含总条数和数据
        return new PageResult<>(pageInfo.getTotal(),pageInfo.getList());
    }

    public RentInfo getDetail(Integer id) {
        return rentInfoMapper.selectByPrimaryKey(id);
    }

    //获取我的发布
    public List<RentInfo> getMyPublic(String userName) {
        Example example = new Example(RentInfo.class);
        //建立查询条件
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
