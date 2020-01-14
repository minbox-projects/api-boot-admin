package org.minbox.framework.api.boot.admin.api.request;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;
import org.minbox.framework.api.boot.admin.common.model.PageableRequest;
import org.minbox.framework.api.boot.admin.entity.SystemUser;

/**
 * 根据查询条件分页查询系统用户列表
 *
 * @author 恒宇少年
 */
@Data
@ApiModel
public class SelectSystemUserByParamRequest extends PageableRequest {
    /**
     * 系统用户名
     * {@link SystemUser#getUsername()}
     */
    @ApiModelProperty("用户名")
    private String username;
    /**
     * 用户状态
     * {@link SystemUser#getStatus()}
     */
    @ApiModelProperty("用户状态状态，1：正常，0：冻结，-1：已删除")
    private String status;
}
