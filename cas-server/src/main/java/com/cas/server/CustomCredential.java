package com.cas.server;

import lombok.Data;
import lombok.EqualsAndHashCode;
import org.apereo.cas.authentication.RememberMeUsernamePasswordCredential;

import javax.validation.constraints.Size;

@Data
@EqualsAndHashCode(callSuper = true)
public class CustomCredential extends RememberMeUsernamePasswordCredential {

    @Size(min = 1, max = 5, message = "require.system")
    private String system;
    private String captcha;
}