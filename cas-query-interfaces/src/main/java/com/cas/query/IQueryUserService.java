package com.cas.query;

import javax.security.auth.login.AccountException;
import java.util.Map;

/**
 * @author 赵秀非
 */
public interface IQueryUserService {
    Map<String, Object> query(String username, String password) throws AccountException;

    String support();
}
