package com.dream.week.pojo;

import lombok.Data;

import javax.persistence.Id;
import java.util.Date;

@Data
public class RentInfo {

    @Id
    private int id;
    private String title;
    private String content;
    private String type;
    private String user;
    private Date date;

}
