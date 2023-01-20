package com.example.RealEstateSite.service;

import com.example.RealEstateSite.model.AuthUser;
import com.example.RealEstateSite.repository.AuthUserRepository;
import com.example.RealEstateSite.repository.UserContactRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class AppUserDetailsService implements UserDetailsService {
    @Autowired
    private AuthUserRepository authUserRepository;



    @Override
    public AppUserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        Optional<AuthUser> authUser = authUserRepository.findByUsername(username);
        if (authUser.isPresent()){
            return new AppUserDetails(authUser.get());
        }
        throw new UsernameNotFoundException("User Not Found");
    }
}
