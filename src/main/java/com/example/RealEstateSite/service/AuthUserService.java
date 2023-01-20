package com.example.RealEstateSite.service;

import com.example.RealEstateSite.model.AuthUser;
import com.example.RealEstateSite.model.Role;
import com.example.RealEstateSite.repository.AuthUserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class AuthUserService {

    @Autowired
    private AuthUserRepository authUserRepository;

    public boolean register(AuthUser authUser){

        Optional<AuthUser> exists = authUserRepository.findByUsername(authUser.getUsername());

        if (exists.isEmpty()){
            authUser.setRole(Role.USER);
            authUser.setPassword(new BCryptPasswordEncoder().encode(authUser.getPassword()));
            authUserRepository.save(authUser);
            return true;
        }
        return false;
    }
}
