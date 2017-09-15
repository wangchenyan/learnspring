package me.wcy.learnspring.interceptor;

import me.wcy.learnspring.interceptor.annotation.TokenCheck;
import me.wcy.learnspring.service.TokenService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.method.HandlerMethod;
import org.springframework.web.servlet.handler.HandlerInterceptorAdapter;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.PrintWriter;
import java.lang.reflect.Method;

/**
 * Created by hzwangchenyan on 2017/9/15.
 */
public class TokenInterceptor extends HandlerInterceptorAdapter {
    @Autowired
    private TokenService tokenService;

    @Override
    public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler) throws Exception {
        // 如果不是映射到方法直接通过
        if (!(handler instanceof HandlerMethod)) {
            return true;
        }

        HandlerMethod handlerMethod = (HandlerMethod) handler;
        Method method = handlerMethod.getMethod();
        TokenCheck pageTokenCheck = method.getAnnotation(TokenCheck.class);
        if (pageTokenCheck == null) {
            return true;
        }

        Long userId = -1L;
        try {
            userId = Long.parseLong(request.getParameter("userId"));
        } catch (NumberFormatException e) {
            e.printStackTrace();
        }

        String token = request.getParameter("token");
        if (!tokenService.checkToken(userId, token)) {
            response.setStatus(HttpServletResponse.SC_UNAUTHORIZED);
            PrintWriter pw = response.getWriter();
            pw.append("Auth Failed !");
            pw.flush();
            pw.close();
            return false;
        }

        return true;
    }
}
