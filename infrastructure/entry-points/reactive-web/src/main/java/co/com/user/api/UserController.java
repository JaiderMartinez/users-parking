package co.com.user.api;

import co.com.user.api.dto.request.UserRequestDto;
import co.com.user.api.dto.response.UserResponseDto;
import co.com.user.api.mapper.UserDtoMapper;
import co.com.user.usecase.user.UserUseCase;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PatchMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import reactor.core.publisher.Mono;

@RestController
@RequestMapping("/users")
@RequiredArgsConstructor
public class UserController {

    private final UserUseCase userUseCase;

    @PostMapping
    public Mono<UserResponseDto> save(@RequestBody UserRequestDto userRequestDto) {
        return userUseCase.save(UserDtoMapper.toModel(userRequestDto))
                .map(UserDtoMapper::toResponse);
    }

    @GetMapping("/{idUser}")
    public Mono<UserResponseDto> findById(@PathVariable(name = "idUser") Long idUser) {
        return userUseCase.findById(idUser)
                .map(UserDtoMapper::toResponse);
    }

    @PutMapping("/{idUser}")
    public Mono<UserResponseDto> updateUser(
            @PathVariable(name = "idUser") Long idUser,
            @RequestBody UserRequestDto userRequestDto) {
        return userUseCase.update(UserDtoMapper.toModel(userRequestDto), idUser)
                .map(UserDtoMapper::toResponse);
    }

    @PatchMapping("/{idUser}")
    public Mono<UserResponseDto> updateUserCoordinates(
            @PathVariable(name = "idUser") Long idUser,
            @RequestBody UserRequestDto userRequestDto) {
        return userUseCase.updateUserCoordinates(UserDtoMapper.toModel(userRequestDto), idUser)
                .map(UserDtoMapper::toResponse);
    }
}
