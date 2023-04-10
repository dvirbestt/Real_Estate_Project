package com.example.RealEstateSite.controller;


import com.example.RealEstateSite.model.*;
import com.example.RealEstateSite.repository.PostRepository;
import com.example.RealEstateSite.service.PostService;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.http.converter.HttpMessageNotReadableException;
import org.springframework.validation.Errors;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

@CrossOrigin("*")
@RestController
@RequestMapping("/post")
public class PostController {

    @Autowired
    private PostService postService;
    @Autowired
    private PostRepository postRepository;

    @PostMapping("/save")
    public ResponseEntity<?> savePost(@Valid @RequestBody Post post, Errors errors) {
        if (errors.hasErrors()) {
            ArrayList<String> errorList = new ArrayList<>();
            errors.getAllErrors().forEach((error -> {

                errorList.add(error.getDefaultMessage());
            }));
            return ResponseEntity.status(401).body(errorList);
        }
        Post savedPost = postService.savePost(post);

        if (savedPost == null){
            return ResponseEntity.badRequest().body("Try Again");
        }

        return ResponseEntity.ok().body(post);
    }

    @PostMapping("/search")
    public ResponseEntity<?> searchByCity(@Valid @RequestBody SearchRequest searchRequest, Errors errors){

        if (errors.hasErrors()) {
            ArrayList<String> errorList = new ArrayList<>();
            errors.getAllErrors().forEach((error -> {
                errorList.add(error.getDefaultMessage());
            }));
            return ResponseEntity.status(401).body(errorList);
        }

        List<Post> postList = postService.searchFilter(searchRequest);

        if (postList != null &&postList.size() > 0 ){
            return ResponseEntity.ok().body(postList);
        }

        return ResponseEntity.badRequest().body(Collections.singleton("No Matches"));
    }

    @PostMapping("/getById")
    public ResponseEntity<?> getById(@RequestBody Post post){
        Post fullPost = postService.getById(post.getId());
        if (fullPost != null){
            return ResponseEntity.ok().body(postService.getById(post.getId()));
        }
        return ResponseEntity.badRequest().body(Collections.singleton("No Such Post Id Found"));
    }


    @GetMapping("/random")
    public ResponseEntity<?> getRandom(){
        return ResponseEntity.ok().body(postService.getRand());
    }

    @GetMapping("/getNames")
    public ResponseEntity<?> getNames(){
        return ResponseEntity.ok().body(postService.getCityNames());
    }

    @PostMapping("/myPosts")
    public ResponseEntity<?> showMyPosts(@RequestBody Post post){
        List<Post> postList = postService.showMyPosts(post.getAuthor());
        if (postList.size() > 0){
            return ResponseEntity.ok().body(postList);
        }
        return ResponseEntity.status(400).body(Collections.singleton("You Did Not Post Anything Yet!"));
    }

    @PostMapping("/deletePost")
    public ResponseEntity<?> deletePost(@RequestBody DeletePostRequest deletePostRequest){

        if (postService.deletePost(deletePostRequest)){
            return ResponseEntity.ok().body(Collections.singleton("Post Deleted Successfully"));
        }
        return ResponseEntity.badRequest().body(Collections.singleton("Try Again!"));
    }

    @PutMapping("/updatePost")
    public ResponseEntity<?> updatePost(@Valid @RequestBody UpdateRequest updateRequest,Errors errors){
        if (errors.hasErrors()) {
            ArrayList<String> errorList = new ArrayList<>();
            errors.getAllErrors().forEach((error -> {
                errorList.add(error.getDefaultMessage());
            }));
            return ResponseEntity.status(401).body(errorList);
        }
        Post post = postService.updatePost(updateRequest);
        if (post != null){
            return ResponseEntity.ok().body(post);
        }
        return ResponseEntity.status(401).body(Collections.singleton("Something Went Wrong, Please Try Again!"));
    }



}
