package org.team.sivi.Service;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.scheduling.annotation.Async;
import org.springframework.stereotype.Service;
import org.team.sivi.Dto.ResetTokenResponseDto;
import org.team.sivi.Exception.BadRequestException;
import org.team.sivi.Model.Usuario;
import org.team.sivi.Security.TokenUtils.ResetTokenUtils;

@Service
public class CorreoServiceImpl implements CorreoService {

    private final ResetTokenUtils resetTokenUtils;
    private final JavaMailSender javaMailSender;

    public CorreoServiceImpl(ResetTokenUtils resetTokenUtils, JavaMailSender javaMailSender) {
        this.resetTokenUtils = resetTokenUtils;
        this.javaMailSender = javaMailSender;
    }

    @Value("${spring.mail.username}")
    private String correoRemitente;

    @Value("${spring.url.recuperar-password}")
    private String url;

    @Async
    public void enviarCorreoRecuperacionAsync(Usuario usuario) throws BadRequestException {

        ResetTokenResponseDto resetTokenResponseDto = resetTokenUtils.crearResetToken(usuario);

        String urlCompleta = url
                + "?resetTokenId=" + resetTokenResponseDto.getResetTokenId()
                + "&resetToken=" + resetTokenResponseDto.getResetToken();

        SimpleMailMessage mensaje = new SimpleMailMessage();
        mensaje.setFrom(correoRemitente);
        mensaje.setTo(usuario.getCorreo());
        mensaje.setSubject("Recuperación de contraseña");
        mensaje.setText(
                "Hola " + usuario.getNombre() + ",\n\n"
                        + "Recibimos una solicitud para restablecer tu contraseña. "
                        + "Haz clic en el siguiente enlace para continuar:\n\n"
                        + urlCompleta + "\n\n"
                        + "Este enlace expirará en 10 minutos.\n\n"
                        + "Si no solicitaste este cambio, puedes ignorar este mensaje.\n\n"
                        + "Por favor, no respondas a este correo, ya que fue enviado automáticamente.\n\n"
                        + "Atentamente,\n"
                        + "El equipo de SIVI"
        );

        javaMailSender.send(mensaje);
    }
}
