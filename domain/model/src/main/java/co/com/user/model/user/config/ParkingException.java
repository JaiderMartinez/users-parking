package co.com.user.model.user.config;

import lombok.Getter;

@Getter
public class ParkingException extends RuntimeException {

    private final ErrorCode error;

    public ParkingException(ErrorCode errorCode) {
        super(errorCode.getLog());
        this.error = errorCode;
    }
}
