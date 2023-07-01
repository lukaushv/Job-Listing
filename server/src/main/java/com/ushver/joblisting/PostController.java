package com.ushver.joblisting;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.mongodb.core.MongoTemplate;
import org.springframework.data.mongodb.core.query.Criteria;
import org.springframework.data.mongodb.core.query.Update;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDateTime;
import java.util.List;

@RestController
@CrossOrigin(origins = "*")
public class PostController {

    @Autowired
    private PostRepository postRepository;

    @Autowired
    private SearchRepository searchRepository;

    @GetMapping("/allposts")
    public List<Post> getAllPosts(){
        return postRepository.findAll();
    }

    @GetMapping("/post/{search}")
    public List<Post> getPost(@PathVariable String search){
        return searchRepository.findByText(search);
    }

    @PostMapping("/postpost")
    public Post createPost(@RequestBody Post post){
        return postRepository.save(post);
    }

}
