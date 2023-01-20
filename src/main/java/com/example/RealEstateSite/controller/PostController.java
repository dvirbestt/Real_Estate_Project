package com.example.RealEstateSite.controller;

import com.example.RealEstateSite.model.Post;
import com.example.RealEstateSite.service.PostService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;

@CrossOrigin
@RestController
@RequestMapping("/post")
public class PostController {

    @Autowired
    private PostService postService;

    @PostMapping("/save")
    public ResponseEntity<?> savePost(@RequestBody Post post){
        Post savedPost = postService.savePost(post);
        if (savedPost == null){
            return ResponseEntity.badRequest().body("Try Again");
        }
        return ResponseEntity.ok().body(post);
    }

    @PostMapping("/search")
    public ResponseEntity<?> searchByCity(@RequestBody Post post){

        List<Post> postList = new ArrayList<>();
        if (post.getCity()!=null && post.getNumOfRooms()!= 0){
            postList.addAll(postService.findByCityAndNumOfRooms(post.getCity(), post.getNumOfRooms()));
        }
        if (post.getCity() != null && post.getNumOfRooms() == 0){
            postList.addAll(postService.findByCity(post.getCity()));
        }


        if (postList.size()>0){
            return ResponseEntity.ok().body(postList);
        }
        return ResponseEntity.ok().body("No Matches");
    }


    @GetMapping("/random")
    public ResponseEntity<?> getRandom(){
        return ResponseEntity.ok().body(postService.getRand());
    }
}
