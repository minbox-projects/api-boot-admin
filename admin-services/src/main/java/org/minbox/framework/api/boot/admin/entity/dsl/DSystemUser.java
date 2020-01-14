package org.minbox.framework.api.boot.admin.entity.dsl;

import com.gitee.hengboy.mybatis.enhance.dsl.expression.ColumnExpression;
import com.gitee.hengboy.mybatis.enhance.dsl.expression.TableExpression;
import org.minbox.framework.api.boot.admin.entity.SystemUser;

/**
 * 系统用户信息表
 * @author ApiBoot Mybatis Enhance Codegen
 */
public class DSystemUser extends TableExpression<SystemUser> {

    public DSystemUser(String root) {
        super(root);
    }

    public static DSystemUser DSL() {
        return new DSystemUser("system_user");
    }

    /**
     * 用户编号
     */
    public ColumnExpression userId = new ColumnExpression("su_user_id", this);
    /**
     * 头像路径
     */
    public ColumnExpression avatar = new ColumnExpression("su_avatar", this);
    /**
     * 用户名
     */
    public ColumnExpression username = new ColumnExpression("su_username", this);
    /**
     * 密码密文
     */
    public ColumnExpression password = new ColumnExpression("su_password", this);
    /**
     * 邮箱地址
     */
    public ColumnExpression email = new ColumnExpression("su_email", this);
    /**
     * 年龄
     */
    public ColumnExpression age = new ColumnExpression("su_age", this);
    /**
     * 地址
     */
    public ColumnExpression address = new ColumnExpression("su_address", this);
    /**
     * 用户创建时间，默认当前时间
     */
    public ColumnExpression createTime = new ColumnExpression("su_create_time", this);
    /**
     * 用户状态，1：正常，0：冻结，-1：已删除
     */
    public ColumnExpression status = new ColumnExpression("su_status", this);
    /**
     * 备注信息
     */
    public ColumnExpression mark = new ColumnExpression("su_mark", this);
    @Override
    public ColumnExpression[] getColumns() {
        return new ColumnExpression[]{userId, avatar, username, password, email, age, address, createTime, status, mark};
    }

}

