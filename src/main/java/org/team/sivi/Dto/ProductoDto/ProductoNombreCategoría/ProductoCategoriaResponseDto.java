package org.team.sivi.Dto.ProductoDto.ProductoNombreCategoría;

public class ProductoCategoriaResponseDto {
    private Long id;
    private String nombre;

    public ProductoCategoriaResponseDto() {
    }

    public ProductoCategoriaResponseDto(Long id, String nombre) {
        this.id = id;
        this.nombre = nombre;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }
}

