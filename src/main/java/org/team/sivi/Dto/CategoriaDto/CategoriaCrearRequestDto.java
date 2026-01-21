package org.team.sivi.Dto.CategoriaDto;

public class CategoriaCrearRequestDto {
    private String nombre;
    private String descripcion;

    public CategoriaCrearRequestDto() {}

    public String getNombre() { return nombre; }
    public void setNombre(String nombre) { this.nombre = nombre; }

    public String getDescripcion() { return descripcion; }
    public void setDescripcion(String descripcion) { this.descripcion = descripcion; }
}