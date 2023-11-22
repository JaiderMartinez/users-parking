package co.com.user.api.mapper;

import co.com.user.api.dto.response.ResponseErrorDto;
import co.com.user.model.user.config.ErrorDictionary;
import lombok.experimental.UtilityClass;

@UtilityClass
public class ResponseErrorMapper {

    public static ResponseErrorDto toResponseErrorDto(ErrorDictionary errorDictionary) {
        return ResponseErrorDto.builder()
                .code(errorDictionary.getId())
                .messageEs(errorDictionary.getMessageEs())
                .messageEn(errorDictionary.getMessageEn())
                .build();
    }
}
