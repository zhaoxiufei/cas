package com.cas.client;

import org.jasig.cas.client.authentication.AuthenticationFilter;
import org.jasig.cas.client.session.SingleSignOutFilter;
import org.jasig.cas.client.session.SingleSignOutHttpSessionListener;
import org.jasig.cas.client.util.HttpServletRequestWrapperFilter;
import org.jasig.cas.client.validation.Cas30ProxyReceivingTicketValidationFilter;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.context.properties.EnableConfigurationProperties;
import org.springframework.boot.web.servlet.FilterRegistrationBean;
import org.springframework.boot.web.servlet.ServletListenerRegistrationBean;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import java.util.EventListener;
import java.util.HashMap;
import java.util.Map;

@SuppressWarnings("SpringJavaAutowiredFieldsWarningInspection")
@Configuration
@EnableConfigurationProperties(CasClientConfigurationProperties.class)
public class CasClientConfiguration {

    @Autowired
    private CasClientConfigurationProperties configProps;

    /**
     * 配置登出过滤器
     */
    @Bean
    public FilterRegistrationBean filterSingleRegistration() {
        final FilterRegistrationBean registration = new FilterRegistrationBean();
        registration.setFilter(new SingleSignOutFilter());
        // 设定匹配的路径
        registration.addUrlPatterns("/*");
        Map<String, String> initParameters = new HashMap<>();
        initParameters.put("casServerUrlPrefix", configProps.getServerUrlPrefix());
        registration.setInitParameters(initParameters);
        // 设定加载的顺序
        registration.setOrder(10);
        return registration;
    }

    /**
     * 配置过滤验证器 这里用的是Cas30ProxyReceivingTicketValidationFilter
     */
    @Bean
    public FilterRegistrationBean filterValidationRegistration() {
        final FilterRegistrationBean registration = new FilterRegistrationBean();
        registration.setFilter(new Cas30ProxyReceivingTicketValidationFilter());
        // 设定匹配的路径
        registration.addUrlPatterns("/*");
        Map<String, String> initParameters = new HashMap<>();
        initParameters.put("casServerUrlPrefix", configProps.getServerUrlPrefix());
        initParameters.put("serverName", configProps.getServerName());
        initParameters.put("useSession", "true");
        registration.setInitParameters(initParameters);
        // 设定加载的顺序
        registration.setOrder(12);
        return registration;
    }

    /**
     * 配置授权过滤器
     */
    @Bean
    public FilterRegistrationBean filterAuthenticationRegistration() {
        final FilterRegistrationBean registration = new FilterRegistrationBean();
        registration.setFilter(new AuthenticationFilter());
        // 设定匹配的路径
        registration.addUrlPatterns("/*");
        Map<String, String> initParameters = new HashMap<String, String>();
        initParameters.put("casServerLoginUrl", configProps.getServerLoginUrl());
        initParameters.put("serverName", configProps.getServerName());

        if (configProps.getIgnorePattern() != null && !"".equals(configProps.getIgnorePattern())) {
            initParameters.put("ignorePattern", configProps.getIgnorePattern());
        }

        //自定义UrlPatternMatcherStrategy 验证规则
        if (configProps.getIgnoreUrlPatternType() != null && !"".equals(configProps.getIgnoreUrlPatternType())) {
            initParameters.put("ignoreUrlPatternType", configProps.getIgnoreUrlPatternType());
        }

        registration.setInitParameters(initParameters);
        // 设定加载的顺序
        registration.setOrder(13);
        return registration;
    }

    /**
     * request wraper过滤器
     */
    @Bean
    public FilterRegistrationBean filterWrapperRegistration() {
        final FilterRegistrationBean registration = new FilterRegistrationBean();
        registration.setFilter(new HttpServletRequestWrapperFilter());
        // 设定匹配的路径
        registration.addUrlPatterns("/*");
        // 设定加载的顺序
        registration.setOrder(14);
        return registration;
    }

    /**
     * 添加监听器
     */
    @Bean
    public ServletListenerRegistrationBean<EventListener> singleSignOutListenerRegistration() {
        ServletListenerRegistrationBean<EventListener> registrationBean = new ServletListenerRegistrationBean<>();
        registrationBean.setListener(new SingleSignOutHttpSessionListener());
        registrationBean.setOrder(1);
        return registrationBean;
    }
}