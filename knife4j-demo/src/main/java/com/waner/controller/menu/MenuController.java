package com.waner.controller.menu;

import com.waner.entity.Menu;
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
 * 菜单控制器
 * Created by Ale on 2022/7/1
 */
@RestController
@RequestMapping("/menu")
@Api(tags = "菜单控制器")
public class MenuController {


    @ApiOperation(value = "查询所有菜单", notes = "查询所有菜单信息")
    @RequestMapping(value = "/getmenus", method = RequestMethod.GET)
    public List<Menu> getAllMenus() {
        Menu menu = new Menu();
        menu.setId(10);
        menu.setName("首页");
        List<Menu> menus = new ArrayList<>();
        menus.add(menu);
        return menus;
    }

    @ApiImplicitParams({
            @ApiImplicitParam(name = "currentPage", value = "当前页", required = true, type = "Integer"),
            @ApiImplicitParam(name = "pageSize", value = "页大小", required = true, type = "Integer")
    })
    @ApiOperation(value = "分页查询菜单信息")
    @RequestMapping(value = "/page/{currentPage}/{pageSize}", method = RequestMethod.GET)
    public String findByPage(@PathVariable Integer currentPage, @PathVariable Integer pageSize) {
        return "ok";
    }


    @ApiOperation(value = "新增菜单", notes = "新增菜单信息")
    @RequestMapping(value = "/save", method = RequestMethod.POST)
    public String save(@RequestBody Menu menu) {
        return "ok";
    }

    @ApiOperation(value = "修改菜单", notes = "修改菜单信息")
    @RequestMapping(value = "/update", method = RequestMethod.POST)
    public String update(@RequestBody Menu menu) {
        return "oK";
    }

    @RequestMapping(value = "/delete", method = RequestMethod.DELETE)
    @ApiOperation(value = "删除菜单", notes = "删除菜单信息")
    public String delete(int id) {
        return "OK";
    }
}
