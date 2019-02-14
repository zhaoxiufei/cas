package com.cas.lms.query;

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
public class LmsQueryUserService implements IQueryUserService {
    private JdbcTemplate jdbcTemplate;


    public void setJdbcTemplate(JdbcTemplate jdbcTemplate) {
        this.jdbcTemplate = jdbcTemplate;
    }

    @Override
    public Map<String, Object> query(String username, String password) throws AccountException {
        final List<Map<String, Object>> queryForMap = jdbcTemplate.queryForList("select * from sys_user where username =?", username);
        if (queryForMap.isEmpty()) {
            throw new AccountNotFoundException();
        }
        return queryForMap.get(0);
    }

    @Override
    public String support() {
        return "lms";
    }
}
