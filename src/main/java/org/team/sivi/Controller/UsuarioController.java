package org.team.sivi.Controller;

import jakarta.servlet.http.HttpServletResponse;
import jakarta.validation.Valid;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;
import org.team.sivi.Dto.UsuarioDto.*;
import org.team.sivi.Exception.BadRequestException;
import org.team.sivi.Exception.NotFoundException;
import org.team.sivi.Service.UsuarioService;

import java.net.URI;
import java.util.HashMap;
import java.util.Map;

@RestController
@RequestMapping("/usuario")
public class UsuarioController {

    private  final UsuarioService usuarioService;

    public UsuarioController(UsuarioService usuarioService) {
        this.usuarioService = usuarioService;
    }

    // Creamos una nueva cuenta de usuario
    // Si sale bien devuelve el estado 201 (Created) y en el cuerpo de la respuesta los datos del usuario creado.
    // También en la cabecera dejamos la URL del nuevo usuario creado.
    @PreAuthorize("hasRole('ADMIN')")
    @PostMapping("/crearCuenta")
    public ResponseEntity<UsuarioCrearCuentaResponseDto> crearCuenta(@Valid @RequestBody UsuarioCrearCuentaRequestDto crearCuentaRequestDto) throws BadRequestException, NotFoundException{

        UsuarioCrearCuentaResponseDto usuarioCrearCuenta=usuarioService.crearCuenta(crearCuentaRequestDto);

        URI location= ServletUriComponentsBuilder
                .fromCurrentRequest().path("/{id}")
                .buildAndExpand(usuarioCrearCuenta.getId())
                .toUri();

        return ResponseEntity.created(location).body(usuarioCrearCuenta);
    }

    // Endpoint para Iniciar sesión con correo y contraseña.
    // Si las credenciales son correctas devuelve un estado 200 (OK) con el mensaje y
    // junto con el token de acceso y refresh.
    @PostMapping("/iniciarSesion")
    public ResponseEntity<UsuarioIniciarSesionResponseDto> iniciarSesion(HttpServletResponse response, @Valid @RequestBody  UsuarioIniciarSesionRequestDto usuarioIniciarSesionRequestDto) throws NotFoundException{

        UsuarioIniciarSesionResponseDto  iniciarSesionResponse=usuarioService.iniciarSesion(response,usuarioIniciarSesionRequestDto);

        return ResponseEntity.ok(iniciarSesionResponse);
    }

  //Endpoint para el ingreso de cualquier usuario sin autenticarse
    @GetMapping("/sinCredenciales")
    public ResponseEntity<Map<String,String>>permitirSinCredenciales(){

        Map<String,String>mensaje=new HashMap<>();

        mensaje.put("Mensaje","bienvenido a sivi");

      return   ResponseEntity.ok(mensaje);
    }
    //Endpoint para el ingreso a solo rol ADMIN
    @PreAuthorize("hasRole('ADMIN')")
    @GetMapping("/soloAdmin")
    public ResponseEntity<Map<String,String>>permitirSoloAdmin(){

        Map<String,String>mensaje=new HashMap<>();

        mensaje.put("Mensaje","bienvenido Admin");

        return   ResponseEntity.ok(mensaje);
    }

    //Endpoint para el ingreso de cualquier usuario autenticado
    @PreAuthorize("hasAnyRole('ADMIN','VEND')")
    @GetMapping("/todosAutenticados")
    public ResponseEntity<Map<String,String>>permitirTodosAutenticados(){

        Map<String,String>mensaje=new HashMap<>();

        mensaje.put("Mensaje","bienvenidos usuarios autenticados");

        return   ResponseEntity.ok(mensaje);
    }

    //Endpoint para el ingreso a solo rol USER
    @PreAuthorize("hasRole('VEND')")
    @GetMapping("/soloUser")
    public ResponseEntity<Map<String,String>>permitirSoloUser(){

        Map<String,String>mensaje=new HashMap<>();

        mensaje.put("Mensaje","bienvenido User");

        return   ResponseEntity.ok(mensaje);
    }


    @DeleteMapping("/eliminarUsuario/{id}")
    public ResponseEntity<Map<String,String>> eliminarUsuario(@PathVariable  Long id) throws NotFoundException, BadRequestException{

        Map<String,String>mensaje=new HashMap<>();

        usuarioService.eliminarUsuario(id);

        mensaje.put("mensaje","tu usuario fue eliminado");

        return ResponseEntity.ok(mensaje);

    }

    @PreAuthorize("hasRole('ADMIN')")
    @GetMapping("/obtenerUsuarios")
    public ResponseEntity<Page<UsuarioListaResponseDto>> obtenerListaUsuarios(Pageable pageable) throws NotFoundException{

        Page<UsuarioListaResponseDto>listaUsuarios=usuarioService.obtenerListaUsuarios(pageable);

        return ResponseEntity.ok(listaUsuarios);
    }

    @PreAuthorize("hasRole('ADMIN')")
    @GetMapping("/ObtenerUsuariosFiltrados")
    public ResponseEntity<Page<UsuarioListaResponseDto>>filtrarUsuarios(@RequestParam(required = false)  String nombre, @RequestParam(required = false) String rol,@RequestParam(required = false)  Boolean activo,Pageable pageable) throws NotFoundException{

        Page<UsuarioListaResponseDto>usuariosFiltrados=usuarioService.filtrarUsuarios(nombre,rol,activo,pageable);

        return ResponseEntity.ok(usuariosFiltrados);
    }



}
