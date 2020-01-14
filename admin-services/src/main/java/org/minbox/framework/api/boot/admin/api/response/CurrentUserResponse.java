package org.minbox.framework.api.boot.admin.api.response;

import lombok.Data;
import org.minbox.framework.api.boot.admin.entity.SystemUser;

/**
 * 当前登录用户基本信息响应实体
 *
 * @author 恒宇少年
 */
@Data
public class CurrentUserResponse extends SystemUser {
    /**
     * 角色列表
     */
    private String[] roles = new String[]{"admin"};
}
