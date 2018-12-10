package com.cas.app2;

import org.jasig.cas.client.authentication.AttributePrincipal;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

/**
 * @author 赵秀非
 */
@SuppressWarnings({"JavaDoc", "SpringJavaAutowiredFieldsWarningInspection"})
@Controller
@RequestMapping("/")
public class TestController {
    @ResponseBody
    @GetMapping("login")
    public Object login(HttpServletRequest request) {
        //获取用户信息
        AttributePrincipal principal = (AttributePrincipal) request.getUserPrincipal();

        return principal.getAttributes();
    }

    @GetMapping("logout")
    public String logout(HttpSession session) {
        session.invalidate();
        return "redirect:http://server.cas.com:8080/cas/logout?service=http://www.app2.com:8092/login";
    }
}
