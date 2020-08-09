package org.minbox.framework.api.boot.admin.mapper;

import com.gitee.hengboy.mybatis.enhance.mapper.EnhanceMapper;
import org.apache.ibatis.annotations.Param;
import org.minbox.framework.api.boot.admin.entity.SystemUser;
import org.springframework.beans.factory.support.BeanDefinitionRegistry;
import org.springframework.core.type.AnnotationMetadata;

/**
 * 系统用户数据接口
 * 继承{@link EnhanceMapper}的数据接口会自动加入到Spring IOC
 * 使用文档详见：http://apiboot.minbox.io/zh-cn/docs/api-boot-mybatis-enhance.html
 *
 * @author 恒宇少年
 * @see org.minbox.framework.api.boot.autoconfigure.enhance.ApiBootMyBatisEnhanceAutoConfiguration
 * @see org.minbox.framework.api.boot.autoconfigure.enhance.ApiBootMyBatisEnhanceAutoConfiguration.AutoConfiguredMapperScannerRegistrar#registerBeanDefinitions(AnnotationMetadata, BeanDefinitionRegistry)
 */
public interface SystemUserMapper extends EnhanceMapper<SystemUser> {
    /**
     * 根据用户名查询用户基本信息
     * "findByXxx"是由MyBatis Enhance所提供的方法命名规则查询，Xxx是驼峰格式的数据实体字段名，如：{@link SystemUser#getUsername()}
     *
     * @param username {@link SystemUser#getUsername()}
     * @return {@link SystemUser}
     */
    SystemUser findByUsername(@Param("username") String username);
}
