package com.yanziting.model.VO;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class UserLoginRequestVO {
    private String usernameOrEmail;
    private String pwd;
    private Boolean rememberMe;
}
