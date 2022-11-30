package com.steve.springbootblogapplication.service;

import com.steve.springbootblogapplication.model.Post;
import com.steve.springbootblogapplication.repository.IPostRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;

@Service
public class PostService {
    private final IPostRepository repository;

    @Autowired
    public PostService(IPostRepository repository) {
        this.repository = repository;
    }

   public List<Post> getAll(){
        return repository.findAll();
   }

    public Optional<Post> getById(Long id){
        return repository.findById(id);
    }

    public Post save(Post post){
        if (post.getId() == null){
            post.setCreatedAt(LocalDateTime.now());
        }
        return repository.save(post);
    }
}
