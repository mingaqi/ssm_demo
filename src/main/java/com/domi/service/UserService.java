package com.domi.service;

import com.domi.mapper.UserMapper;
import com.domi.model.User;
import com.domi.common.utils.SqlUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

//spring管理的bean在默认情况下是会在服务器启动的时候初始化的。
//bean设置了scope为prototype（原型）之后，会每次使用时生产一个
//bean设置了lazy-init=”true”后，启动服务器不会马上实例化，而是在用到的时候被实例化。

@Service("userService")
@Scope("prototype") // scope=prototype 为多例, lazy延迟加载  prototype也是延迟
public class UserService {

    @Autowired
    private UserMapper userMapper;

    public Integer insertOne(User user) {
        return userMapper.insert(user);
    }

    public List<User> getAll() {
        return userMapper.selectAll();
    }

//    test for transaction
    @Transactional(rollbackFor = { Exception.class })
    public int iinsert2Rows() throws Exception{
        int count  = 0;
            User user = new User();
            user.setId(SqlUtil.getUUIDByPk());
            user.setName("test1");
            user.setPwd("4321");
            count = userMapper.insert(user);

            if (count>0) {
            throw new Exception("test for transaction");
            }

            user.setId(SqlUtil.getUUIDByPk());
            user.setName("test2");
            user.setPwd("4321");
            count++;
        return count;
    }
}
