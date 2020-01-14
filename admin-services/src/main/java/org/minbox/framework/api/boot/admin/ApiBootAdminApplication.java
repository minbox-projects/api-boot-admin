package org.minbox.framework.api.boot.admin;

import org.minbox.framework.api.boot.autoconfigure.swagger.annotation.EnableApiBootSwagger;
import org.minbox.framework.logging.spring.context.annotation.client.EnableLoggingClient;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

/**
 * ApiBoot Admin Application
 * 启动入口类
 *
 * @author 恒宇少年
 */
@SpringBootApplication
@EnableApiBootSwagger
@EnableLoggingClient
public class ApiBootAdminApplication {
    /**
     * logger instance
     */
    static Logger logger = LoggerFactory.getLogger(ApiBootAdminApplication.class);

    public static void main(String[] args) {
        SpringApplication.run(ApiBootAdminApplication.class, args);
        logger.info("「ApiBoot Admin」服务启动成功.");
    }
}
