package org.minbox.framework.api.boot.admin;

import org.minbox.framework.logging.spring.context.annotation.admin.EnableLoggingAdmin;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

/**
 * Log Server Application
 *
 * @author 恒宇少年
 */
@SpringBootApplication
@EnableLoggingAdmin
public class ApiBootAdminLoggingServerApplication {
    /**
     * logger instance
     */
    static Logger logger = LoggerFactory.getLogger(ApiBootAdminLoggingServerApplication.class);

    public static void main(String[] args) {
        SpringApplication.run(ApiBootAdminLoggingServerApplication.class, args);
        logger.info("{}服务启动成功.", "Logging Server");
    }
}
