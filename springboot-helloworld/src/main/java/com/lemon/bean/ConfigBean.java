package com.lemon.bean;

import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.context.annotation.Configuration;

/**
 * Created by lemon
 */
@Configuration
@ConfigurationProperties(prefix = "my")
public class ConfigBean {
    private String name;
    private String age;
    private int number;
    private String uuid;
    private String other;
    private String profiles;

    public String getProfiles() {
        return profiles;
    }

    public void setProfiles(String profiles) {
        this.profiles = profiles;
    }

    public String getOther() {
        return other;
    }

    public void setOther(String other) {
        this.other = other;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getAge() {
        return age;
    }

    public void setAge(String age) {
        this.age = age;
    }

    public int getNumber() {
        return number;
    }

    public void setNumber(int number) {
        this.number = number;
    }

    public String getUuid() {
        return uuid;
    }

    public void setUuid(String uuid) {
        this.uuid = uuid;
    }

    @Override
    public String toString() {
        return "ConfigBean{" +
                "name='" + name + '\'' +
                ", age='" + age + '\'' +
                ", number=" + number +
                ", uuid='" + uuid + '\'' +
                ", profiles='" + profiles + '\'' +
                ", other='" + other + '\'' +
                '}';
    }
}
