package com.yanziting.service.impl;

import com.yanziting.dao.UserDao;
import com.yanziting.model.DO.UserDO;
import com.yanziting.model.DTO.UserRegisterRequestDTO;
import com.yanziting.service.UserService;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.lang.StringUtils;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;

@Slf4j
@Service
public class UserServiceImpl implements UserService {

    @Resource
    private UserDao userDao;
    /**
     * 用户注册
     */
    @Override
    public String register(UserRegisterRequestDTO userRegisterRequestDTO) {
        if (null == userRegisterRequestDTO){
            return "用户信息为空";
        }
        if (StringUtils.isEmpty(userRegisterRequestDTO.getUsername())){
            return "用户名不能为空";
        }
        if (StringUtils.isEmpty(userRegisterRequestDTO.getEmail())){
            return "邮箱不能为空";
        }
        if (StringUtils.isEmpty(userRegisterRequestDTO.getPwd())){
            return "密码不能为空";
        }

        //TODO 对用户名、邮箱、密码做限制
        UserDO sameUsernameUser = userDao.getUserByUsername(userRegisterRequestDTO.getUsername());
        if (null != sameUsernameUser){
            return "用户名重复";
        }
        UserDO sameEmailUser = userDao.getUserByEmail(userRegisterRequestDTO.getEmail());
        if (null != sameEmailUser){
            return "邮箱重复";
        }

        userDao.insertUser(UserDO.builder()
                .username(userRegisterRequestDTO.getUsername())
                .email(userRegisterRequestDTO.getEmail())
                .pwd(userRegisterRequestDTO.getPwd())
                .loginIp("")
                .status(0)
                .build()
        );

        return "Success";
    }
}
