package com.cas.server.handler;

import com.cas.query.IQueryUserService;
import com.cas.server.CustomCredential;
import com.cas.server.exception.CaptchaErrorException;
import com.cas.server.exception.CaptchaRequierException;
import com.cas.server.factory.QueryUserServiceFactory;
import com.cuisongliu.kaptcha.autoconfigure.util.KaptchaUtil;
import org.apache.commons.lang3.StringUtils;
import org.apereo.cas.authentication.AuthenticationHandlerExecutionResult;
import org.apereo.cas.authentication.Credential;
import org.apereo.cas.authentication.PreventedException;
import org.apereo.cas.authentication.handler.support.AbstractPreAndPostProcessingAuthenticationHandler;
import org.apereo.cas.authentication.principal.PrincipalFactory;
import org.apereo.cas.services.ServicesManager;

import javax.security.auth.login.AccountNotFoundException;
import javax.servlet.http.HttpSession;
import java.security.GeneralSecurityException;
import java.util.Map;

public class CustomAuthenticationHandler extends AbstractPreAndPostProcessingAuthenticationHandler {


    private HttpSession httpSession;
    private QueryUserServiceFactory queryUserServiceFactory;

    public void setQueryUserServiceFactory(QueryUserServiceFactory queryUserServiceFactory) {
        this.queryUserServiceFactory = queryUserServiceFactory;
    }

    public CustomAuthenticationHandler(String name, ServicesManager servicesManager, PrincipalFactory principalFactory, Integer order) {
        super(name, servicesManager, principalFactory, order);
    }

    public void setHttpSession(HttpSession httpSession) {
        this.httpSession = httpSession;
    }

    @Override
    protected AuthenticationHandlerExecutionResult doAuthentication(Credential credential) throws GeneralSecurityException, PreventedException {
        CustomCredential customCredential = (CustomCredential) credential;
        if (httpSession.getAttribute("usernameAndPasswordError") != null) {//需要校验
            String requestCaptcha = customCredential.getCaptcha();
            if (StringUtils.isEmpty(requestCaptcha)) {
                throw new CaptchaRequierException("验证码必须输入项");
            }
            String sessionValue = (String) httpSession.getAttribute(KaptchaUtil.kaptchaKeyGenerator(""));
            if (!sessionValue.equals(requestCaptcha)) {
                throw new CaptchaErrorException();
            }
        }

        final IQueryUserService service = queryUserServiceFactory.getService(customCredential.getSystem());
        Map<String, Object> info;
        try {
            info = service.query(customCredential.getUsername(), customCredential.getPassword());
        } catch (AccountNotFoundException e) {
            httpSession.setAttribute("usernameAndPasswordError", "error");
            throw e;
        }
        httpSession.removeAttribute("usernameAndPasswordError");
        return createHandlerResult(customCredential, this.principalFactory.createPrincipal(customCredential.getId(), info));
    }

    @Override
    public boolean supports(Credential credential) {
        return credential instanceof CustomCredential;
    }
}