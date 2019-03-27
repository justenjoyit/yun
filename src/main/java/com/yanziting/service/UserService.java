package com.yanziting.service;

import com.yanziting.model.DTO.UserDTO;
import com.yanziting.model.DTO.UserLoginRequestDTO;
import com.yanziting.model.DTO.UserRegisterRequestDTO;

public interface UserService {
    String register(UserRegisterRequestDTO userRegisterRequestDTO);

    String login(UserLoginRequestDTO userLoginRequestDTO);

    UserDTO getUserByUsernameOrEmailAndPwd(String usernameOrEmail, String pwd);

}
