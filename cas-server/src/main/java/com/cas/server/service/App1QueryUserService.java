package com.cas.server.service;

import com.google.common.collect.Maps;
import org.springframework.stereotype.Service;

import java.util.HashMap;
import java.util.Map;

/**
 * @author 赵秀非
 */
@SuppressWarnings({"JavaDoc", "SpringJavaAutowiredFieldsWarningInspection"})
@Service
public class App1QueryUserService implements ICustomQueryUserService {
    @Override
    public Map<String, Object> query(String username, String password) {
        final HashMap<String, Object> map = Maps.newHashMap();
        map.put("client", "app1");
        return map;
    }

    @Override
    public String support() {
        return "app1";
    }
}
