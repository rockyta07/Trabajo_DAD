package fluffandpaws.webadopcion.security;

import java.security.SecureRandom;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.authentication.logout.LogoutHandler;

@Configuration
@EnableWebSecurity
public class WebSecurityConfig extends WebSecurityConfigurerAdapter{
    @Autowired
    private RepositoryUserDetailsService userDetailsService;


    @Bean
    public PasswordEncoder passwordEncoder() {
        return new BCryptPasswordEncoder(10, new SecureRandom());
    }

    @Override
    protected void configure(AuthenticationManagerBuilder auth) throws Exception {
        auth.userDetailsService(userDetailsService).passwordEncoder(passwordEncoder());
    }

    @Override
    @Bean
    public AuthenticationManager authenticationManagerBean() throws Exception {
        return super.authenticationManagerBean();
    }
    @Override
    protected void configure(HttpSecurity http) throws Exception {

        // Public pages
        http.authorizeRequests().antMatchers("/").permitAll();
        http.authorizeRequests().antMatchers("/login").permitAll();
        http.authorizeRequests().antMatchers("/loginerror").permitAll();
        http.authorizeRequests().antMatchers("/logout").permitAll();

        // Private pages Animales
        http.authorizeRequests().antMatchers("/crearAnimal").hasAnyRole("USER");
        http.authorizeRequests().antMatchers("/editAnimal/*").hasAnyRole("USER");
        http.authorizeRequests().antMatchers("/borrarAnimal/*").hasAnyRole("ADMIN");
        //Private pages Mensajes
        http.authorizeRequests().antMatchers("/crearMensaje").hasAnyRole("USER");
        http.authorizeRequests().antMatchers("/editMensaje/*").hasAnyRole("USER");
        http.authorizeRequests().antMatchers("/borrarMensaje/*").hasAnyRole("ADMIN");
        //Private pages Protectora
        http.authorizeRequests().antMatchers("/crearProtectora").hasAnyRole("USER");
        http.authorizeRequests().antMatchers("/editProtectora/*").hasAnyRole("USER");
        http.authorizeRequests().antMatchers("/borrarProtectora/*").hasAnyRole("ADMIN");


        // Login form
        http.formLogin().loginPage("/login");
        http.formLogin().usernameParameter("username");
        http.formLogin().passwordParameter("password");
        http.formLogin().defaultSuccessUrl("/");
        http.formLogin().failureUrl("/loginerror");

        // Logout
        http.logout().logoutUrl("/logout");
        http.logout().logoutSuccessUrl("/");
    }
}
