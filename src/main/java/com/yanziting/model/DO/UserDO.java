package com.yanziting.model.DO;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.Date;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class UserDO {
    private String id;
    private String username;
    private String email;
    private String pwd;
    private Date registerTime;
    private Date lastLoginTime;
    private String loginIp;
    private Integer status;
}
