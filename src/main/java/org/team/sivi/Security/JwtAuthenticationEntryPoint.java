package org.team.sivi.Security;

import com.fasterxml.jackson.databind.ObjectMapper;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import org.springframework.http.MediaType;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.web.AuthenticationEntryPoint;
import org.springframework.stereotype.Component;
import java.io.IOException;
import java.time.LocalDateTime;
import java.time.LocalTime;
import java.util.HashMap;
import java.util.Map;

//Indicamos que va a ser una clase de tipo componente
@Component
public class JwtAuthenticationEntryPoint implements AuthenticationEntryPoint {
    //Sobreescribimos el metodo proporcionado por AuthenticationEntryPoint
    @Override
    public void commence(HttpServletRequest request, HttpServletResponse response, AuthenticationException authException) throws IOException, ServletException {
       //Cambiamos el estado respuesta a 401
        response.setStatus(HttpServletResponse.SC_UNAUTHORIZED);
        //Indicamos que el contenido de la respuesta va a ser en formato Json
        response.setContentType(MediaType.APPLICATION_JSON_VALUE);
       //Inicializamos un map para personalizar el mensaje de la respuesta
        Map<String,String>mensaje=new HashMap<>();
        //Personalizamos el mensaje
        mensaje.put("estado","401");
        mensaje.put("fecha", String.valueOf(LocalDateTime.now()));
        mensaje.put("mensaje",authException.getMessage());
       //Convertimos el mensaje a un Json
        String json= new ObjectMapper().writeValueAsString(mensaje);
        //Asignamos el mensaje a la respuesta
        response.getWriter().write(json);
    }
}
