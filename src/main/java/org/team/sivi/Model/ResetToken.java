package org.team.sivi.Model;

import jakarta.persistence.*;

import java.time.Instant;
//Entidad resetToken
@Entity
public class ResetToken {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String resetToken;
    private String resetTokenHash;
    private Instant fechaCreacion;
    private Instant fechaExpiracion;
    private boolean valido;

  //Relación 1 a 1 con usuario
    @OneToOne()
    private Usuario usuario;

    public ResetToken() {
    }

    public ResetToken(Long id, String resetToken, String resetTokenHash, Instant fechaCreacion, Instant fechaExpiracion, boolean valido, Usuario usuario) {
        this.id = id;
        this.resetToken = resetToken;
        this.resetTokenHash = resetTokenHash;
        this.fechaCreacion = fechaCreacion;
        this.fechaExpiracion = fechaExpiracion;
        this.valido = valido;
        this.usuario = usuario;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getResetToken() {
        return resetToken;
    }


    public void setResetToken(String resetToken) {
        this.resetToken = resetToken;
    }

    public String getResetTokenHash() {
        return resetTokenHash;
    }

    public void setResetTokenHash(String resetTokenHash) {
        this.resetTokenHash = resetTokenHash;
    }

    public Instant getFechaCreacion() {
        return fechaCreacion;
    }

    public void setFechaCreacion(Instant fechaCreacion) {
        this.fechaCreacion = fechaCreacion;
    }

    public Instant getFechaExpiracion() {
        return fechaExpiracion;
    }

    public void setFechaExpiracion(Instant fechaExpiracion) {
        this.fechaExpiracion = fechaExpiracion;
    }

    public boolean isValido() {
        return valido;
    }

    public void setValido(boolean valido) {
        this.valido = valido;
    }

    public Usuario getUsuario() {
        return usuario;
    }

    public void setUsuario(Usuario usuario) {
        this.usuario = usuario;
    }
}
