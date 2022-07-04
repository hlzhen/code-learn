package com.waner.entity;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

/**
 * 菜单实体
 * Created by Ale on 2022/7/1
 */
@ApiModel(value = "菜单实体", description = "菜单实体")
@Data
public class Menu {

    @ApiModelProperty(value = "菜单主键ID")
    private int id;

    @ApiModelProperty(value = "菜单名称")
    private String name;
}
