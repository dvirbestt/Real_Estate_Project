package com.example.RealEstateSite.controller;

import com.example.RealEstateSite.model.AuthUser;
import com.example.RealEstateSite.model.AuthenticationResponse;
import com.example.RealEstateSite.model.RegistrationRequest;
import com.example.RealEstateSite.model.UserContact;
import com.example.RealEstateSite.service.*;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.validation.Errors;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;

@RestController
@CrossOrigin
@RequestMapping("/user")
public class AuthUserController {

    @Autowired
    private UserContactService userContactService;
    @Autowired
    private AuthUserService authUserService;
    @Autowired
    private AppUserDetailsService appUserDetailsService;
    @Autowired
    private AuthenticationManager authenticationManager;

    @Autowired
    private JwtService jwtUtils;

    @PostMapping("/register")
    public ResponseEntity<?> register(@Valid @RequestBody RegistrationRequest request, Errors errors) {
        System.out.println(1);
        if (errors.hasErrors()) {
            ArrayList<String> errorList = new ArrayList<>();
            errors.getAllErrors().stream().forEach((error -> {
                errorList.add(error.getDefaultMessage());
            }));
            return ResponseEntity.badRequest().body(errorList);
        }

        AuthUser authUser = new AuthUser(request.getUsername(), request.getPassword());
        UserContact userContact = new UserContact(
                request.getUsername(),
                request.getFirstName(),
                request.getLastName(),
                request.getEmail(),
                request.getPhoneNumber()
        );
        if (authUserService.register(authUser)) {
            userContactService.saveUser(userContact);
            return ResponseEntity.ok().body("Registered Successfully");
        }
        return ResponseEntity.badRequest().body("User Name Already Taken");
    }

    @PostMapping("/login")
    public ResponseEntity<?> createAuthenticationToken(@Valid @RequestBody AuthUser authUser, Errors errors) throws Exception {

        if (errors.hasErrors()) {
            ArrayList<String> errorList = new ArrayList<>();
            errors.getAllErrors().stream().forEach((error -> {
                errorList.add(error.getDefaultMessage());
            }));
            return ResponseEntity.badRequest().body(errorList);
        }

        try {
            authenticationManager.authenticate(
                    new UsernamePasswordAuthenticationToken(authUser.getUsername(), authUser.getPassword())

            );

        } catch (BadCredentialsException e) {

            return ResponseEntity.status(401).body("Wrong Username / Password");
        }
        final AppUserDetails userDetails = appUserDetailsService.loadUserByUsername(authUser.getUsername());
        final String jwt = jwtUtils.generateToken(userDetails);
        return ResponseEntity.ok().body(new AuthenticationResponse(jwt, userContactService.getUserDetails(authUser.getUsername())));

    }

}
