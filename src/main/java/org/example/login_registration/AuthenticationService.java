package org.example.login_registration;

import lombok.RequiredArgsConstructor;
import org.example.login_registration.DTO.JwtAuthenticationResponse;
import org.example.login_registration.DTO.SignInRequest;
import org.example.login_registration.DTO.SignUpRequest;
import org.example.login_registration.Entities.ERole;
import org.example.login_registration.Entities.User;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class AuthenticationService {
    private final UserService userService;
    private final JwtService jwtService;
    private final PasswordEncoder passwordEncoder;
    private final AuthenticationManager authenticationManager;

    public JwtAuthenticationResponse signUp(SignUpRequest request){
        var user = User.builder()
                .username(request.getUsername())
                .email(request.getEmail())
                .password(passwordEncoder.encode(request.getPassword()))
                .role(ERole.USER_ROLE)
                .isverifiedartist(false)
                .build();
        userService.createUser(user);
        var jwt = jwtService.generateToken(user);
        return new JwtAuthenticationResponse(jwt);
    }
    public JwtAuthenticationResponse signIn(SignInRequest request){
        System.out.println("Attempting login for:" + request.getUsername());
        authenticationManager.authenticate(new UsernamePasswordAuthenticationToken(
                request.getUsername(),
                request.getPassword()
        ));
        var user = userService
                .userDetailsService()
                .loadUserByUsername(request.getUsername());
        var jwt = jwtService.generateToken(user);
        return new JwtAuthenticationResponse(jwt);
    }
}
