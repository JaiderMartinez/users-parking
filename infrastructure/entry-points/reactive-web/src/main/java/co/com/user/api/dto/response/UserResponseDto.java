package co.com.user.api.dto.response;

import lombok.Builder;
import lombok.Getter;

@Getter
@Builder
public class UserResponseDto {

    private Long id;
    private String firstName;
    private String lastName;
    private String document;
    private String email;
    private String phone;
    private String password;
    private String locationX;
    private String locationY;
}
