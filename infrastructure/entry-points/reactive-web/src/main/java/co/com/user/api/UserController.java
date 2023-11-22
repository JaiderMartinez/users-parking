package co.com.user.api;

import co.com.user.api.dto.request.UserCoordinatesRequestDto;
import co.com.user.api.dto.request.UserRequestDto;
import co.com.user.api.dto.request.UserUpdateRequestDto;
import co.com.user.api.dto.response.UserResponseDto;
import co.com.user.api.mapper.UserDtoMapper;
import co.com.user.usecase.UserUseCase;
import io.swagger.v3.oas.annotations.OpenAPIDefinition;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.Parameter;
import io.swagger.v3.oas.annotations.info.Info;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import jakarta.validation.Valid;
import jakarta.validation.constraints.NotNull;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PatchMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;
import reactor.core.publisher.Mono;

@RestController
@RequestMapping(path = "/users")
@RequiredArgsConstructor
@Validated
@OpenAPIDefinition(
        info = @Info(
                title = "Documentación de la API del microservicio usuarios",
                version = "1.0",
                description = "Esta API permite registrar nuevos usuarios en el sistema, " +
                        "validando previamente si quien intenta hacer el registro cumple los requisitos."
        )
)
public class UserController {

    private final UserUseCase userUseCase;

    @Operation(
            summary = "Crear un nuevo usuario",
            operationId = "crear usuario",
            description = "Crear usuario para la app de parqueaderos"
    )
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Usuario creado correctamente",
                    content = @Content(mediaType = "application/json",
                    schema = @Schema(implementation = UserRequestDto.class))),
            @ApiResponse(responseCode = "400", description = "Solicitud incorrecta. Verifique los datos enviados.",
                    content = @Content),
            @ApiResponse(responseCode = "404", description = "Usuario no encontrado.",
                    content = @Content),
            @ApiResponse(responseCode = "500", description = "Error interno del servidor.",
                    content = @Content)
    })
    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public Mono<UserResponseDto> save(
            @Parameter(name = "userRequestDto",
                    description = "Este parámetro representa la implementación de la clase UserRequestDto, " +
                            "la cual tiene atributos de tipo String: firstName, lastName, document, email, phone, password" +
                            "y tipo Integer locationX, locationY.",
                    schema = @Schema(implementation = UserRequestDto.class))
            @Valid @RequestBody UserRequestDto userRequestDto) {
        return userUseCase.save(UserDtoMapper.toModel(userRequestDto))
                .map(UserDtoMapper::toResponse);
    }

    @GetMapping("/{idUser}")
    public Mono<UserResponseDto> findById(@NotNull @PathVariable(name = "idUser") Long idUser) {
        return userUseCase.findById(idUser)
                .map(UserDtoMapper::toResponse);
    }

    @PutMapping("/{idUser}")
    public Mono<UserResponseDto> updateUser(
            @NotNull @PathVariable(name = "idUser") Long idUser,
            @RequestBody UserUpdateRequestDto userUpdateRequestDto) {
        return userUseCase.update(UserDtoMapper.toModel(userUpdateRequestDto), idUser)
                .map(UserDtoMapper::toResponse);
    }

    @PatchMapping("/{idUser}")
    public Mono<UserResponseDto> updateUserCoordinates(
            @NotNull @PathVariable(name = "idUser") Long idUser,
            @RequestBody UserCoordinatesRequestDto userCoordinatesRequestDto) {
        return userUseCase.updateUserCoordinates(
                UserDtoMapper.userCoordinatesRequestDtoToModel(userCoordinatesRequestDto), idUser)
                .map(UserDtoMapper::toResponse);
    }
}
