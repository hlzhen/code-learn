package com.waner.controller.user;

import com.waner.entity.User;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiImplicitParam;
import io.swagger.annotations.ApiImplicitParams;
import io.swagger.annotations.ApiOperation;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import java.util.ArrayList;
import java.util.List;

/**
 * 用户控制器
 * Created by Ale on 2022/7/1
 */
@RestController
@RequestMapping("/user")
@Api(tags = "用户控制器")
public class UserController {

    @ApiOperation(value = "查询所有用户", notes = "查询所有用户信息")
    @RequestMapping(value = "/getusers", method = RequestMethod.GET)
    public List<User> getAllUsers() {
        User user = new User();
        user.setId(10);
        user.setName("tom");
        user.setAge(20);
        user.setAddress("shanghai");
        List<User> users = new ArrayList<>();
        users.add(user);
        return users;
    }

    @ApiImplicitParams({
            @ApiImplicitParam(name = "currentPage", value = "当前页", required = true, type = "Integer"),
            @ApiImplicitParam(name = "pageSize", value = "页大小", required = true, type = "Integer")
    })
    @ApiOperation(value = "分页查询用户信息")
    @RequestMapping(value = "/page/{currentPage}/{pageSize}", method = RequestMethod.GET)
    public String findByPage(@PathVariable Integer currentPage, @PathVariable Integer pageSize) {
        return "ok";
    }


    @ApiOperation(value = "新增用户", notes = "新增用户信息")
    @RequestMapping(value = "/save", method = RequestMethod.POST)
    public String save(@RequestBody User user) {
        return "ok";
    }

    @ApiOperation(value = "修改用户", notes = "修改用户信息")
    @RequestMapping(value = "/update", method = RequestMethod.POST)
    public String update(@RequestBody User user) {
        return "oK";
    }

    @RequestMapping(value = "/delete", method = RequestMethod.DELETE)
    @ApiOperation(value = "删除用户", notes = "删除用户信息")
    public String delete(int id) {
        return "OK";
    }

}
