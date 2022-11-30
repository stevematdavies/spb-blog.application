package com.steve.springbootblogapplication.controller;

import com.steve.springbootblogapplication.model.Post;
import com.steve.springbootblogapplication.service.PostService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;

import java.util.Optional;

@Controller
@RequestMapping("/posts")
public class PostController {
    private final PostService postService;

    @Autowired
    public PostController(PostService postService) {
        this.postService = postService;
    }

    @GetMapping("/{id}")
    public String getPost(@PathVariable Long id, Model model){
        Optional<Post> maybePost = postService.getById(id);
        if (maybePost.isPresent()){
            model.addAttribute("post", maybePost.get());
            return "post";
        } else {
            return "404";
        }
    }
}
