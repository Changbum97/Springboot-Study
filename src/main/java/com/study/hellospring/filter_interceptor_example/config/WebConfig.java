package com.study.hellospring.filter_interceptor_example.config;

import com.study.hellospring.filter_interceptor_example.filter.AuthFilter;
import com.study.hellospring.filter_interceptor_example.filter.LogFilter;
import com.study.hellospring.filter_interceptor_example.interceptor.AuthInterceptor;
import com.study.hellospring.filter_interceptor_example.interceptor.LogInterceptor;
import org.springframework.boot.web.servlet.FilterRegistrationBean;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.InterceptorRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

import javax.servlet.Filter;

@Configuration
public class WebConfig implements WebMvcConfigurer {
    @Override
    public void addInterceptors(InterceptorRegistry registry) {
        registry.addInterceptor(new LogInterceptor())
                .order(1)
                .addPathPatterns("/interceptor-test/*");
        registry.addInterceptor(new AuthInterceptor())
                .order(2)
                .addPathPatterns("/interceptor-test/pass");
        registry.addInterceptor(new LogInterceptor())
                .order(3)
                .addPathPatterns("/filter-interceptor-test/*");
    }

    @Bean
    public FilterRegistrationBean addLogFilter() {
        FilterRegistrationBean<Filter> filterRegistrationBean = new FilterRegistrationBean<>();

        // 구현한 필터 등록
        filterRegistrationBean.setFilter(new LogFilter());

        // 필터 순서 지정
        filterRegistrationBean.setOrder(1);

        // 필터가 적용될 URL 지정
        filterRegistrationBean.addUrlPatterns("/filter-test/pass", "/filter-interceptor-test/*");

        return filterRegistrationBean;
    }

    @Bean
    public FilterRegistrationBean addAuthFilter() {
        FilterRegistrationBean<Filter> filterRegistrationBean = new FilterRegistrationBean<>();

        // 구현한 필터 등록
        filterRegistrationBean.setFilter(new AuthFilter());

        // 필터 순서 지정
        filterRegistrationBean.setOrder(2);

        // 필터가 적용될 URL 지정
        filterRegistrationBean.addUrlPatterns("/filter-test/*");

        return filterRegistrationBean;
    }

}
