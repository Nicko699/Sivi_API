package org.team.sivi.Service;

import org.team.sivi.Exception.BadRequestException;
import org.team.sivi.Model.Usuario;

public interface CorreoService {
    public void enviarCorreoRecuperacionAsync(Usuario usuario) throws BadRequestException;
}
