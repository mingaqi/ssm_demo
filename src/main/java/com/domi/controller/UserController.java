package com.domi.controller;

import com.domi.model.User;
import com.domi.service.UserService;
import com.domi.common.utils.SqlUtil;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

@Controller
public class UserController {

    private final Logger logger = LoggerFactory.getLogger(this.getClass());

    @Autowired
    private UserService userService;


    @RequestMapping(value = "/showAll")
    public ModelAndView showUser(){
        logger.debug("查询用户");
        ModelAndView modelAndView = new ModelAndView();
        modelAndView.addObject("userList",userService.getAll());
        modelAndView.addObject("randomName",this.getName());
        modelAndView.setViewName("index");
        return modelAndView;
    }

    @RequestMapping("/add")
    public String login(User user) {
        user.setId(SqlUtil.getUUIDByPk());
        logger.debug("1 rows will be insert:"+user.toString());
        userService.insertOne(user);
        return "showAll";
    }



//    随机生成name
    public String getName() {
        String result = "";
        for (int i = 0; i < 4; i++) {
            int intValue = (int) (Math.random() * 26 + 97);
            result = result + (char) intValue;
        }
        return result;
    }
}
