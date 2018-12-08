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
public class App2QueryUserService implements ICustomQueryUserService {
    @Override
    public Map<String, Object> query(String username, String password) {
        final HashMap<String, Object> map = Maps.newHashMap();
        map.put("client", "app2");
        //查询数据库 判断用户类型之类的
        return map;
    }

    @Override
    public String support() {
        return "app2";
    }
}
