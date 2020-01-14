package org.minbox.framework.api.boot.admin.common.enums;

import lombok.Getter;

/**
 * 用户状态枚举定义
 *
 * @author 恒宇少年
 */
@Getter
public enum Status {
    /**
     * 启用
     */
    ENABLE(1),
    /**
     * 禁用
     */
    DISABLE(0),
    /**
     * 已删除
     */
    DELETE(-1);
    private Integer value;

    Status(Integer value) {
        this.value = value;
    }
}
