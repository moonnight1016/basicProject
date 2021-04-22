package com.person.norma.basicservice.entity;

import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import com.baomidou.mybatisplus.extension.activerecord.Model;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.experimental.Accessors;

import java.io.Serializable;
import java.util.Date;

/**
 * @Author： norma
 * @Description： 用户信息表 表对应的类实体
 * @Date： 2021-04-22
 * @Modified By：
 */
@Data
@EqualsAndHashCode(callSuper = false)
@Accessors(chain = true)
@TableName(value = "sys_user")
public class SysUserEntity extends Model<SysUserEntity> {

    private static final long serialVersionUID = 1L;


    /**
     * 主键uuid
     */
    @TableId(value = "guid")
    @ApiModelProperty(value = "主键uuid")
    private String guid;

    /**
     * 账号
     */
    @TableField(value = "user_name")
    @ApiModelProperty(value = "账号")
    private String userName;

    /**
     * 姓名
     */
    @TableField(value = "name")
    @ApiModelProperty(value = "姓名")
    private String name;

    /**
     * 密码
     */
    @TableField(value = "password")
    @ApiModelProperty(value = "密码")
    private String password;

    /**
     * 状态
     */
    @TableField(value = "valid_code")
    @ApiModelProperty(value = "状态")
    private Integer validCode;

    /**
     * 创建时间
     */
    @TableField(value = "create_time")
    @ApiModelProperty(value = "创建时间")
    private Date createTime;

    /**
     * 更新时间
     */
    @TableField(value = "update_time")
    @ApiModelProperty(value = "更新时间")
    private Date updateTime;


    @Override
    protected Serializable pkVal() {
        return this.guid;
    }

}
