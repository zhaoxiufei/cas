package com.cas.server.exception;

import javax.security.auth.login.AccountException;

/**
 * @author 赵秀非
 */
@SuppressWarnings({"JavaDoc", "SpringJavaAutowiredFieldsWarningInspection"})
public class CaptchaRequierException extends AccountException {
    public CaptchaRequierException() {
        super();
    }

    public CaptchaRequierException(String msg) {
        super(msg);
    }
}
