package org.team.sivi.Dto.CategoriaDto;
import java.time.LocalDateTime;

public class CategoriaResponseDto {
    private Long id;
    private String nombre;
    private String descripcion;
    private Boolean activo;
    private LocalDateTime fechaCreacion;

    public CategoriaResponseDto() {}

    // Getters y Setters (obligatorios para que MapStruct funcione)
    public Long getId() { return id; }
    public void setId(Long id) { this.id = id; }
    public String getNombre() { return nombre; }
    public void setNombre(String nombre) { this.nombre = nombre; }
    public String getDescripcion() { return descripcion; }
    public void setDescripcion(String descripcion) { this.descripcion = descripcion; }
    public Boolean getActivo() { return activo; }
    public void setActivo(Boolean activo) { this.activo = activo; }
    public LocalDateTime getFechaCreacion() { return fechaCreacion; }
    public void setFechaCreacion(LocalDateTime fechaCreacion) { this.fechaCreacion = fechaCreacion; }
}