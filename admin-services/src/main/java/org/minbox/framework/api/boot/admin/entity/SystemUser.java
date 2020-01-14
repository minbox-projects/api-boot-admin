package org.minbox.framework.api.boot.admin.entity;

import com.gitee.hengboy.mybatis.enhance.common.annotation.Column;
import com.gitee.hengboy.mybatis.enhance.common.annotation.Id;
import com.gitee.hengboy.mybatis.enhance.common.annotation.Table;
import com.gitee.hengboy.mybatis.enhance.common.enums.KeyGeneratorTypeEnum;
import lombok.Data;

import java.io.Serializable;
import java.sql.Timestamp;

/**
 * 系统用户信息表
 * @author ApiBoot Mybatis Enhance Codegen
 */
@Data
@Table(name = "system_user")
public class SystemUser implements Serializable {

    /**
     * 用户编号
     */
    @Id(generatorType = KeyGeneratorTypeEnum.UUID)
    @Column(name = "su_user_id")
    private String userId;
    /**
     * 头像路径
     */
    @Column(name = "su_avatar")
    private String avatar;
    /**
     * 用户名
     */
    @Column(name = "su_username")
    private String username;
    /**
     * 密码密文
     */
    @Column(name = "su_password")
    private String password;
    /**
     * 邮箱地址
     */
    @Column(name = "su_email")
    private String email;
    /**
     * 年龄
     */
    @Column(name = "su_age")
    private Integer age;
    /**
     * 地址
     */
    @Column(name = "su_address")
    private String address;
    /**
     * 用户创建时间，默认当前时间
     */
    @Column(name = "su_create_time",insertable = false)
    private Timestamp createTime;
    /**
     * 用户状态，1：正常，0：冻结，-1：已删除
     */
    @Column(name = "su_status")
    private Integer status = 1;
    /**
     * 备注信息
     */
    @Column(name = "su_mark")
    private String mark;
}

