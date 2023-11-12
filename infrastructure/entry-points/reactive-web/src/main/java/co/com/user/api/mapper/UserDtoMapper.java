package co.com.user.api.mapper;

import co.com.user.api.dto.request.UserRequestDto;
import co.com.user.api.dto.response.UserResponseDto;
import co.com.user.model.user.User;
import lombok.experimental.UtilityClass;

@UtilityClass
public class UserDtoMapper {

    public static User toModel(UserRequestDto userRequestDto) {
        return User.builder()
                .firstName(userRequestDto.getFirstName())
                .lastName(userRequestDto.getLastName())
                .document(userRequestDto.getDocument())
                .email(userRequestDto.getEmail())
                .phone(userRequestDto.getPhone())
                .password(userRequestDto.getPassword())
                .locationX(userRequestDto.getLocationX())
                .locationY(userRequestDto.getLocationY())
                .build();
    }

    public static UserResponseDto toResponse(User user) {
        return UserResponseDto.builder()
                .id(user.getId())
                .firstName(user.getFirstName())
                .lastName(user.getLastName())
                .document(user.getDocument())
                .email(user.getEmail())
                .phone(user.getPhone())
                .password(user.getPassword())
                .locationX(user.getLocationX())
                .locationY(user.getLocationY())
                .build();
    }
}
