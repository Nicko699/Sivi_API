package org.team.sivi.Dto.LoteDto;

public class LoteProductoCrearResponseDto {
    private String codigoBarras;
    private String nombre;

    public LoteProductoCrearResponseDto() {
    }

    public LoteProductoCrearResponseDto(String codigoBarras, String nombre) {
        this.codigoBarras = codigoBarras;
        this.nombre = nombre;
    }

    public String getCodigoBarras() {
        return codigoBarras;
    }

    public void setCodigoBarras(String codigoBarras) {
        this.codigoBarras = codigoBarras;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }
}
