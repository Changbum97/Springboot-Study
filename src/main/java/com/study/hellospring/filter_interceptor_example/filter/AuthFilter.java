package com.study.hellospring.filter_interceptor_example.filter;

import lombok.extern.slf4j.Slf4j;
import org.springframework.util.PatternMatchUtils;

import javax.servlet.*;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.UUID;

@Slf4j
public class AuthFilter implements Filter {

    // 권한 인증을 체크 하지 않을 URL
    private static final String[] whiteList = {"/filter-test/all-pass", "/filter-test/fail"};

    // 권한 인증 체크 해야되는 URL
    private static final String[] blackList = {"/filter-test/pass"};

    @Override
    public void doFilter(ServletRequest request, ServletResponse response, FilterChain chain) throws IOException, ServletException {

        HttpServletRequest httpServletRequest = (HttpServletRequest) request;
        String requestURI = httpServletRequest.getRequestURI();

        HttpServletResponse httpServletResponse = (HttpServletResponse) response;

        // 요청의 추적을 위해 UUID 사용
        String uuid = UUID.randomUUID().toString();
        request.setAttribute("logId", uuid);

        try {
            log.info("doFilter : REQUEST [{}][{}]", uuid, requestURI);

            // requestURI가 Auth Check가 필요한 요청이라면 체크
            if(isAuthCheckPath(requestURI)) {
                log.info("doFilter : 인증 체크 로직 실행 REQUEST [{}][{}]", uuid, requestURI);

                boolean auth = Boolean.valueOf(httpServletRequest.getHeader("pass"));

                if(auth != true) {
                    log.info("dofilter : 인증 실패 : REQUEST [{}][{}]", uuid, requestURI);
                    httpServletResponse.sendRedirect("/filter-test/fail");
                    return;
                }
            }

            log.info("dofilter : 인증 성공 : REQUEST [{}][{}]", uuid, requestURI);
            chain.doFilter(request, response);

        } catch (Exception e) {
            throw e;
        } finally {
            log.info("dofilter : RESPONSE [{}][{}]", uuid, requestURI);
        }

    }

    private boolean isAuthCheckPath(String requestURI) {
        // whiteList 사용 방법
        // return !PatternMatchUtils.simpleMatch(whiteList, requestURI);

        // blackList 사용 방법
        return PatternMatchUtils.simpleMatch(blackList, requestURI);
    }
}
