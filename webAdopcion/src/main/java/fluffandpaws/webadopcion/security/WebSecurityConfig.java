package fluffandpaws.webadopcion.security;

import java.security.SecureRandom;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;

@Configuration
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

/*

@Configuration
public class WebSecurityConfig {

    @Autowired
    RepositoryUserDetailsService userDetailsService;

    private BCryptPasswordEncoder bCryptEncoder;

    @Bean
    public SecurityFilterChain configure (HttpSecurity httpSecurity) throws Exception {
        AuthenticationManagerBuilder authMngBuilder = httpSecurity.getSharedObject(AuthenticationManagerBuilder.class);
        authMngBuilder.userDetailsService(userDetailsService).passwordEncoder(bCryptEncoder);

        // Get AuthenticationManager
        AuthenticationManager authenticationManager = authMngBuilder.build();


        // Public pages
        httpSecurity.authorizeHttpRequests().requestMatchers("/", "/login", "/loginerror", "/logout").permitAll();

        // Private pages
        httpSecurity.authorizeHttpRequests().requestMatchers("/newbook").hasAnyRole("USER");
        httpSecurity.authorizeHttpRequests().requestMatchers("/editbook/*").hasAnyRole("USER");
        httpSecurity.authorizeHttpRequests().requestMatchers("/removebook/*").hasAnyRole("ADMIN");
        //http.authorizeHttpRequests().requestMatchers

        // Login form
        httpSecurity.formLogin().loginPage("/login");
        httpSecurity.formLogin().usernameParameter("username");
        httpSecurity.formLogin().passwordParameter("password");
        httpSecurity.formLogin().defaultSuccessUrl("/");
        httpSecurity.formLogin().failureUrl("/loginerror");

        // Logout
        httpSecurity.logout().logoutUrl("/logout");
        httpSecurity.logout().logoutSuccessUrl("/");

        return (web) -> {web.ignoring().antMAtchers("");}
    }

    @Bean
    public PasswordEncoder passwordEncoder() {
        return new BCryptPasswordEncoder(10, new SecureRandom());
    }

    @Bean
    public SecurityFilterChain filterChain(HttpSecurity http) throws Exception {
        http.csrf()
                .disable()
                .authorizeRequests()
                .antMatchers(HttpMethod.DELETE)
                .hasRole("ADMIN")
                .antMatchers("/admin/**")
                .hasAnyRole("ADMIN")
                .antMatchers("/user/**")
                .hasAnyRole("USER", "ADMIN")
                .antMatchers("/login/**")
                .anonymous()
                .anyRequest()
                .authenticated()
                .and()
                .httpBasic()
                .and()
                .sessionManagement()
                .sessionCreationPolicy(SessionCreationPolicy.STATELESS);

        return http.build();
    }

    @Override
    protected void configure(AuthenticationManagerBuilder auth) throws Exception {
        auth.userDetailsService(userDetailsService).passwordEncoder(passwordEncoder());
    }


 */

    /*
    ------------------------------------------------------------------------------
                                    DEPRECATED
    ------------------------------------------------------------------------------


    @Override
    protected void configure(AuthenticationManagerBuilder auth) throws Exception {

        auth.userDetailsService(userDetailsService).passwordEncoder(passwordEncoder());
    }

    @Override
    protected void configure(HttpSecurity http) throws Exception {

        // Public pages
        http.authorizeRequests().antMatchers("/").permitAll();
        http.authorizeRequests().antMatchers("/login").permitAll();
        http.authorizeRequests().antMatchers("/loginerror").permitAll();
        http.authorizeRequests().antMatchers("/logout").permitAll();

        // Private pages
        http.authorizeRequests().antMatchers("/newbook").hasAnyRole("USER");
        http.authorizeRequests().antMatchers("/editbook/*").hasAnyRole("USER");
        http.authorizeRequests().antMatchers("/removebook/*").hasAnyRole("ADMIN");

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
    */
//}