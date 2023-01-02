package com.study.hellospring.filter_interceptor_example.interceptor;

import lombok.extern.slf4j.Slf4j;
import org.springframework.web.servlet.HandlerInterceptor;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.UUID;

@Slf4j
public class AuthInterceptor implements HandlerInterceptor {

    @Override
    public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler) throws Exception {
        String requestURI = request.getRequestURI();
        String uuid = UUID.randomUUID().toString();

        request.setAttribute("logId", uuid);


        boolean auth = Boolean.valueOf(request.getHeader("pass"));

        if(auth != true) {
            log.info("preHandle : 인증 실패 : REQUEST [{}][{}]", uuid, requestURI);
            response.sendRedirect("/interceptor-test/fail");
            return false;
        }

        log.info("preHandle : 인증 성공 : REQUEST [{}][{}][{}]", uuid, requestURI, handler);
        return true;
    }

    @Override
    public void postHandle(HttpServletRequest request, HttpServletResponse response, Object handler, ModelAndView modelAndView) throws Exception {
        log.info("postHandle : RESPONSE [{}][{}]", request.getAttribute("logId"), modelAndView);
    }

    @Override
    public void afterCompletion(HttpServletRequest request, HttpServletResponse response, Object handler, Exception ex) throws Exception {
        String requestURI = request.getRequestURI();
        String logId = (String) request.getAttribute("logId");
        log.info("afterCompletion : RESPONSE [{}][{}][{}]", logId, requestURI, handler);

        if(ex != null) {
            log.error("afterCompletion error!!", ex);
        }
    }
}
