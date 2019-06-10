package com.lemon.model;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Profile;
import org.springframework.stereotype.Component;

/**
 * Created by lemon
 */
@Component
@Profile("dev")
public class DevDBConnector implements DBConnector {
    @Value("${my.name}")
    private String message;

    @Override
    public String getMessage() {
        return "开发," + message;
    }
}
