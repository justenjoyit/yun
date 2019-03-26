package com.yanziting.dao;

import com.yanziting.model.DO.UserDO;

public interface UserDao {
    UserDO getUserByUsername(String username);

    UserDO getUserByEmail(String email);

    void insertUser(UserDO userDO);
}
