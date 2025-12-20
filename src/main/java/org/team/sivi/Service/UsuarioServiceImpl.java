package org.team.sivi.Service;

import jakarta.servlet.http.HttpServletResponse;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.http.ResponseCookie;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.team.sivi.Dto.*;
import org.team.sivi.Dto.UsuarioDto.*;
import org.team.sivi.Exception.BadRequestException;
import org.team.sivi.Exception.NotFoundException;
import org.team.sivi.Mapper.UsuarioMapper;
import org.team.sivi.Model.Rol;
import org.team.sivi.Model.Usuario;
import org.team.sivi.Repository.RolRepository;
import org.team.sivi.Repository.UsuarioRepository;
import org.team.sivi.Security.CookieUtils.CookieOnlyUtils;
import org.team.sivi.Security.TokenUtils.AccesTokenUtils;
import org.team.sivi.Security.TokenUtils.RefreshTokenUtils;
import org.team.sivi.UsuarioSpecifications.UsuarioSpecifications;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
public class UsuarioServiceImpl implements UsuarioService {

    private final UsuarioMapper usuarioMapper;
    private final UsuarioRepository usuarioRepository;
    private final RolRepository rolRepository;
    private final PasswordEncoder passwordEncoder;
    private final AuthenticationManager authenticationManager;
    private final AccesTokenUtils accesTokenUtils;
    private final RefreshTokenUtils refreshTokenUtils;
    private final CookieOnlyUtils cookieOnlyUtils;

    public UsuarioServiceImpl(UsuarioMapper usuarioMapper, UsuarioRepository usuarioRepository, RolRepository rolRepository, PasswordEncoder passwordEncoder, AuthenticationManager authenticationManager, AccesTokenUtils accesTokenUtils, RefreshTokenUtils refreshTokenUtils, CookieOnlyUtils cookieOnlyUtils) {
        this.usuarioMapper = usuarioMapper;
        this.usuarioRepository = usuarioRepository;
        this.rolRepository = rolRepository;
        this.passwordEncoder = passwordEncoder;
        this.authenticationManager = authenticationManager;
        this.accesTokenUtils = accesTokenUtils;
        this.refreshTokenUtils = refreshTokenUtils;
        this.cookieOnlyUtils = cookieOnlyUtils;
    }
    //Metodo para crear cuenta del usuario
    @Transactional
    @Override
    public UsuarioCrearCuentaResponseDto crearCuenta(UsuarioCrearCuentaRequestDto crearCuentaRequestDto) throws BadRequestException, NotFoundException {
       //Mapeamos los datos ingresados por el usuario
        Usuario usuario=usuarioMapper.usuarioCrearCuentaRequestDtoToUsuario(crearCuentaRequestDto);
      //Verificamos que el correo que ingresó no tenga una cuenta en el sistema
        if (usuarioRepository.existsUsuarioByCorreo(usuario.getCorreo())){
            throw new BadRequestException("El correo " + usuario.getCorreo() + " ya está registrado. Intente con otro correo electrónico.");
        }
          //Inicializamos una lista de roles
           List<Rol>listaRoles=new ArrayList<>();
    //Recorremos los id de los roles ingresados por el usuario
        for (Long rolId:crearCuentaRequestDto.getListaRol()){
            //buscamos el rol en la bd
           Optional<Rol> rol=rolRepository.findById(rolId);
           //Si existe el rol lo agregamos a la lista
           if (rol.isPresent()){
               Rol rolGet=rol.get();

               listaRoles.add(rolGet);
           }
           //si no existe, lanzamos una excepcion al usuario con un mensaje del motivo
           else {
               throw new NotFoundException("El rol con el id:"+rolId+" no fue encontrado en nuestro sistema");
           }
        }
        //Encriptamos la contraseña del usuario para protegerla
        String passwordEncript=passwordEncoder.encode(usuario.getPassword());
        //setteamos algunos datos del usuario que no fueron mappeados o la contraseña que necesitaba encriptación
        usuario.setPassword(passwordEncript);
        usuario.setActivo(true);
        usuario.setFechaCreacion(LocalDateTime.now());
        usuario.setListaRol(listaRoles);
        //Guardamos la nueva cuenta del usuario en la bd
        usuarioRepository.save(usuario);
        //Retornamos el usuario creado y mapeamos del usuario al dto de respuesta
        return usuarioMapper.usuarioToUsuarioCrearCuentaResponseDto(usuario);
    }

