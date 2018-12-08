package com.cas.server.config;

import com.cas.server.factory.CustomQueryUserServiceFactory;
import com.cas.server.handler.CustomAuthenticationHandler;
import org.apereo.cas.authentication.AuthenticationEventExecutionPlan;
import org.apereo.cas.authentication.AuthenticationEventExecutionPlanConfigurer;
import org.apereo.cas.authentication.AuthenticationHandler;
import org.apereo.cas.authentication.principal.DefaultPrincipalFactory;
import org.apereo.cas.configuration.CasConfigurationProperties;
import org.apereo.cas.services.ServicesManager;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.boot.context.properties.EnableConfigurationProperties;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@SuppressWarnings({"SpringJavaInjectionPointsAutowiringInspection", "SpringJavaAutowiredFieldsWarningInspection", "SpringFacetCodeInspection"})
@Configuration
@EnableConfigurationProperties(CasConfigurationProperties.class)
public class CustomAuthenticationEventExecutionPlanConfigurer implements AuthenticationEventExecutionPlanConfigurer {

    @Autowired
    @Qualifier("servicesManager")
    private ServicesManager servicesManager;

    @Autowired
    private CustomQueryUserServiceFactory customQueryUserServiceFactory;


    @Bean
    public AuthenticationHandler customAuthenticationHandler() {
        CustomAuthenticationHandler handler = new CustomAuthenticationHandler(
                CustomAuthenticationHandler.class.getSimpleName(),
                servicesManager,
                new DefaultPrincipalFactory(),
                1);
        handler.setCustomQueryUserServiceFactory(customQueryUserServiceFactory);
        return handler;
    }

    @Override
    public void configureAuthenticationExecutionPlan(AuthenticationEventExecutionPlan plan) {
        plan.registerAuthenticationHandler(customAuthenticationHandler());
    }
}