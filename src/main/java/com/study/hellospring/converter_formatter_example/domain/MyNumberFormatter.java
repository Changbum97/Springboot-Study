package com.study.hellospring.converter_formatter_example.domain;

import org.springframework.format.Formatter;

import java.text.NumberFormat;
import java.text.ParseException;
import java.util.Locale;

public class MyNumberFormatter implements Formatter<Number> {
    @Override
    public Number parse(String text, Locale locale) throws ParseException {
        // "1,000" -> 1000
        NumberFormat format = NumberFormat.getInstance(locale);
        return format.parse(text);
    }

    @Override
    public String print(Number object, Locale locale) {
        // 1000 -> "1,000"
        NumberFormat instance = NumberFormat.getInstance(locale);
        return instance.format(object);
    }
}
