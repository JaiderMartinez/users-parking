package co.com.user.api.dto.request;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class UserRequestDto {

    private String firstName;
    private String lastName;
    private String document;
    private String email;
    private String phone;
    private String password;
    private String locationX;
    private String locationY;
}
