package co.com.user.api.dto.request;

import lombok.Builder;
import lombok.Getter;

@Getter
@Builder
public class UserUpdateRequestDto {

    private String firstName;
    private String lastName;
    private String document;
    private String email;
    private String phone;
    private String password;
    private Integer locationX;
    private Integer locationY;
}
