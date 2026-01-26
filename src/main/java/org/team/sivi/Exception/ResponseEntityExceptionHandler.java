package org.team.sivi.Exception;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.http.converter.HttpMessageNotReadableException;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.team.sivi.Exception.Dto.Mensaje;
import java.time.LocalDateTime;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

//Usamos controller advice para manejar todas las excepciones global de nuestro sistema en un solo lugar
//Con esa anotacion, spring ya sabe que esta clase se encarga de las excepciones
@ControllerAdvice
public class ResponseEntityExceptionHandler {

    // Manejamos la excepcion cuando no se encuentra un recurso
    // Devolvemos un mensaje personalizado
    //usamos ExceptionHandler para atrapar la excepcion
    @ExceptionHandler(NotFoundException.class)
    public ResponseEntity<Mensaje> notFoundException(NotFoundException exception){

        Mensaje mensaje=new Mensaje(404, HttpStatus.NOT_FOUND,exception.getMessage(), LocalDateTime.now());

        return ResponseEntity.status(HttpStatus.NOT_FOUND).body(mensaje);
    }

    // Manejamos la excepcion cuando el usuario se equivoqe al digitar o no escriba nada en un campo rquerido
    // Devolvemos un mensaje personalizado
    //usamos ExceptionHandler para atrapar la excepcion
    @ExceptionHandler(MethodArgumentNotValidException.class)
    public ResponseEntity<Map<String,String>>methodArgumentNotValidException(MethodArgumentNotValidException exception){

        Map<String,String>mensaje=new HashMap<>();

        List<FieldError>listaErrores=exception.getBindingResult().getFieldErrors();

        for (FieldError error:listaErrores){

            String field=error.getField();
            String mensajeError=error.getDefaultMessage();

            mensaje.put(field,mensajeError);
        }

       return  ResponseEntity.status(HttpStatus.BAD_REQUEST).body(mensaje);
    }

    // Manejamos la excepcion cuando el usuario haga una peticion inapropiada
    // Devolvemos un mensaje personalizado
    //usamos ExceptionHandler para atrapar la excepcion
    @ExceptionHandler(BadRequestException.class)
    public ResponseEntity<Mensaje>badRequestException(BadRequestException exception){

        Mensaje mensaje=new Mensaje(400,HttpStatus.BAD_REQUEST,exception.getMessage(),LocalDateTime.now());

        return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(mensaje);
    }

    @ExceptionHandler(UnauthorizedException.class)
    public ResponseEntity<Mensaje>unauthorizedException(UnauthorizedException exception){

        Mensaje mensajeError=new Mensaje(401 ,HttpStatus.UNAUTHORIZED,exception.getMessage(),LocalDateTime.now());

        return ResponseEntity.status(HttpStatus.UNAUTHORIZED).body(mensajeError);

    }

//Excepcion de los Enum si viene con otro nombre desde el front o null
    @ExceptionHandler(HttpMessageNotReadableException.class)
    public ResponseEntity<Mensaje>httpMessageNotReadableException(HttpMessageNotReadableException exception){

        Mensaje mensaje=new Mensaje(400,HttpStatus.BAD_REQUEST,exception.getMessage(),LocalDateTime.now());

        return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(mensaje);
    }


}
