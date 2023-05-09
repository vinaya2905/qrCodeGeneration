package com.barcode.springbootqrcodegeneratorapp.config;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.MessageSource;
import org.springframework.context.i18n.LocaleContextHolder;
import org.springframework.stereotype.Component;

@Component
public class GlobalMessageSource {

    @Autowired
    private MessageSource messageSource;

    public String get(String key) {
        return messageSource.getMessage(key, null, LocaleContextHolder.getLocale());
    }

    public String get(String key, String args) {
        return messageSource.getMessage(key, new Object[]{args}, LocaleContextHolder.getLocale());
    }
}
