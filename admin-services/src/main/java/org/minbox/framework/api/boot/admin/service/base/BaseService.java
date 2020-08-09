package org.minbox.framework.api.boot.admin.service.base;

import com.gitee.hengboy.mybatis.enhance.dsl.factory.EnhanceDslFactory;
import com.gitee.hengboy.mybatis.enhance.mapper.EnhanceMapper;
import org.minbox.framework.logging.client.global.GlobalLogging;
import org.springframework.beans.factory.annotation.Autowired;

import java.io.Serializable;

/**
 * 业务逻辑基础类
 *
 * @author 恒宇少年
 */
public class BaseService<T extends Object, ID extends Serializable> {
    /**
     * MyBatis Enhance 动态查询工厂类
     * 相关文档：http://apiboot.minbox.io/zh-cn/docs/api-boot-mybatis-enhance.html
     *
     * @see com.gitee.hengboy.mybatis.enhance.dsl.factory.EnhanceDsl
     */
    @Autowired
    protected EnhanceDslFactory dslFactory;
    /**
     * MyBatis Enhance 增强数据接口，泛型方式注入
     * 相关文档：http://apiboot.minbox.io/zh-cn/docs/api-boot-mybatis-enhance.html
     * 如：EnhanceMapper<SystemUser,String> mapper，对应{@link org.minbox.framework.api.boot.admin.mapper.SystemUserMapper}
     */
    @Autowired
    protected EnhanceMapper<T> mapper;
    /**
     * MinBox Logging 提供的全局日志
     * 相关文档：http://www.minbox.io/logging/config-client.html#_15-%E5%85%A8%E5%B1%80%E6%97%A5%E5%BF%97
     */
    @Autowired
    protected GlobalLogging globalLogging;
}
