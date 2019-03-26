package com.yanziting.dao;

import com.yanziting.model.DO.HelloDO;

import java.util.List;

public interface HelloDao {

    List<HelloDO> getAllHello();

    void createHello(HelloDO helloDO);
}
