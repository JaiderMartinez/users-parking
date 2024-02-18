package co.com.user.api.dto.request;

import jakarta.validation.constraints.NotNull;

/**
 * Mayor seguridad: Los records son clases finales,
 * lo que significa que no se pueden heredar ni modificar.
 * Esto puede ayudar a prevenir errores y garantizar la integridad de los datos.
 *
 * Mejor rendimiento: Los records se compilan a código nativo,
 * lo que puede mejorar el rendimiento en comparación
 * con las clases generadas por Lombok.
 */
public record UserCoordinatesRequestDto (
    @NotNull Integer locationX,
    @NotNull Integer locationY
) {}
