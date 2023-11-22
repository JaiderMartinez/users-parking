package co.com.user.api.dto.request;

import jakarta.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class UserCoordinatesRequestDto {

    @NotNull
    private Integer locationX;
    @NotNull
    private Integer locationY;
}
