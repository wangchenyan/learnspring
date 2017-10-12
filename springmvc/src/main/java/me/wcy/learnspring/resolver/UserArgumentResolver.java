package me.wcy.learnspring.resolver;

import me.wcy.learnspring.dao.UserDAO;
import me.wcy.learnspring.po.User;
import me.wcy.learnspring.resolver.annotation.ResolveUser;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.MethodParameter;
import org.springframework.dao.DataAccessException;
import org.springframework.web.bind.support.WebDataBinderFactory;
import org.springframework.web.context.request.NativeWebRequest;
import org.springframework.web.method.support.HandlerMethodArgumentResolver;
import org.springframework.web.method.support.ModelAndViewContainer;

/**
 * Created by hzwangchenyan on 2017/9/15.
 */
public class UserArgumentResolver implements HandlerMethodArgumentResolver {
    @Autowired
    private UserDAO userDAO;

    @Override
    public boolean supportsParameter(MethodParameter parameter) {
        return parameter.hasParameterAnnotation(ResolveUser.class)
                && parameter.getParameterType().isAssignableFrom(User.class);
    }

    @Override
    public Object resolveArgument(MethodParameter parameter, ModelAndViewContainer mavContainer, NativeWebRequest webRequest, WebDataBinderFactory binderFactory) throws Exception {
        try {
            Long userId = Long.valueOf(webRequest.getParameter("userId"));
            return userDAO.queryById(userId);
        } catch (NumberFormatException | DataAccessException e) {
            e.printStackTrace();
            return null;
        }
    }
}
