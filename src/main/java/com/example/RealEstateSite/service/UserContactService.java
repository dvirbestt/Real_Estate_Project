package com.example.RealEstateSite.service;

import com.example.RealEstateSite.model.UserContact;
import com.example.RealEstateSite.repository.UserContactRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;


@Service
public class UserContactService {
    @Autowired
    private UserContactRepository userContactRepository;

    public void saveUser(UserContact userContact){
        userContactRepository.save(userContact);
    }

    public UserContact getUserDetails(String username){
        return userContactRepository.findUserByUsername(username).get();
    }
}
