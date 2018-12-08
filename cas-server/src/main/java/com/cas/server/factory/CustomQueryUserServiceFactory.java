package com.cas.server.factory;

import com.cas.server.service.ICustomQueryUserService;
import com.google.common.collect.Maps;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.Map;

/**
 * @author 赵秀非
 */
@SuppressWarnings({"JavaDoc", "SpringJavaAutowiredFieldsWarningInspection"})
@Component
public class CustomQueryUserServiceFactory {
    private Map<String, ICustomQueryUserService> map = Maps.newHashMap();

    @Autowired
    public void init(ICustomQueryUserService[] customQueryUserServices) {
        for (ICustomQueryUserService service : customQueryUserServices) {
            map.put(service.support(), service);
        }
    }

    public ICustomQueryUserService getService(String system) {
        return map.get(system);
    }
}
