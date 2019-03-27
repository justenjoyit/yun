package com.yanziting.model.DTO;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.Date;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class UserDTO {
    private String id;
    private String username;
    private String email;
    private Date registerTime;
    private Date lastLoginTime;
    private String loginIp;
    private Integer status;
}
