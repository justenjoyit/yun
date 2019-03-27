package com.yanziting.service.impl;

import com.yanziting.dao.UserDao;
import com.yanziting.model.DO.UserDO;
import com.yanziting.model.DTO.UserDTO;
import com.yanziting.model.DTO.UserLoginRequestDTO;
import com.yanziting.model.DTO.UserRegisterRequestDTO;
import com.yanziting.service.UserService;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.lang.StringUtils;
import org.apache.shiro.SecurityUtils;
import org.apache.shiro.authc.AuthenticationException;
import org.apache.shiro.authc.UsernamePasswordToken;
import org.apache.shiro.subject.Subject;
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
        if (null == userRegisterRequestDTO) {
            return "用户信息为空";
        }
        if (StringUtils.isEmpty(userRegisterRequestDTO.getUsername())) {
            return "用户名不能为空";
        }
        if (StringUtils.isEmpty(userRegisterRequestDTO.getEmail())) {
            return "邮箱不能为空";
        }
        if (StringUtils.isEmpty(userRegisterRequestDTO.getPwd())) {
            return "密码不能为空";
        }

        //TODO 对用户名、邮箱、密码做限制
        UserDO sameUsernameUser = userDao.getUserByUsername(userRegisterRequestDTO.getUsername());
        if (null != sameUsernameUser) {
            return "用户名重复";
        }
        UserDO sameEmailUser = userDao.getUserByEmail(userRegisterRequestDTO.getEmail());
        if (null != sameEmailUser) {
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

    /**
     * 用户登录
     *
     * @param userLoginRequestDTO
     * @return
     */
    @Override
    public String login(UserLoginRequestDTO userLoginRequestDTO) {
        if (null == userLoginRequestDTO) {
            return "用户不能为空";
        }
        if (StringUtils.isEmpty(userLoginRequestDTO.getUsernameOrEmail())) {
            return "用户名或邮箱不能为空";
        }
        if (StringUtils.isEmpty(userLoginRequestDTO.getPwd())) {
            return "密码不能为空";
        }
        //默认不记住
        if (null == userLoginRequestDTO.getRememberMe()) {
            userLoginRequestDTO.setRememberMe(false);
        }
        Subject currentUser = SecurityUtils.getSubject();
        UsernamePasswordToken usernamePasswordToken = new UsernamePasswordToken(userLoginRequestDTO.getUsernameOrEmail(), userLoginRequestDTO.getPwd());

        try {
            currentUser.login(usernamePasswordToken);
            return "登录成功";
        } catch (AuthenticationException e) {
            return "登录失败";
        }
    }

    /**
     * 通过用户名或邮箱以及密码获取用户
     *
     * @param usernameOrEmail
     * @param pwd
     * @return
     */
    @Override
    public UserDTO getUserByUsernameOrEmailAndPwd(String usernameOrEmail, String pwd) {
        if (StringUtils.isEmpty(usernameOrEmail) || StringUtils.isEmpty(pwd)) {
            return null;
        }
        UserDO userDO = userDao.getUserByUsernameOrEmailAndPwd(usernameOrEmail, pwd);
        if (null == userDO) {
            return null;
        }
        return UserDTO.builder()
                .username(userDO.getUsername())
                .email(userDO.getEmail())
                .id(userDO.getId())
                .lastLoginTime(userDO.getLastLoginTime())
                .loginIp(userDO.getLoginIp())
                .registerTime(userDO.getRegisterTime())
                .status(userDO.getStatus())
                .build();
    }
}
