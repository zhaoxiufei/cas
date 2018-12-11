package com.cas.server.exception;

import javax.security.auth.login.AccountException;

/**
 * @author 赵秀非
 */
@SuppressWarnings({"JavaDoc", "SpringJavaAutowiredFieldsWarningInspection"})
public class CaptchaRequiredException extends AccountException {
    public CaptchaRequiredException() {
        super();
    }

    public CaptchaRequiredException(String msg) {
        super(msg);
    }
}
