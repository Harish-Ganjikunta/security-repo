package com.login.register.service;

import com.login.register.entities.Users;
import com.login.register.repositories.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import java.util.Set;
import java.util.stream.Collectors;

@Service
public class CustomUserDetailsService implements UserDetailsService {


    @Autowired
    private UserRepository userRepository;

    /*public CustomUserDetailsService(UserRepository userRepository) {
        this.userRepository = userRepository;
    }*/


    @Override
    public UserDetails loadUserByUsername(String userNameOrEmail) throws UsernameNotFoundException {
        Users user = userRepository.findByUsernameOrEmail(userNameOrEmail,userNameOrEmail).orElseThrow(()-> new UsernameNotFoundException("User is not found with the given details"));
        Set<GrantedAuthority> authorities = user.getRoles().stream().map((role) ->new SimpleGrantedAuthority(role.getName())).collect(Collectors.toSet());
        return new User(user.getEmail(),user.getPassword(),authorities);
    }
}
