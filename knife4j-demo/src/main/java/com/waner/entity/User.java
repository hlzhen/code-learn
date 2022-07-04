package com.waner.entity;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

/**
 * Created by Ale on 2022/7/2
 */
@ApiModel(description = "用户实体类")
@Data
public class User {
    @ApiModelProperty(value = "主键ID")
    private int id;

    @ApiModelProperty(value = "用户名称")
    private String name;

    @ApiModelProperty(value = "用户年龄")
    private int age;

    @ApiModelProperty(value = "用户地址")
    private String address;
}
