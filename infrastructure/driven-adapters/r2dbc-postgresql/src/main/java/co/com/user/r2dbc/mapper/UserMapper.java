package co.com.user.r2dbc.mapper;

import co.com.user.model.user.User;
import co.com.user.r2dbc.entities.UserEntity;
import lombok.experimental.UtilityClass;

@UtilityClass
public class UserMapper {
    
    public static User toModel(UserEntity userEntity) {
        return User.builder()
                .id(userEntity.getId())
                .firstName(userEntity.getFirstName())
                .lastName(userEntity.getLastName())
                .document(userEntity.getDocument())
                .email(userEntity.getEmail())
                .phone(userEntity.getPhone())
                .password(userEntity.getPassword())
                .locationX(userEntity.getLocationX())
                .locationY(userEntity.getLocationY())
                .build();
    }

    public static UserEntity toEntity(User user) {
        return UserEntity.builder()
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
