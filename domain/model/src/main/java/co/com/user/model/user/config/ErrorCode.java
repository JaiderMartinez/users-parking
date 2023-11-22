package co.com.user.model.user.config;

import lombok.Getter;

@Getter
public enum ErrorCode {
    B400000("B400-000", "Bad Request"),
    N000000("N00-000", "Not found");

    private final String code;
    private final String log;

    ErrorCode(String code, String log) {
        this.code = code;
        this.log = log;
    }
}
