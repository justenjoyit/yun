package com.yanziting.controller;

import com.yanziting.model.DTO.UserRegisterRequestDTO;
import com.yanziting.model.VO.UserRegisterRequestVO;
import com.yanziting.service.UserService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.Resource;

@RestController("/user")
public class UserController {
    private final static Logger logger = LoggerFactory.getLogger(HelloController.class);

    @Resource
    private UserService userService;

    /**
     * 用户注册
     * @param userRegisterRequestVO 注册信息，包括用户名、邮箱、密码
     * @return Success 成功、Fail 失败
     */
    @PostMapping("/user/register")
    public String register(@RequestBody UserRegisterRequestVO userRegisterRequestVO){
        logger.info("UserController-register: username:{} , email:{} , pwd:{}",userRegisterRequestVO.getUsername(),userRegisterRequestVO.getEmail(),userRegisterRequestVO.getPwd());
        //进行注册
        return userService.register(UserRegisterRequestDTO.builder()
                .username(userRegisterRequestVO.getUsername())
                .email(userRegisterRequestVO.getEmail())
                .pwd(userRegisterRequestVO.getPwd())
                .build());
    }
}
