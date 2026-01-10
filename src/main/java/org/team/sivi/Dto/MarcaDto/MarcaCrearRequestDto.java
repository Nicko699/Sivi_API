package org.team.sivi.Dto.MarcaDto;

import jakarta.validation.constraints.NotBlank;

public class MarcaCrearRequestDto {
    @NotBlank(message = "El nombre es obligatorio")
    private String nombre;
    private String descripcion;

    public MarcaCrearRequestDto() {
    }

    public MarcaCrearRequestDto(String nombre, String descripcion) {
        this.nombre = nombre;
        this.descripcion = descripcion;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public String getDescripcion() {
        return descripcion;
    }

    public void setDescripcion(String descripcion) {
        this.descripcion = descripcion;
    }
}
