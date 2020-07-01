package com.ideatest.demo.service;

import com.ideatest.demo.pojo.User;

import java.util.List;

/**
 * @author ziran
 * @date 2020/6/11-10:44
 */
public interface UserService {
    User getUser(int id);

    List<User> getAll();

    User getUserByName(String name);

    User getUser(int id, String name);
}
