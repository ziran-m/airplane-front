package com.ideatest.demo.controller;

import com.ideatest.demo.annotation.UnInterception;
import com.ideatest.demo.pojo.JsonResult;
import com.ideatest.demo.pojo.User;
import com.ideatest.demo.service.impl.UserServiceImpl;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiParam;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

/**
 * @author ziran
 * @date 2020/6/9-12:40
 */
@RestController
/**
 * @RequestMapping处理请求地址映射,在类的级别上的注解会将一个特定请求或者请求模式映射到一个控制器之上
 * @PathVariable 注解主要是用来获取 url 参数
 * @RequestParam 是从 request 里面获取参数值
 * @RequestBody 注解用于接收前端传来的实体，接收参数也是对应的实体
 * */
@RequestMapping("/user")
@Api(value = "用户信息接口")
public class DemoController {

    @Autowired
    private UserServiceImpl userServiceImpl;

    @GetMapping("/getUser/{id}")
    @ApiOperation(value = "根据用户唯一标识获取用户信息")
    public JsonResult<User> getUserInfo(@PathVariable @ApiParam(value = "用户唯一标识") int id) {
        User user = userServiceImpl.getUser(id);
        return new JsonResult<>(user);
    }

    @GetMapping("/getUserByName/{name}")
    @ApiOperation(value = "根据用户名获取用户信息")
    public JsonResult<User> getUserByName(@PathVariable String name) {
        User user = userServiceImpl.getUserByName(name);
        return new JsonResult<>(user);
    }

    @GetMapping("/getAll")
    @ApiOperation(value = "获取所有用户信息")
    public JsonResult<List<User>> getAll() {
        List<User> list = userServiceImpl.getAll();
        return new JsonResult<>(list);
    }

    @ApiOperation(value = "测试jackson对null的处理")
    @GetMapping("/nullUser")
    @UnInterception
    public User getNullUser() {
        User user = new User(1, "ziran", null);
        return user;
    }
}