    @Override
    public UsuarioIniciarSesionResponseDto iniciarSesion(HttpServletResponse response, UsuarioIniciarSesionRequestDto usuarioIniciarSesionRequestDto) throws NotFoundException {
        //  Autenticamos al usuario con correo y contraseña
     Authentication authentication= authenticationManager.authenticate(new UsernamePasswordAuthenticationToken
                (usuarioIniciarSesionRequestDto.getCorreo(),usuarioIniciarSesionRequestDto.getPassword()));
    // Guardamos la sesión autenticada en el contexto de seguridad de spring
        SecurityContextHolder.getContext().setAuthentication(authentication);
       //Creamos el accessToken Jwt
        String accessToken= accesTokenUtils.token(authentication);
        //Creamos el refreshToken y el metodo ya lo guarda en la bd
        RefreshTokenResponseDto refreshToken=refreshTokenUtils.crearRefreshToken(authentication.getName());

        //Creamos una CookieOnly para refreshToken para evitar robo de tokens
        cookieOnlyUtils.crearCookieOnly(response,refreshToken.getRefreshTokenId(),refreshToken.getRefreshToken());

       //Retornamos la respuesta al front/postman
        return new UsuarioIniciarSesionResponseDto(accessToken,"Bearer ");
    }

    @Transactional(readOnly = true)
    @Override
    public Page<UsuarioListaResponseDto> obtenerListaUsuarios(Pageable pageable) throws NotFoundException {

        Page<Usuario>listaUsuario=usuarioRepository.findAllByOrderByIdAsc(pageable);

        List<UsuarioListaResponseDto>listaUsuariosDto=new ArrayList<>();

        if (listaUsuario.isEmpty()){

            throw new NotFoundException("No existen usuarios en el sistema");
        }

        for (Usuario usuario:listaUsuario){

            UsuarioListaResponseDto usuarioListaResponseDto=usuarioMapper.usuarioToUsuarioListaResponseDto(usuario);
            listaUsuariosDto.add(usuarioListaResponseDto);
        }

        return new PageImpl<>(listaUsuariosDto,pageable,listaUsuario.getTotalElements());

    }

    @Transactional(readOnly = true)
    @Override
    public Page<UsuarioListaResponseDto> filtrarUsuarios(String nombre, String rol, Boolean activo, Pageable pageable) throws NotFoundException {

        Specification<Usuario>spec=Specification.allOf(UsuarioSpecifications.nombreLike(nombre))
                .and(UsuarioSpecifications.rolEqual(rol))
                .and(UsuarioSpecifications.activoEqual(activo));

        Page<Usuario>usuariosFiltrados=usuarioRepository.findAll(spec,pageable);

        if (usuariosFiltrados.isEmpty()){

            throw new NotFoundException("No se encontraron resultados");
        }

        return usuariosFiltrados.map(usuarioMapper::usuarioToUsuarioListaResponseDto);
    }
 @Transactional
    @Override
    public void eliminarUsuario(Long id) throws NotFoundException, BadRequestException {
        Optional<Usuario>usuario=usuarioRepository.findById(id);

        if (usuario.isPresent()){
            Usuario usuarioGet=usuario.get();

            boolean rolAdmin=false;

          for (Rol rol:usuarioGet.getListaRol() ){

              if (rol.getNombre().equals("ROLE_ADMIN")){
                  rolAdmin=true;
                  break;
              }
            }

            long cantidad=usuarioRepository.countByListaRol_Nombre("ROLE_ADMIN");

            if (rolAdmin && cantidad<=1) {

                throw new BadRequestException("No es posible eliminar este usuario porque es el único con rol de administrador en el sistema.");

            }

            usuarioRepository.deleteById(usuarioGet.getId());

        } else {

           throw new NotFoundException("El usuario con ID " + id + " no existe o ya fue eliminado.");
        }
    }


}
