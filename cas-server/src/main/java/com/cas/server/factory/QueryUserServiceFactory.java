package com.cas.server.factory;

import com.cas.query.IQueryUserService;
import com.google.common.collect.Maps;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.Map;

/**
 * @author 赵秀非
 */
@SuppressWarnings({"JavaDoc", "SpringJavaAutowiredFieldsWarningInspection"})
@Component
public class QueryUserServiceFactory {
    private Map<String, IQueryUserService> map = Maps.newHashMap();

    @Autowired(required = false)
    public void init(IQueryUserService[] customQueryUserServices) {
        for (IQueryUserService service : customQueryUserServices) {
            map.put(service.support(), service);
        }
    }

    public IQueryUserService getService(String system) {
        return map.get(system);
    }
}
