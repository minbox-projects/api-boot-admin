package org.minbox.framework.api.boot.admin.api.base;

import org.minbox.framework.api.boot.admin.common.enums.ResponseCode;
import org.minbox.framework.api.boot.admin.common.exception.LogicException;
import org.minbox.framework.api.boot.admin.entity.SystemUser;
import org.minbox.framework.logging.client.global.GlobalLogging;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.AnonymousAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;

/**
 * Api接口基础类
 *
 * @author 恒宇少年
 */
public class BaseApi {
    /**
     * MinBox Logging 提供的全局日志
     * 相关文档：http://www.minbox.io/logging/config-client.html#_15-%E5%85%A8%E5%B1%80%E6%97%A5%E5%BF%97
     */
    @Autowired
    protected GlobalLogging globalLogging;

    /**
     * 获取当前登录用户的登录名
     * {@link SecurityContextHolder#getContext()}
     *
     * @return {@link SystemUser#getUsername()}
     * @throws LogicException
     */
    protected String getCurrentUserName() throws LogicException {
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        if (!(authentication instanceof AnonymousAuthenticationToken)) {
            String currentUserName = authentication.getName();
            return currentUserName;
        }
        throw new LogicException(ResponseCode.NO_USER_LOGIN);
    }
}
