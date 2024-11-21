package org.example.login_registration.Impl;

import org.example.login_registration.Entities.User;
import org.example.login_registration.UserRepo;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

@Service
public class UserDetailsServiceImpl implements UserDetailsService {
    UserRepo userRepo;
    @Override
    public UserDetailsImpl loadUserByUsername(String username) throws UsernameNotFoundException {
        User user = userRepo.findByUsername(username)
                .orElseThrow(()->new UsernameNotFoundException("Username not found!"));
        return UserDetailsImpl.build(user);
    }


}
