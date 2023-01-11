package com.study.hellospring.converter_formatter_example.config;

import com.study.hellospring.converter_formatter_example.domain.IpPortToStringConverter;
import com.study.hellospring.converter_formatter_example.domain.MyNumberFormatter;
import com.study.hellospring.converter_formatter_example.domain.StringToIpPortConverter;
import org.springframework.context.annotation.Configuration;
import org.springframework.format.FormatterRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

@Configuration
public class WebConfig2 implements WebMvcConfigurer {
    @Override
    public void addFormatters(FormatterRegistry registry) {
        registry.addConverter(new StringToIpPortConverter());
        registry.addConverter(new IpPortToStringConverter());
        registry.addFormatter(new MyNumberFormatter());
    }
}
