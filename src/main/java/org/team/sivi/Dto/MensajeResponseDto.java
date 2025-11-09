package org.team.sivi.Dto;

public class MensajeResponseDto {

    private String mensaje;

    public MensajeResponseDto() {
    }

    public MensajeResponseDto(String mensaje) {
        this.mensaje = mensaje;
    }

    public String getMensaje() {
        return mensaje;
    }

    public void setMensaje(String mensaje) {
        this.mensaje = mensaje;
    }
}
