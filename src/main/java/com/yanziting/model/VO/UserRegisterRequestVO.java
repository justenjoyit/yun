package com.yanziting.model.VO;

import lombok.Data;

@Data
public class UserRegisterRequestVO {
    private String username;
    private String email;
    private String pwd;
}
