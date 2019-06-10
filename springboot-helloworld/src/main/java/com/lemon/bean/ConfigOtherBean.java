package com.lemon.bean;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Primary;
import org.springframework.context.annotation.PropertySource;

/**
 * Created by lemon
 */
@Configuration
@ConfigurationProperties(prefix = "other")
@PropertySource(value = "classpath:app.yml")
@Primary
public class ConfigOtherBean {
    // 必须使用 Value 注解,否则会一直是 null
    @Value("${color}")
    private String color;
    @Value("${rgb}")
    private String rgb;
    @Value("${address}")
    private String address;

    public String getColor() {
        return color;
    }

    public void setColor(String color) {
        this.color = color;
    }

    public String getRgb() {
        return rgb;
    }

    public void setRgb(String rgb) {
        this.rgb = rgb;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    @Override
    public String toString() {
        return "ConfigOtherBean{" +
                "color='" + color + '\'' +
                ", rgb='" + rgb + '\'' +
                ", address='" + address + '\'' +
                '}';
    }
}
