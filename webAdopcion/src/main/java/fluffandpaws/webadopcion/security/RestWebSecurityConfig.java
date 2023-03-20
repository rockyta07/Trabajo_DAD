package fluffandpaws.webadopcion.security;

import fluffandpaws.webadopcion.security.jwt.JwtRequestFilter;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.annotation.Order;
import org.springframework.http.HttpMethod;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;

@Configuration
@Order(1) //Si no establecemos orden da error porq usa el 100 que ya esta ocupado
public class RestWebSecurityConfig extends WebSecurityConfigurerAdapter {
    @Autowired
    private UserDetailsService userDetailsService;

    @Autowired
    private JwtRequestFilter jwtRequestFilter;

    @Autowired
    private PasswordEncoder passwordEncoder;

    @Override
    protected void configure(AuthenticationManagerBuilder auth) throws Exception{
        auth.userDetailsService(userDetailsService).passwordEncoder(passwordEncoder);
    }

    @Bean
    @Override
    public AuthenticationManager authenticationManager() throws Exception{
        return super.authenticationManager();
    }

    @Override
    protected void configure(HttpSecurity httpSecurity) throws Exception {
        httpSecurity.antMatcher("/api/**");

        //URLS privadas
        httpSecurity.authorizeRequests().antMatchers(HttpMethod.POST, "/api/Animales/**");
        httpSecurity.authorizeRequests().antMatchers(HttpMethod.PUT, "/api/Animales/**");
        httpSecurity.authorizeRequests().antMatchers(HttpMethod.DELETE, "/api/Animales/**");

        httpSecurity.authorizeRequests().antMatchers(HttpMethod.POST, "/api/Protectoras/**");
        httpSecurity.authorizeRequests().antMatchers(HttpMethod.PUT, "/api/Protectoras/**");
        httpSecurity.authorizeRequests().antMatchers(HttpMethod.DELETE, "/api/Protectoras/**");

        httpSecurity.authorizeRequests().antMatchers(HttpMethod.POST, "/api/Usuarios/**");
        httpSecurity.authorizeRequests().antMatchers(HttpMethod.PUT, "/api/Usuarios/**");
        httpSecurity.authorizeRequests().antMatchers(HttpMethod.DELETE, "/api/Usuarios/**");

        httpSecurity.authorizeRequests().antMatchers(HttpMethod.POST, "/api/Mensajes/**");
        httpSecurity.authorizeRequests().antMatchers(HttpMethod.PUT, "/api/Mensajes/**");
        httpSecurity.authorizeRequests().antMatchers(HttpMethod.DELETE, "/api/Mensajes/**");

        // URLS publicas -> el resto
        httpSecurity.authorizeRequests().anyRequest().permitAll();

        //Otra config:
        httpSecurity.csrf().disable();
        httpSecurity.httpBasic().disable(); //Asi no funciona el http
        httpSecurity.formLogin().disable(); //Para poner el nuestro propio
        httpSecurity.sessionManagement().sessionCreationPolicy(SessionCreationPolicy.STATELESS); //ya que el jwt es stateless
        httpSecurity.addFilterBefore(jwtRequestFilter, UsernamePasswordAuthenticationFilter.class);
    }
}
