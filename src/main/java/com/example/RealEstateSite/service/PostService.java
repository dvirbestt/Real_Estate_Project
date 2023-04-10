package com.example.RealEstateSite.service;

import com.example.RealEstateSite.model.*;
import com.example.RealEstateSite.repository.PostRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;


@Service
public class PostService {
    private String folder = "/Users/dvirbest/Downloads/RealEstateSite/src/main/resources/Images/";
    @Autowired
    private AppUserDetailsService appUserDetailsService;

    @Autowired
    private PostRepository postRepository;

    @Autowired
    private JwtService jwtService;


    public List<Post> findByCity(String city){
        return postRepository.findByCity(city);
    }

    public Post savePost(Post post){
        return postRepository.save(post);
    }

    public List<String> getCityNames(){
        return postRepository.getNames();
    }


    public List<Post> getRand(){
        return postRepository.getRand();
    }

    public Post getById(String id){
        Optional<Post> post = postRepository.findById(id);
        return post.orElse(null);
    }

    public List<Post> searchFilter(SearchRequest sr){
        if (sr.hasAll()){
            return postRepository.getByAll(sr.getCity(),sr.getNumOfRooms(),sr.getPrice(),
                    sr.getPropertyType());
        }
        if (sr.hasCityRoomsAndType()){
            return postRepository.getByCityRoomsAndType(sr.getCity(), sr.getNumOfRooms(), sr.getPropertyType());
        }
        if (sr.hasCityPriceAndType()){
            return postRepository.getByCityPriceAndType(sr.getCity(),sr.getPrice(),sr.getPropertyType());
        }
        if (sr.hasCityRoomsAndPrice()){
            return postRepository.getByCityRoomsAndPrice(sr.getCity(), sr.getNumOfRooms(), sr.getPrice());
        }
        if (sr.hasCityAndRooms()){
            return postRepository.findByCityAndNumOfRooms(sr.getCity(), sr.getNumOfRooms());
        }
        if (sr.hasCityAndType()){
            return postRepository.getByCityAndType(sr.getCity(), sr.getPropertyType());
        }
        if (sr.hasCityAndPrice()){
            return postRepository.getByCityAndPrice(sr.getCity(),sr.getPrice());
        }
        if (sr.getCity() != null ){
            return findByCity(sr.getCity());
        }
        return null;
    }

    public List<Post> showMyPosts(String author){
        return postRepository.showMyPosts(author);
    }

    public boolean deletePost(DeletePostRequest deletePostRequest){
        try {
            AppUserDetails appUserDetails = appUserDetailsService.loadUserByUsername(deletePostRequest.getUsername());
            if (appUserDetails != null && new BCryptPasswordEncoder().matches(deletePostRequest.getPassword(), appUserDetails.getPassword())){
                postRepository.deleteById(deletePostRequest.getId());
                return true;
            }
        }catch (Exception e){
            return false;
        }
        return false;
    }

    public Post updatePost(UpdateRequest updateRequest){
        Optional<Post> isPostExist = postRepository.findById(updateRequest.getPost().getId());

        if (isPostExist.isPresent()) {
            AppUserDetails userDetails = appUserDetailsService.loadUserByUsername(jwtService.extractUsername(updateRequest.getJwt()));
            if (isPostExist.get().getAuthor().equals(userDetails.getUsername()) ){
               ;
                return postRepository.save(updateRequest.getPost());
            }
        }
        return null;
    }

    public boolean adminDeletePost(Post post){
        try {
            System.out.println(post);
            postRepository.deleteById(post.getId());
            return true;
        }catch (Exception e){
            return false;
        }
    }

}
