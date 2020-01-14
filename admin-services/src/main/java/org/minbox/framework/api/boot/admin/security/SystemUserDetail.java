package org.minbox.framework.api.boot.admin.security;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.minbox.framework.api.boot.admin.common.enums.Status;
import org.minbox.framework.api.boot.admin.entity.SystemUser;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import java.util.ArrayList;
import java.util.Collection;

/**
 * Spring Security登录所需要的用户详情实体
 *
 * @author 恒宇少年
 * @see SystemUserStoreDelegate#loadUserByUsername(String)
 * @see UserDetails
 */
@Data
@AllArgsConstructor
@NoArgsConstructor
public class SystemUserDetail implements UserDetails {
    private SystemUser systemUser;

    @Override
    public Collection<? extends GrantedAuthority> getAuthorities() {
        return new ArrayList();
    }

    @Override
    public String getPassword() {
        return systemUser.getPassword();
    }

    @Override
    public String getUsername() {
        return systemUser.getUsername();
    }

    @Override
    public boolean isAccountNonExpired() {
        return Status.ENABLE.getValue() == systemUser.getStatus().intValue();
    }

    @Override
    public boolean isAccountNonLocked() {
        return Status.ENABLE.getValue() == systemUser.getStatus().intValue();
    }

    @Override
    public boolean isCredentialsNonExpired() {
        return Status.ENABLE.getValue() == systemUser.getStatus().intValue();
    }

    @Override
    public boolean isEnabled() {
        return Status.ENABLE.getValue() == systemUser.getStatus().intValue();
    }
}
