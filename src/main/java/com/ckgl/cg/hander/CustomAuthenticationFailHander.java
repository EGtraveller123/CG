package com.ckgl.cg.hander;


import org.springframework.security.core.AuthenticationException;
import org.springframework.security.web.WebAttributes;
import org.springframework.security.web.authentication.SimpleUrlAuthenticationFailureHandler;
import org.springframework.stereotype.Component;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

/**
 * CustomAuthenticationFailHander <br/>
 * 描述 : CustomAuthenticationFailHander <br/>
 * 作者 : qianmoQ <br/>
 * 版本 : 1.0 <br/>
 * 创建时间 : 2018-03-20 下午4:08 <br/>
 * 联系作者 : <a href="mailTo:shichengoooo@163.com">qianmoQ</a>
 */
@Component(value = "customAuthenticationFailHander")
public class CustomAuthenticationFailHander extends SimpleUrlAuthenticationFailureHandler {

    @Override
    public void onAuthenticationFailure(HttpServletRequest request, HttpServletResponse response, AuthenticationException exception) throws IOException, ServletException {
        System.out.println("登录失败!!!");
        this.returnJson(response, exception);
    }

    /**
     * 直接返回需要返回的 json 数据
     */
    private void returnJson(HttpServletResponse response,
                            AuthenticationException exception) throws IOException {
        response.setCharacterEncoding("UTF-8");
        response.setContentType("application/json");
        response.getWriter().println("{\"ok\":0,\"msg\":\"" + exception.getLocalizedMessage() + "\"}");
    }

    /**
     * 直接返会错误页面
     */
    private void returnErrorPage(HttpServletRequest request, HttpServletResponse response,
                                 AuthenticationException exception) throws IOException, ServletException {
        String strUrl = request.getContextPath() + "/loginErrorPath";
        request.getSession().setAttribute("status", 0);
        request.getSession().setAttribute("message", exception.getLocalizedMessage());
        request.getSession().setAttribute(WebAttributes.AUTHENTICATION_EXCEPTION, exception);
        // 使用该方法会出现错误
//        request.getRequestDispatcher(strUrl).forward(request, response);
        response.sendRedirect(strUrl);
    }

}