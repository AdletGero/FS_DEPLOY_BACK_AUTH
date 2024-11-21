package org.example.login_registration;

import lombok.RequiredArgsConstructor;
import org.example.login_registration.Entities.ERole;
import org.example.login_registration.Entities.User;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class UserService {
    private final UserRepo userRepo;
    public User saveUser(User user){
        return userRepo.save(user);
    }
    public User createUser(User user){
        if(userRepo.existsByUsername(user.getUsername())){
            throw new RuntimeException("This username is taken!");
        }
        if(userRepo.existsByEmail(user.getEmail())){
            throw new RuntimeException("This email is taken!");
        }
        return saveUser(user);
    }
    public User getByUsername(String username){
        return userRepo.findByUsername(username).orElseThrow(()-> new UsernameNotFoundException("Username not found!"));
    }
    public UserDetailsService userDetailsService(){
        return this::getByUsername;
    }
    public User getCurrentUser(){
        var username = SecurityContextHolder.getContext().getAuthentication().getName();
        return getByUsername(username);
    }
    @Deprecated
    public void getAdmin(){
        var user = getCurrentUser();
        user.setRole(ERole.ADMIN_ROLE);
        saveUser(user);
    }

}
