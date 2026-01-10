package org.team.sivi.Security.TokenUtils;
import io.jsonwebtoken.Claims;
import io.jsonwebtoken.JwtException;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.security.Keys;
import jakarta.annotation.PostConstruct;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.stereotype.Service;
import org.team.sivi.Model.Rol;
import org.team.sivi.Repository.UsuarioRepository;
import org.team.sivi.Security.CustomUserDetails;

import javax.crypto.SecretKey;
import java.nio.charset.StandardCharsets;
import java.time.Duration;
import java.time.Instant;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

@Service
public class AccesTokenUtils {

    // Inyectamos desde application.properties el valor de la firma de 32 caracteres para el JWT.
    @Value("${spring.security.firma}")
    private String firma;
    //Esta variable es usada para contener la clave secreta para la firmar el JWT
    private SecretKey key;


    //Indicamos que este metodo se va a ejecutar automáticamente después de que Spring haya inyectado los valores.
    //Inicializamos la variable key a partir del string firma

    @PostConstruct
    public void init(){
        // Convertimos la firma en bytes y generamos una clave secreta compatible con HMAC-SHA256, un algoritmo de codificación,
        // usado para la firma
        this.key= Keys.hmacShaKeyFor(firma.getBytes(StandardCharsets.UTF_8));
    }

   //Metodo para crear el accesToken por medio de Authentication
    //Lo usamos para crear por primera vez el accessToken, Cuando el usuario inicia sesión
    public String token(Authentication authentication){
        // Creamos una lista para almacenar los roles del usuario
        List<String>listaRol=new ArrayList<>();
        // Recorremos los GrantedAuthority que vienen de Authentication
        for (GrantedAuthority authority:authentication.getAuthorities()){

            listaRol.add(authority.getAuthority());
        }

        CustomUserDetails userDetails= (CustomUserDetails) authentication.getPrincipal();

        return CrearAccessToken(authentication.getName(),userDetails.getNombreCompleto(),listaRol);
    }
  //Metodo crearAccesToken por medio de los datos correo y Rol
    //Lo usamos para renovar el accessToken cuando se venza
    public String token(String correo,String nombre, List<Rol>listaRoles){

        List<String>listaRol=new ArrayList<>();

        for (Rol rol:listaRoles){

            listaRol.add(rol.getNombre());
        }
        return CrearAccessToken(correo,nombre,listaRol);
    }


    //Creamos un metodo para crear el Token Jwt
    public String CrearAccessToken(String correo, String nombre,List<String>listaRoles){

        // Obtenemos la fecha y hora actual como momento de creación del token
        Instant fechaCreacion=Instant.now();
        Instant fechaExpiracion=fechaCreacion.plus(Duration.ofMinutes(10));

        //Creamos el Jwt token y retornamos
      return  Jwts.builder()
                .subject(correo)//Agregamos el email que es el identificador del usuario
              .claim("nombre",nombre)
                .claim("roles",listaRoles) //Agregamos la lista roles del usuario
                .issuedAt(Date.from(fechaCreacion)) //Agregamos la fecha creacion dle token
                .expiration(Date.from(fechaExpiracion)) //Agregamos la fecha expiracion del token
                .signWith(key)//Agregamos la firma
                .compact();//Terminamos de crearlo

    }

    //Metodo para obtener el correo del usuario por el JWT token creado
    public String obtenerCorreo(String token){
        // Parseamos el token para extraer los claims (información guardada en el JWT)
        Claims claims=Jwts
                .parser() // Iniciamos el parser del token
                .verifyWith(key) // verificamos que el token no haya sido modificado con la firma
                .build() // Construimos el parser
                .parseSignedClaims(token)// Parseamos el token firmado (JWS)
                .getPayload();//Obtenemos los datos del Jwt

        return claims.getSubject();//Retornamos el correo
    }

    //Creamos un metodo para validar el Acces token
    public boolean validarToken(String token){
        //Si el Jwt es valido retornamos true
        try{

            Jwts.parser()
                    .verifyWith(key)
                    .build()
                    .parseSignedClaims(token);
            return true;
        }
        //si no, capturamos la excepcion y retornamos un false
        catch (JwtException e){

            return false;
        }

    }

}
