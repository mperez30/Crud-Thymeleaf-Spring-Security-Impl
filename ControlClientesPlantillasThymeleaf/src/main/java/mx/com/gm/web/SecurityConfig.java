package mx.com.gm.web;

import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;

@Configuration
@EnableWebSecurity
public class SecurityConfig extends WebSecurityConfigurerAdapter{
    
   
   // autenticacion de url
   
    @Override
    protected void configure(AuthenticationManagerBuilder auth) throws Exception{
        auth.inMemoryAuthentication()
                .withUser("admin")
                    .password("{noop}admin")
                    .roles("ADMIN","USER")
                .and()
                .withUser("user")
                    .password("{noop}admin")
                    .roles("USER")
                ;
    }
    
    // autorizacion de url
    // se autoriza a un usuario el acceso a alguna vista pagina o pantalla (son lo mismo
        @Override
    protected void configure(HttpSecurity http) throws Exception{
        http.authorizeHttpRequests().antMatchers("/editar/**","/agregar/**","/eliminar/**")
           .hasRole("ADMIN")
           .antMatchers("/")
           .hasAnyRole("USER","ADMIN")
           .and()
           .formLogin()
           .loginPage("/login")
           .and()
           .exceptionHandling().accessDeniedPage("/errores/403");
    }
    
    
    
    
}
