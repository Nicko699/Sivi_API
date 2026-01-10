package org.team.sivi.Exception.Dto;

import org.springframework.http.HttpStatus;

import java.time.LocalDateTime;
//Creamos una clase para crear nuestro propio mensaje de respuesta de la excepcion
public class Mensaje {
    private int estatus;
    private HttpStatus error;
    private String mensaje;
    private LocalDateTime fecha;

    public Mensaje() {
    }

    public Mensaje(int estatus, HttpStatus error, String mensaje, LocalDateTime fecha) {
        this.estatus = estatus;
        this.error = error;
        this.mensaje = mensaje;
        this.fecha = fecha;
    }

    public int getEstatus() {
        return estatus;
    }

    public void setEstatus(int estatus) {
        this.estatus = estatus;
    }

    public HttpStatus getError() {
        return error;
    }

    public void setError(HttpStatus error) {
        this.error = error;
    }

    public String getMensaje() {
        return mensaje;
    }

    public void setMensaje(String mensaje) {
        this.mensaje = mensaje;
    }

    public LocalDateTime getFecha() {
        return fecha;
    }

    public void setFecha(LocalDateTime fecha) {
        this.fecha = fecha;
    }
}
