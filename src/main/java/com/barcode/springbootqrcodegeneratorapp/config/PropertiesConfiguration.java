package com.barcode.springbootqrcodegeneratorapp.config;

import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.stereotype.Component;

import static com.barcode.springbootqrcodegeneratorapp.constants.QRCodeConstants.PROPERTIES;

@Component
@Data
@NoArgsConstructor
@ConfigurationProperties(prefix = PROPERTIES)
public class PropertiesConfiguration {
    private String fileName;
    private String qrcodeImagepath;
    private String qrcodeMulti;

}
