package com.barcode.springbootqrcodegeneratorapp.config;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Configuration;

@Configuration
public class ApplicationConfig {

    @Autowired
    private final PropertiesConfiguration propertiesConfiguration;
    public ApplicationConfig(PropertiesConfiguration propertiesConfiguration)
    {
        this.propertiesConfiguration = propertiesConfiguration;
    }
}
