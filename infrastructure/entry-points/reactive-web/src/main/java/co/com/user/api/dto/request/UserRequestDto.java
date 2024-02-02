package co.com.user.api.dto.request;

import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Pattern;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class UserRequestDto {

    @NotBlank
    private String firstName;
    @NotBlank
    private String lastName;
    @NotBlank
    @Pattern(regexp = "^[0-9]{1,10}$")
    private String document;
    @Email
    private String email;
    @NotBlank
    @Pattern(regexp = "^(\\+[0-9]{1,3})?[0-9]{10}$")
    private String phone;
    @NotBlank
    private String password;
    @NotNull
    private Integer locationX;
    @NotNull
    private Integer locationY;
}
