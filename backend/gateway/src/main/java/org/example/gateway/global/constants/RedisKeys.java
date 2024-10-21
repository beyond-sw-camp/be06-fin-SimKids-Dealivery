package org.example.gateway.global.constants;


import lombok.Getter;

@Getter
public enum RedisKeys {
    USER_REFRESH_TOKEN("refresh_token:user:"),
    COMPANY_REFRESH_TOKEN("refresh_token:company:");

    private final String key;

    RedisKeys(String key) {
        this.key = key;
    }
}
