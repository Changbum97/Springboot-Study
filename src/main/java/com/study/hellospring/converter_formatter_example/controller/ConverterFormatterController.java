package com.study.hellospring.converter_formatter_example.controller;

import com.study.hellospring.converter_formatter_example.domain.IpPort;
import com.study.hellospring.converter_formatter_example.domain.Product;
import com.study.hellospring.converter_formatter_example.domain.StringToIpPortConverter;
import org.springframework.core.convert.support.DefaultConversionService;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import java.time.LocalDateTime;

@Controller
@RequestMapping("/converter-formatter-example")
public class ConverterFormatterController {

    @ResponseBody
    @GetMapping("/string-to-ip-port-v1")
    public IpPort stringToIpPortV1(@RequestParam String str) {
        DefaultConversionService conversionService = new DefaultConversionService();
        conversionService.addConverter(new StringToIpPortConverter());

        IpPort ipPort = conversionService.convert(str, IpPort.class);
        return ipPort;
    }

    @ResponseBody
    @GetMapping("/string-to-ip-port-v2")
    public IpPort stringToIpPortV2(@RequestParam IpPort ipPort) {
        return ipPort;
    }

    @GetMapping("/ip-port-to-string")
    public String ipPortToString(Model model) {
        model.addAttribute("ipPort", new IpPort("127.0.0.1", 8000));
        return "converter_formatter_example/converterPage";
    }

    @GetMapping("/formatter-page")
    public String formatterPage(Model model) {

        model.addAttribute("number", 10000000);

        model.addAttribute("product", new Product("가방", 1000000, LocalDateTime.now()));
        return "converter_formatter_example/formatterPage";
    }
}
