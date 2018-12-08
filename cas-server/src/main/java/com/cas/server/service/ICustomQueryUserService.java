package com.cas.server.service;

import java.util.Map;

/**
 * @author 赵秀非
 */
public interface ICustomQueryUserService {
    Map<String, Object> query(String username, String password);

    String support();
}
