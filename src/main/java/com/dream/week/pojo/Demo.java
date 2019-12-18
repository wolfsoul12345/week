package com.dream.week.pojo;

import lombok.Data;

import javax.persistence.Id;

@Data
public class Demo {

    @Id
    private Integer id;
    private String name;
}
