package com.dream.week.pojo;

import lombok.Data;

import javax.persistence.Id;
import javax.persistence.Table;

@Data
@Table(name = "t_user")
public class User {
    @Id
    private String userName;
    private String password;
    public String email;
    public String phone;
    private Integer role;
    private Integer status;
}
