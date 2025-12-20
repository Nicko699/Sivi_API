package org.team.sivi.Security;

import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.team.sivi.Model.Rol;
import org.team.sivi.Model.Usuario;
import org.team.sivi.Repository.UsuarioRepository;
import java.util.ArrayList;
import java.util.Collection;
import java.util.List;
import java.util.Optional;

//Creamos una clase que va a implementar UserDetailsService
//Definimos esta clase como service por que va a tener lógica
@Service
public class CustomUserDetailsService implements UserDetailsService {

    //Realizamos inyección dependencias mediante constructor
    private final UsuarioRepository usuarioRepository;

    public CustomUserDetailsService(UsuarioRepository usuarioRepository) {
        this.usuarioRepository = usuarioRepository;
    }

    // Creamos un metodo que recorre la lista de roles del usuario y los convierte en una coleccion
   // de objetos GrantedAuthority que representan los permisos o roles
    // que Spring Security utiliza para manejar la autenticación y autorización.
    public Collection<GrantedAuthority>authorities(List<Rol>listaRol){
        //Instanciamos una nueva coleccion de GrantedAuthority
        Collection<GrantedAuthority>authorities=new ArrayList<>();
        //Recorremos la lista de roles del usuario que vienen por el parametro
        for (Rol rol:listaRol){
           //Convertimos el rol del usuario en una autoridad que spring pueda entender con la implementacion simpleGrantedAuthority
            GrantedAuthority authority=new SimpleGrantedAuthority(rol.getNombre());
            //Guardamos la autoridad en una lista de GrantedAuthority
           authorities.add(authority);

        }

        //Retornamos la lista con las authoridades
        return authorities;
    }
    //Sobreescribimos el metodo proporcionado por UserDetailsService
    //Indicamos que este metodo solo se encarga de leer datos, no de modificar con transactional(readOnly=true)
    @Transactional(readOnly = true)
    @Override
    public UserDetails loadUserByUsername(String email) throws UsernameNotFoundException {
      //Buscamos al usuario en la bd por el correo
        Optional<Usuario>usuario=usuarioRepository.findUsuarioByCorreo(email);
         //Comprobamos si existe el usuario en la Bd
        if (usuario.isPresent()){
            Usuario usuarioGet=usuario.get();
            //Retornamos un objeto User que implementa la interfaz UserDetails de Spring Security.
        // Este objeto contiene la información esencial del usuario para poder validar que es el y poder autenticarse
            return new CustomUserDetails(usuarioGet.getCorreo()
                    ,usuarioGet.getNombre(),usuarioGet.getPassword(),usuarioGet.isActivo(),authorities(usuarioGet.getListaRol()));

        }
        //Si no, lanzamos una excepción, indicando que el usuario no se encuentra en el sistema
        else {

            throw new UsernameNotFoundException("El usuario no se encuentra registrado en SIVI");
        }
    }
}
