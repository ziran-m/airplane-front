package com.ideatest.demo.service.impl;

import com.ideatest.demo.pojo.User;
import com.ideatest.demo.service.UserService;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

/**
 * @author ziran
 * @date 2020/6/11-10:56
 */
@Service("UserService")
public class UserServiceImpl implements UserService {
    @Override
    public User getUser(int id) {
        return new User(1,"ziran1","ziran1");
    }

    @Override
    public List<User> getAll() {
        List<User> list = new ArrayList<>();
        list.add(new User(1,"ziran1","ziran1"));
        list.add(new User(2,"ziran2","ziran2"));
        return list;
    }

    @Override
    public User getUserByName(String name) {
        return new User(1,"ziran1","ziran1");
    }

    @Override
    public User getUser(int id, String name) {
        return new User(1,"ziran1","ziran1");
    }
}
