package com.steve.springbootblogapplication.controller;

import com.steve.springbootblogapplication.model.Account;
import com.steve.springbootblogapplication.model.Post;
import com.steve.springbootblogapplication.service.AccountService;
import com.steve.springbootblogapplication.service.PostService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.util.Optional;

@Controller
@RequestMapping("/posts")
public class PostController {
    private final PostService postService;
    private final AccountService accountService;

    @Autowired
    public PostController(PostService postService, AccountService accountService) {
        this.postService = postService;
        this.accountService = accountService;
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

    @GetMapping("/new")
    public String createNewPost(Model model){
        Optional<Account> maybeAccount = accountService.getAccountByEmail("user.user@domain.com");
        if (maybeAccount.isPresent()){
            Post post = new Post();
            post.setAccount(maybeAccount.get());
            model.addAttribute("post",post);
            return "post_new";
        } else {
            return "404";
        }

    }

    @PostMapping("/new")
    public String addNewPost(@ModelAttribute Post post){
        Post savedPost = postService.save(post);
        return "redirect:"+ savedPost.getId();
    }
}
