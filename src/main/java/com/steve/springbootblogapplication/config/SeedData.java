package com.steve.springbootblogapplication.config;

import com.steve.springbootblogapplication.model.Post;
import com.steve.springbootblogapplication.service.PostService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
public class SeedData implements CommandLineRunner {

    private final PostService postService;

    @Autowired
    public SeedData(PostService postService) {
        this.postService = postService;
    }

    @Override
    public void run(String... args) throws Exception {
        List<Post> posts = postService.getAll();

        if(posts.size() == 0){
            Post post1 = new Post();
            post1.setTitle("title of post 1");
            post1.setBody("body of post 1");

            Post post2 = new Post();
            post2.setTitle("title of post 2");
            post2.setBody("body of post 2");

            postService.save(post1);
            postService.save(post2);
        }
    }
}
