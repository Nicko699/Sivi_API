package org.team.sivi.Dto.VentaDto;

import jakarta.validation.constraints.NotBlank;

public class VentaCambiarEstadoRequestDto {
    @NotBlank(message = "El estado no puede estar vacío")
    private String estado;

    public VentaCambiarEstadoRequestDto() {
    }

    public VentaCambiarEstadoRequestDto(String estado) {
        this.estado = estado;
    }

    public String getEstado() {
        return estado;
    }

    public void setEstado(String estado) {
        this.estado = estado;
    }
}
