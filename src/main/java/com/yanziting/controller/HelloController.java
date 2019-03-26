package com.yanziting.controller;

import com.yanziting.dao.HelloDao;
import com.yanziting.model.DO.HelloDO;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.Resource;
import java.util.List;

@RestController
public class HelloController {
    private final static Logger logger = LoggerFactory.getLogger(HelloController.class);

    @Resource
    private HelloDao helloDao;

    @GetMapping("/")
    public String index(){
        return "welcome to Yun";
    }

    @GetMapping("/hello")
    public List<HelloDO> getAllHello(){
        return helloDao.getAllHello();
    }

    @PostMapping("/hello")
    public void createHello(@RequestBody HelloDO helloDO){
        if (null != helloDO){
            logger.info("createHello:{}", helloDO.getKeyword());
            helloDao.createHello(helloDO);
        }
    }
}
