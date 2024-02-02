package co.com.user.r2dbc.mapper;

import co.com.user.model.user.config.ErrorDictionary;
import co.com.user.r2dbc.entities.ErrorDictionaryEntity;
import lombok.experimental.UtilityClass;

@UtilityClass
public class ErrorDictionaryMapper {

    public static ErrorDictionary toModel(ErrorDictionaryEntity errorDictionaryEntity) {
        return ErrorDictionary.builder()
                .id(errorDictionaryEntity.getId())
                .httpStatus(errorDictionaryEntity.getHttpStatus())
                .message(errorDictionaryEntity.getMessage())
                .messageEn(errorDictionaryEntity.getMessageEn())
                .messageEs(errorDictionaryEntity.getMessageEs())
                .build();
    }
}
