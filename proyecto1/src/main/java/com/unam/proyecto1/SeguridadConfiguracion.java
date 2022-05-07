package com.unam.proyecto1;


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

    @Override
    protected void configure(HttpSecurity http) throws Exception {

             http.csrf().disable().authorizeRequests()
                .antMatchers("/", "/usuarios/crea", "/registro").permitAll()
                .antMatchers("/usuarios/").hasRole("USUARIO")
                //.antMatchers("/manejoAdmin/").hasRole("ADMIN")
                .anyRequest().authenticated()
                .and()
                .formLogin().loginPage("/")
                .loginProcessingUrl("/").usernameParameter("email").passwordParameter("password")
                .defaultSuccessUrl("/usuarios/").permitAll()
                //.defaultSuccessUrl("/manejoAdmin/").permitAll()
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
