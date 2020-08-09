package org.minbox.framework.api.boot.admin.security;

import com.fasterxml.jackson.core.JsonGenerator;
import org.minbox.framework.oauth.exception.OAuth2Exception;
import org.minbox.framework.oauth.response.AuthorizationDeniedResponse;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Component;

/**
 * 自定义认证失败时的响应格式
 * 使用场景：当用户不存在、密码错误
 *
 * @author 恒宇少年
 */
@Component
public class CustomAuthorizationDeniedResponse implements AuthorizationDeniedResponse {
    @Override
    public void serializeResponse(OAuth2Exception e, JsonGenerator generator) {
        try {
            generator.writeObjectField("code", e.getHttpErrorCode());
            generator.writeObjectField("message", e.getMessage());
        } catch (Exception ex) {
            ex.printStackTrace();
        }
    }
}
