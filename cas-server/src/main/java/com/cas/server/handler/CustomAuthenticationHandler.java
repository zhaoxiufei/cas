package com.cas.server.handler;

import com.cas.server.CustomCredential;
import com.cas.server.factory.CustomQueryUserServiceFactory;
import com.cas.server.service.ICustomQueryUserService;
import org.apereo.cas.authentication.AuthenticationHandlerExecutionResult;
import org.apereo.cas.authentication.Credential;
import org.apereo.cas.authentication.PreventedException;
import org.apereo.cas.authentication.handler.support.AbstractPreAndPostProcessingAuthenticationHandler;
import org.apereo.cas.authentication.principal.PrincipalFactory;
import org.apereo.cas.services.ServicesManager;
import org.springframework.web.context.request.RequestContextHolder;
import org.springframework.web.context.request.ServletRequestAttributes;

import java.security.GeneralSecurityException;
import java.util.Map;

public class CustomAuthenticationHandler extends AbstractPreAndPostProcessingAuthenticationHandler {


    private CustomQueryUserServiceFactory customQueryUserServiceFactory;

    public void setCustomQueryUserServiceFactory(CustomQueryUserServiceFactory customQueryUserServiceFactory) {
        this.customQueryUserServiceFactory = customQueryUserServiceFactory;
    }

    public CustomAuthenticationHandler(String name, ServicesManager servicesManager, PrincipalFactory principalFactory, Integer order) {
        super(name, servicesManager, principalFactory, order);
    }

    @Override
    protected AuthenticationHandlerExecutionResult doAuthentication(Credential credential) throws GeneralSecurityException, PreventedException {
        CustomCredential customCredential = (CustomCredential) credential;
        String requestCaptcha = customCredential.getCaptcha();
        ServletRequestAttributes attributes = (ServletRequestAttributes) RequestContextHolder.getRequestAttributes();
        //TODO 验证验证码
        final ICustomQueryUserService service = customQueryUserServiceFactory.getService(customCredential.getSystem());
        final Map<String, Object> query = service.query(customCredential.getUsername(), customCredential.getPassword());
//        Object attribute = attributes.getRequest().getSession().getAttribute("captcha");

//        String realCaptcha = attribute == null ? null : attribute.toString();

//        if(StringUtils.isBlank(requestCaptcha) || !requestCaptcha.toUpperCase().equals(realCaptcha)){
//            throw new FailedLoginException("验证码错误");
//        }
//
//        String username = customCredential.getUsername();
//        Map<String, Object> user = userService.findByUserName(username);
//
//        //可以在这里直接对用户名校验,或者调用 CredentialsMatcher 校验
//        if (user == null || !user.get("password").equals(customCredential.getPassword())) {
//            throw new UnknownAccountException("用户名或密码错误！");
//        }
//        //这里将 密码对比 注销掉,否则 无法锁定  要将密码对比 交给 密码比较器 在这里可以添加自己的密码比较器等
//        //if (!password.equals(user.getPassword())) {
//        //    throw new IncorrectCredentialsException("用户名或密码错误！");
//        //}
//        if ("1".equals(user.get("state"))) {
//            throw new LockedAccountException("账号已被锁定,请联系管理员！");
//        }

        return createHandlerResult(customCredential, this.principalFactory.createPrincipal("666", query));
    }

    @Override
    public boolean supports(Credential credential) {
        return credential instanceof CustomCredential;
    }
}