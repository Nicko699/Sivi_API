package org.team.sivi.Security;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpMethod;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.config.annotation.authentication.configuration.AuthenticationConfiguration;
import org.springframework.security.config.annotation.method.configuration.EnableMethodSecurity;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configurers.AbstractHttpConfigurer;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;
//Indicamos que va a ser una clase solo para la configuracion
@Configuration
@EnableWebSecurity
@EnableMethodSecurity
public class SecurityConfig {

    //Realizamos inyección dependencias mediante constructor
    private final JwtAuthenticationFilter jwtAuthenticationFilter;
    private final JwtAuthenticationEntryPoint jwtAuthenticationEntryPoint;
    private final JwtAccesDeniedHandler jwtAccesDeniedHandler;

    public SecurityConfig(JwtAuthenticationFilter jwtAuthenticationFilter, JwtAuthenticationEntryPoint jwtAuthenticationEntryPoint, JwtAccesDeniedHandler jwtAccesDeniedHandler) {
        this.jwtAuthenticationFilter = jwtAuthenticationFilter;
        this.jwtAuthenticationEntryPoint = jwtAuthenticationEntryPoint;
        this.jwtAccesDeniedHandler = jwtAccesDeniedHandler;
    }

   //bean que se encarga de autenticar al usuario mediante UserDetails
    @Bean
    public AuthenticationManager authenticationManager(AuthenticationConfiguration Configuration) throws Exception {
        // Devuelve el AuthenticationManager que Spring Security utiliza para autenticar credenciales
        return Configuration.getAuthenticationManager();
    }

    //Bean que se encarga de hashear(encriptar) contraseñas o codigos
    @Bean
    public PasswordEncoder passwordEncoder(){

        return new BCryptPasswordEncoder();
    }
    // Bean que configura toda la seguridad web de Spring Security
    @Bean
    public SecurityFilterChain securityFilterChain(HttpSecurity httpSecurity) throws Exception {

        httpSecurity.csrf(AbstractHttpConfigurer::disable) // Desactivamos el CSRF ya que se usa JWT y no lo tradicional son sesiones
                .formLogin(AbstractHttpConfigurer::disable) //Desactivamos el formulario por defecto que trae spring ya que no lo vamos usar
                .httpBasic(AbstractHttpConfigurer::disable)//Desactivamos la autenticacion http
                .exceptionHandling(exception->exception.authenticationEntryPoint
                        (jwtAuthenticationEntryPoint).accessDeniedHandler(jwtAccesDeniedHandler))//Configuramos las excepciones, usuario no autenticado y usuario sin permisos
                .sessionManagement(session->session.sessionCreationPolicy(SessionCreationPolicy.STATELESS))//Configuramos nuestro aplicacion a Stateles, Va  usar tokens
                .authorizeHttpRequests(authorize->authorize.requestMatchers(HttpMethod.POST,"/usuario/crearCuenta","/usuario/iniciarSesion").permitAll()
                        .requestMatchers(HttpMethod.GET,"/usuario/sinCredenciales").permitAll().anyRequest().authenticated());//Configuramos las rutas de los endpoints
        //Le decimos a spring que ejecute nuestro filtro personalizado primero a cualquier otro que el tenga
        httpSecurity.addFilterBefore(jwtAuthenticationFilter, UsernamePasswordAuthenticationFilter.class);
    //Retornamos la configuracion y construimos.
      return httpSecurity.build();
    }
}
