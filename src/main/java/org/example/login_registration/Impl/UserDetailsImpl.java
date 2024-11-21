package org.example.login_registration.Impl;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.example.login_registration.Entities.User;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import java.util.Collection;
import java.util.List;

@NoArgsConstructor
@Getter
@Setter
public class UserDetailsImpl implements UserDetails {
private Long id;
private String username;
private String email;
private String password;
private boolean isverifiedartist;
private  Collection<? extends GrantedAuthority> Authorities;

    public UserDetailsImpl(  Collection<? extends GrantedAuthority> authorities, Long id, String username, String email, String password, boolean isverifiedartist) {
        this.Authorities= authorities;
        this.id = id;
        this.username = username;
        this.email = email;
        this.password = password;
        this.isverifiedartist = isverifiedartist;
    }
    public static UserDetailsImpl build(User user){
        List<GrantedAuthority> authorityList = List.of(new SimpleGrantedAuthority(user.getRole().name()));
        return new UserDetailsImpl(
                authorityList,
                user.getId(),
                user.getUsername(),
                user.getEmail(),
                user.getPassword(),
                user.getIsverifiedartist()
        );
    }

    @Override
    public Collection<? extends GrantedAuthority> getAuthorities() {
        return Authorities;
    }

    @Override
    public String getPassword() {
        return password;
    }

    @Override
    public String getUsername() {
        return username;
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
}
