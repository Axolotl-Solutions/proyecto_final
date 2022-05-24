package com.unam.proyecto1;


import com.unam.proyecto1.controlador.LoginSuccessHandler;
import com.unam.proyecto1.servicio.seguridad.DetalleUsuarioServicio;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;

@Configuration
@EnableWebSecurity
public class SeguridadConfiguracion extends WebSecurityConfigurerAdapter {

    @Autowired
    private DetalleUsuarioServicio detalleUsuarioServicio;

    @Autowired
    private LoginSuccessHandler loginSuccessHandler;

    @Override
    protected void configure(HttpSecurity http) throws Exception {
<<<<<<< HEAD
             http.csrf().disable().authorizeRequests()
                .antMatchers("/registro", "/registro/crea").permitAll()
                .antMatchers("/", "/inicio", "/usuarios/").hasAnyAuthority("ROLE_ENTRENADOR", "ROLE_COMPETIDOR", "ROLE_JUEZ")
                                .antMatchers("/admins/", "inicioAdmin").hasAnyAuthority("ROLE_ADMIN")
=======
        http.csrf().disable().authorizeRequests()
                .antMatchers("/","/usuarios/","/inicio","/registro").hasRole("ADMIN")
>>>>>>> cae55f833a96b06d38b497b5a97bdfddff969079
                .anyRequest().authenticated()
                .and()
                .formLogin().loginPage("/")
                .loginProcessingUrl("/").usernameParameter("email").passwordParameter("password")
                     .successHandler(loginSuccessHandler)
                     .permitAll()
                .and()
                .logout()// logout configuration
                .logoutUrl("/salir")
                .deleteCookies("JSESSIONID")
                .invalidateHttpSession(true)
                .logoutSuccessUrl("/").permitAll();
    }



    @Autowired
    public void configureGlobal(AuthenticationManagerBuilder auth) throws Exception {
        BCryptPasswordEncoder passwordEncoder = new BCryptPasswordEncoder();
        auth.userDetailsService((UserDetailsService) detalleUsuarioServicio).passwordEncoder(passwordEncoder);
    }
}
