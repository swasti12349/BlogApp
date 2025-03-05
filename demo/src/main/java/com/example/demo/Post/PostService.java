package com.example.demo.Post;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

@Service
public class PostService {

    @Autowired
    public PostRepo postRepo;

    public List<Post> getAllPosts() {
        return postRepo.findAll();
    }

    public void addPost(Post post) {
        postRepo.save(post);
    }

    public void deletePost(Long id) {
        postRepo.deleteById(id);
    }

    public void updatePost(Post post) {
        postRepo.save(post);
    }

    public Post getPost(Long id) {
        return postRepo.findById(id).orElse(null);
    }

    public Page<Post> searchPosts(String keyword, int page, int size) {
        Pageable pageable = PageRequest.of(page, size);
        return postRepo.findByTitleContaining(keyword, pageable);
    }


}
