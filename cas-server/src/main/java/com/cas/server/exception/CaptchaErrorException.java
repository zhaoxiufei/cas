package com.cas.server.exception;

import javax.security.auth.login.AccountException;

/**
 * @author 赵秀非
 */
@SuppressWarnings({"JavaDoc", "SpringJavaAutowiredFieldsWarningInspection"})
public class CaptchaErrorException extends AccountException {
    public CaptchaErrorException() {
        super();
    }

    public CaptchaErrorException(String msg) {
        super(msg);
    }
}
