package com.dream.week.pojo;

import com.fasterxml.jackson.annotation.JsonFormat;
import lombok.Data;
import org.springframework.web.multipart.MultipartFile;

import javax.persistence.Id;
import javax.persistence.Table;
import javax.persistence.Transient;
import java.io.File;
import java.util.Date;

@Data
@Table(name = "t_rent_info")
public class RentInfo {

    @Id
    private Integer id;
    private String title;
    private String content;
    private String type;
    private String user;
    @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss",timezone = "UTC+8")
    private Date date;
    private Integer cost;
    private String phone;
    private String address;
    private String img;
    private Float area;


}
