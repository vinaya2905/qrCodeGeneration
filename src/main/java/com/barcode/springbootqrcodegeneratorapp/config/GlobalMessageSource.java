package com.barcode.springbootqrcodegeneratorapp.config;

import lombok.AllArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.MessageSource;
import org.springframework.context.annotation.Bean;
import org.springframework.context.i18n.LocaleContextHolder;
import org.springframework.context.support.ReloadableResourceBundleMessageSource;
import org.springframework.stereotype.Component;

import java.nio.charset.StandardCharsets;

import static com.barcode.springbootqrcodegeneratorapp.constants.QRCodeConstants.*;

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
