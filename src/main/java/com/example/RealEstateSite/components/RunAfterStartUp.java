package com.example.RealEstateSite.components;

import com.example.RealEstateSite.model.*;
import com.example.RealEstateSite.repository.AuthUserRepository;
import com.example.RealEstateSite.service.AuthUserService;
import com.example.RealEstateSite.service.PostService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.context.event.ApplicationReadyEvent;
import org.springframework.context.event.EventListener;
import org.springframework.stereotype.Component;
import java.util.Optional;

@Component
public class RunAfterStartUp {
    @Autowired
    private AuthUserRepository authUserRepository;
    @Autowired
    private AuthUserService authUserService;
    @Autowired
    private PostService postService;

    @EventListener(ApplicationReadyEvent.class)
    public void runAfter(){
        Optional<AuthUser> testUser = authUserRepository.findByUsername("admin1");

        if (testUser.isEmpty()){
                AuthUser authUser = new AuthUser("admin1","0502222222Db!", Role.ADMIN);
                UserContact userContact = new UserContact(authUser.getUsername(),"admin1","admin1","admin1@gmail.com","0502222222");
                authUserService.register(authUser,userContact);

                AuthUser user1 = new AuthUser("user1","0502222222Db!");
                UserContact userContact1= new UserContact(user1.getUsername(), "Jake","Peralta","Jake@gmail.com","0503333333");
                authUserService.register(user1,userContact1);

                Post post = new Post("user1",userContact1.getFirstName(),"Ha'alon 56","Dimona",3,1,100,"Demo description",userContact1.getPhoneNumber(),userContact1.getEmail(), PropertyType.RENT,1000);
                Post post1 = new Post("user1",userContact1.getFirstName(),"Ha'maapil 1055","Dimona",4,2,120,"Demo description",userContact1.getPhoneNumber(),userContact1.getEmail(), PropertyType.SALE,1200000);
                postService.savePost(post);
                postService.savePost(post1);
        }
    }
}
