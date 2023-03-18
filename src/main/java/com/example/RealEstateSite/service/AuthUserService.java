package com.example.RealEstateSite.service;

import com.example.RealEstateSite.model.AuthUser;
import com.example.RealEstateSite.model.Role;
import com.example.RealEstateSite.model.UserContact;
import com.example.RealEstateSite.repository.AuthUserRepository;
import com.example.RealEstateSite.repository.UserContactRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;
import java.util.Collections;
import java.util.Optional;

@Service
public class AuthUserService {

    @Autowired
    private AuthUserRepository authUserRepository;
    @Autowired
    private UserContactRepository userContactRepository;

    @Autowired UserContactService userContactService;

    public ResponseEntity<?> register(AuthUser authUser, UserContact userContact){

        Optional<AuthUser> exists = authUserRepository.findByUsername(authUser.getUsername());
        Optional<UserContact> exists1 =userContactRepository.findUserByEmail(userContact.getEmail());

        if (exists.isPresent()){
            return ResponseEntity.badRequest().body(Collections.singleton("User Name Already Taken"));
        }
        if (exists1.isPresent()){
            return ResponseEntity.badRequest().body(Collections.singleton("Email Already Taken"));
        }
        if (authUser.getRole()==null){
            authUser.setRole(Role.USER);
        }
        authUser.setPassword(new BCryptPasswordEncoder().encode(authUser.getPassword()));
        authUserRepository.save(authUser);
        userContactService.saveUser(userContact);
        return ResponseEntity.ok().body(Collections.singleton("User Created Successfully"));
    }


}
