package fluffandpaws.webadopcion.security;

import fluffandpaws.webadopcion.models.Usuario;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

public class WebUserDetails implements UserDetails {
    private static final long serialVersionUID = 1L;
    private Usuario usuario;

    public WebUserDetails(Usuario usuario){
        this.usuario = usuario;
    }

    @Override
    public Collection<? extends GrantedAuthority> getAuthorities() {
        List<String> roles = usuario.getRoles();
        List<SimpleGrantedAuthority> authorities = new ArrayList<>();

        for(String role : roles){
            authorities.add(new SimpleGrantedAuthority(role));
        }

        return authorities;
    }

    @Override
    public String getPassword() {
        return usuario.getPass();
    }

    @Override
    public String getUsername() {
        return usuario.getUsername();
    }

    @Override
    public boolean isAccountNonExpired() {
        return true;
    }

    @Override
    public boolean isAccountNonLocked() {
        return true;
    }

    @Override
    public boolean isCredentialsNonExpired() {
        return true;
    }

    @Override
    public boolean isEnabled() {
        return true;
    }

    public String getFullname(){
        return this.usuario.getName() + " " + this.usuario.getLastName();
    }

    public void setFirstName(String name){
        this.usuario.setName(name);
    }

    public void setLastName(String lastname){
        this.usuario.setLastname(lastname);
    }

    public boolean hasRole(String role){
        return usuario.hasRole(role);
    }

}
