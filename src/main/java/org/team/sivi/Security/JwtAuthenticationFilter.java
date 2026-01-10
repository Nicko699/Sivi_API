package org.team.sivi.Security;

import jakarta.servlet.FilterChain;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Service;
import org.springframework.util.StringUtils;
import org.springframework.web.filter.OncePerRequestFilter;
import org.team.sivi.Security.TokenUtils.AccesTokenUtils;
import java.io.IOException;

@Service
public class JwtAuthenticationFilter extends OncePerRequestFilter {
    //Realizamos inyección dependencias mediante constructor
    private final CustomUserDetailsService userDetailsService;
    private final AccesTokenUtils accesToken;

    public JwtAuthenticationFilter(CustomUserDetailsService userDetailsService, AccesTokenUtils accesToken) {
        this.userDetailsService = userDetailsService;
        this.accesToken = accesToken;
    }

    //Metodo para obtener el token Jwt de la autorizacion
    private String obtenerBearerToken(HttpServletRequest request){
        //Obtenemos el bearer token de la cabecera authorization
        String bearerToken=request.getHeader("Authorization");
        //Validamos que no sea null o este vacio y que comience con el prefijo bearer mas un espacio
        if (StringUtils.hasText(bearerToken) && bearerToken.startsWith("Bearer ")  ){
            //Retornamos el token desde la posicion numero 7 que es donde empieza el token
            return bearerToken.substring(7);

        }
        //Si no, retornamos null
        return null;
    }
   //Personalizamos el metodo proporcionado
    //Este metodo se encarga de validar el jwt token y además guardar los datos de autenticacion del usuario en el contexto de seguridad
    @Override
    protected void doFilterInternal(HttpServletRequest request, HttpServletResponse response, FilterChain filterChain) throws ServletException, IOException {
       //Obtenemos tl token del metodo obtenerBearerToken
        String token=obtenerBearerToken(request);
      //Validamos el token que no haya sido null, vacio o la firma sea invalida
        if (StringUtils.hasText(token) && accesToken.validarToken(token)){
           //Obtenemos el correo del usuario
            String correo=accesToken.obtenerCorreo(token);
            //Guardamos los datos de autenticacion del usuario en el objeto userDetails
            UserDetails userDetails=userDetailsService.loadUserByUsername(correo);

            // Creamos el objeto de autenticación con los datos del usuario.
            UsernamePasswordAuthenticationToken authenticationToken
                    =new UsernamePasswordAuthenticationToken(userDetails,null,userDetails.getAuthorities());
            // Guardamos el objeto de autenticación en el SecurityContextHolder.
             // los datos del usuario quedan disponibles sin volver a consultar la BD.
            SecurityContextHolder.getContext().setAuthentication(authenticationToken);

        }
        //Permitimos que siga con la ejecucion de otro filtro de seguridad
        filterChain.doFilter(request,response);
    }
}
