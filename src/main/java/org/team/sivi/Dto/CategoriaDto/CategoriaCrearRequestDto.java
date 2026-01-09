package org.team.sivi.Dto.CategoriaDto;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;

public record CategoriaCrearRequestDto(
        @NotBlank(message = "El nombre es obligatorio")
        @Size(min = 3, max = 50, message = "El nombre debe tener entre 3 y 50 caracteres")
        String nombre,

        String descripcion
) {}