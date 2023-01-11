package com.study.hellospring.converter_formatter_example.domain;


import org.springframework.core.convert.converter.Converter;

public class StringToIpPortConverter implements Converter<String, IpPort> {

    @Override
    public IpPort convert(String source) {
        String[] splitSource = source.split(":");
        String ip = source.split(":")[0];
        int port = Integer.valueOf(source.split(":")[1]);

        return new IpPort(ip, port);
    }
}
