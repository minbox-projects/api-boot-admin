package org.minbox.framework.api.boot.admin.security;

import org.minbox.framework.api.boot.admin.entity.SystemUser;
import org.minbox.framework.api.boot.admin.security.SystemUserDetail;
import org.minbox.framework.api.boot.admin.mapper.SystemUserMapper;
import org.minbox.framework.api.boot.plugin.security.delegate.ApiBootStoreDelegate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Service;
import org.springframework.util.ObjectUtils;

/**
 * 系统用户业务逻辑
 * 实现ApiBoot提供的自定义查询Spring Security用户信息的接口{@link ApiBootStoreDelegate}
 *
 * @author 恒宇少年
 */
@Component
public class SystemUserStoreDelegate implements ApiBootStoreDelegate {
    /**
     * 系统用户数据接口
     */
    @Autowired
    private SystemUserMapper systemUserMapper;

    /**
     * 根据用户名查询用户基本信息
     *
     * @param username {@link SystemUser#getUsername()}
     * @return {@link SystemUserDetail}
     * @throws UsernameNotFoundException
     */
    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        SystemUser user = systemUserMapper.findByUsername(username);
        if (ObjectUtils.isEmpty(user)) {
            throw new UsernameNotFoundException("用户：" + username + "，不存在.");
        }
        return new SystemUserDetail(user);
    }
}
