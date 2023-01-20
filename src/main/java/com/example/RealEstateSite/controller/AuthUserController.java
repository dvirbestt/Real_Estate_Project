package com.example.RealEstateSite.controller;

import com.example.RealEstateSite.model.AuthUser;
import com.example.RealEstateSite.model.AuthenticationResponse;
import com.example.RealEstateSite.model.RegistrationRequest;
import com.example.RealEstateSite.model.UserContact;
import com.example.RealEstateSite.service.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.web.bind.annotation.*;

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
    public ResponseEntity<?> register(@RequestBody RegistrationRequest request){
        AuthUser authUser = new AuthUser(request.getUsername(),request.getPassword());
        UserContact userContact = new UserContact(
                request.getUsername(),
                request.getFirstName(),
                request.getLastName(),
                request.getEmail(),
                request.getPhoneNumber()
        );
        if (authUserService.register(authUser)){
            userContactService.saveUser(userContact);
            return ResponseEntity.ok().body("Registered Successfully");
        }
        return ResponseEntity.badRequest().body("User Name Already Taken");
    }

    @PostMapping("/login")
    public ResponseEntity<?> createAuthenticationToken(@RequestBody AuthUser authUser) throws Exception{
        System.out.println(1);
        try {
            authenticationManager.authenticate(
                    new UsernamePasswordAuthenticationToken(authUser.getUsername(),authUser.getPassword())

            );

        }catch (BadCredentialsException e){

            throw new Exception("Incorrect User/password",e);

        }
        final AppUserDetails userDetails = appUserDetailsService.loadUserByUsername(authUser.getUsername());
        final String jwt = jwtUtils.generateToken(userDetails);
        return ResponseEntity.ok().body(new AuthenticationResponse(jwt, userContactService.getUserDetails(authUser.getUsername())));

    }

    @GetMapping("/test")
    public String test(){
        return "Test Successfully";
    }

    @GetMapping("/testAdmin")
    public String testAdmin(){
        return "Test Admin";
    }
}
