package pe.edu.upc.moderneducation.security;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import pe.edu.upc.moderneducation.model.entity.User;

public class MyUserDetails implements UserDetails {

    private static final long serialVersionUID = 1L;
    private User user;

    public MyUserDetails(User user){
        super();
        this.user = user;
    }

    @Override
    public Collection<? extends GrantedAuthority> getAuthorities() {
        List<GrantedAuthority> grantedAuthorities = new ArrayList<>();
        
        this.user.getAuthorities().forEach(authority -> {
            GrantedAuthority grantedAuthority = new SimpleGrantedAuthority(authority.getAuthority());
            grantedAuthorities.add(grantedAuthority);
        });
        return grantedAuthorities;
    }

    @Override
    public String getPassword() {
        return this.user.getPassword();
    }

    @Override
    public String getUsername() {
        return this.user.getUsername();
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
        return this.user.isEnable();
    }
    
    public String getFirstName(){
        return this.user.getFirstName();
    }

    public User getUser(){
        return this.user;
    }

    public String getProfileImage(){
        return this.user.getProfileImage();
    }

    public String getFullName(){
        return this.user.getFirstName() + ' ' + this.user.getLastName();
    }

    public Integer getId(){
        return this.user.getId();
    }
}
