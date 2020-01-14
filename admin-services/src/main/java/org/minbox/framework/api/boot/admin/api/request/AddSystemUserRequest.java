package org.minbox.framework.api.boot.admin.api.request;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import io.swagger.annotations.ApiOperation;
import lombok.Data;
import org.hibernate.validator.constraints.Length;
import org.minbox.framework.api.boot.admin.entity.SystemUser;

import javax.validation.constraints.Min;
import javax.validation.constraints.NotEmpty;

/**
 * 添加系统用户请求对象
 * 用于接收前端传递的参数
 *
 * @author 恒宇少年
 */
@Data
@ApiModel
public class AddSystemUserRequest {
    /**
     * {@link SystemUser#getUsername()}
     */
    @NotEmpty
    @Length(min = 2, max = 20)
    @ApiModelProperty("登录名")
    private String username;
    /**
     * {@link SystemUser#getPassword()}
     */
    @NotEmpty
    @Length(min = 6)
    @ApiModelProperty("登录密码")
    private String password;
    /**
     * {@link SystemUser#getEmail()}
     */
    @ApiModelProperty("邮箱地址")
    private String email;
    /**
     * {@link SystemUser#getAge()}
     */
    @Min(1)
    @ApiModelProperty("年龄")
    private Integer age;
    /**
     * {@link SystemUser#getAddress()}
     */
    @Length(max = 200)
    @ApiModelProperty("家庭地址")
    private String address;
    /**
     * {@link SystemUser#getMark()}
     */
    @Length(max = 200)
    @ApiModelProperty("备注信息")
    private String mark;
}
