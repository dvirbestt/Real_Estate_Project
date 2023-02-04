package com.example.RealEstateSite.service;

import com.example.RealEstateSite.model.Post;
import com.example.RealEstateSite.repository.PostRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;


@Service
public class PostService {

    @Autowired
    private PostRepository postRepository;

    public List<Post> findByCity(String city){
        return postRepository.findByCity(city);
    }

    public Post savePost(Post post){
        return postRepository.save(post);
    }

    //TODO - Search By City And Number Of Rooms



    public List<Post> findByCityAndNumOfRooms(String city,float numOfRooms){
        return postRepository.findByCityAndNumOfRooms(city,numOfRooms);
    }

    public List<Post> getRand(){
        return postRepository.getRand();
    }

    public Post getById(String id){
        Optional<Post> post = postRepository.findById(id);

        return post.orElse(null);

    }
}
