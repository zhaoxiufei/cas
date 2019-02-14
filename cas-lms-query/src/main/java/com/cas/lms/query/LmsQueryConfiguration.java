package com.cas.lms.query;

import com.zaxxer.hikari.HikariConfig;
import com.zaxxer.hikari.HikariDataSource;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.jdbc.core.JdbcTemplate;

import javax.sql.DataSource;

/**
 * @author 赵秀非
 */
@SuppressWarnings({"JavaDoc", "SpringJavaAutowiredFieldsWarningInspection"})
@Configuration
public class LmsQueryConfiguration {

    @Bean
    public LmsQueryUserService app1QueryUserService() {
        final LmsQueryUserService userService = new LmsQueryUserService();
        userService.setJdbcTemplate(jdbcTemplate());
        return userService;
    }

    public DataSource dataSource() {
        //TODO 数据源
        HikariConfig config = new HikariConfig();
        config.setJdbcUrl("jdbc:mysql://192.168.9.240:3306/lms?characterEncoding=utf8&serverTimezone=UTC&useSSL=false&allowPublicKeyRetrieval=true");
        config.setUsername("lms");
        config.setPassword("lmsZdWI1o");
        config.setDriverClassName("com.mysql.cj.jdbc.Driver");
        return new HikariDataSource(config);
    }

    public JdbcTemplate jdbcTemplate() {
        return new JdbcTemplate(dataSource());
    }
}
