package com.cas.app1.query;

import com.cas.query.IQueryUserService;
import org.springframework.jdbc.core.JdbcTemplate;

import javax.security.auth.login.AccountException;
import javax.security.auth.login.AccountNotFoundException;
import java.util.List;
import java.util.Map;

/**
 * @author 赵秀非
 */
@SuppressWarnings({"JavaDoc", "SpringJavaAutowiredFieldsWarningInspection"})
public class App1QueryUserService implements IQueryUserService {
    private JdbcTemplate jdbcTemplate;


    public void setJdbcTemplate(JdbcTemplate jdbcTemplate) {
        this.jdbcTemplate = jdbcTemplate;
    }

    @Override
    public Map<String, Object> query(String username, String password) throws AccountException {
        final List<Map<String, Object>> queryForMap = jdbcTemplate.queryForList("select * from sys_user where username =?", new Object[]{username});
        if (queryForMap.size() == 0) {
            throw new AccountNotFoundException();
        }
        return queryForMap.get(0);
    }

    @Override
    public String support() {
        return "app1";
    }
}
