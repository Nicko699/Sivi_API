package org.team.sivi.Security;

import com.fasterxml.jackson.databind.ObjectMapper;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import org.springframework.http.MediaType;
import org.springframework.security.access.AccessDeniedException;
import org.springframework.security.web.access.AccessDeniedHandler;
import org.springframework.stereotype.Component;

import java.io.IOException;
import java.time.LocalDateTime;
import java.util.HashMap;
import java.util.Map;

//Indicamos que va a ser una clase de tipo componente
@Component
public class JwtAccesDeniedHandler implements AccessDeniedHandler {

    //Sobreescribimos el metodo proporcionado por AccessDeniedHandler
    @Override
    public void handle(HttpServletRequest request, HttpServletResponse response, AccessDeniedException accessDeniedException) throws IOException, ServletException {
        //Cambiamos el estado respuesta a 403
        response.setStatus(HttpServletResponse.SC_FORBIDDEN);
        //Indicamos que el contenido de la respuesta va a ser en formato Json
        response.setContentType(MediaType.APPLICATION_JSON_VALUE);
        //Inicializamos un map para personalizar el mensaje de la respuesta
        Map<String,String>mensaje=new HashMap<>();
        //Personalizamos el mensaje
        mensaje.put("estado","403");
        mensaje.put("fecha", String.valueOf(LocalDateTime.now()));
        mensaje.put("mensaje","No puedes acceder a este recurso. El acceso está restringido a solo personal autorizado");
        //Convertimos el mensaje a un Json
        String json=new ObjectMapper().writeValueAsString(mensaje);
        //Asignamos el mensaje a la respuesta
        response.getWriter().write(json);
    }
}
