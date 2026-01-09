package org.team.sivi.Dto.CategoriaDto;

import java.time.LocalDateTime;

public record CategoriaResponseDto(
        Long id,
        String nombre,
        String descripcion,
        Boolean activo,
        LocalDateTime fechaCreacion
) {}