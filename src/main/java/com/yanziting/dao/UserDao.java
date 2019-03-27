package com.yanziting.dao;

import com.yanziting.model.DO.UserDO;
import org.apache.ibatis.annotations.Param;

public interface UserDao {
    UserDO getUserByUsername(String username);

    UserDO getUserByEmail(String email);

    void insertUser(UserDO userDO);

    UserDO getUserByUsernameOrEmailAndPwd(@Param("usernameOrEmail") String usernameOrEmail, @Param("pwd") String pwd);
}
