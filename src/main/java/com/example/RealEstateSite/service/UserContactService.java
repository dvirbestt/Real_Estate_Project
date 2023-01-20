package com.example.RealEstateSite.service;


import com.example.RealEstateSite.model.UserContact;
import com.example.RealEstateSite.repository.UserContactRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class UserContactService {
    @Autowired
    private UserContactRepository userContactRepository;

    public boolean saveUser(UserContact userContact){
        Optional<UserContact> existingUser = userContactRepository.findUserByUsername(userContact.getUsername());
        if (existingUser.isPresent()){
            return false;
        }
        userContactRepository.save(userContact);
        return true;
    }

    public UserContact getUserDetails(String username){
        return userContactRepository.findUserByUsername(username).get();
    }
}
