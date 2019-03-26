package com.yanziting.model.DTO;

import lombok.Builder;
import lombok.Data;

@Data
@Builder
public class UserRegisterRequestDTO {
    private String username;
    private String email;
    private String pwd;
}
