package com.example.demo.Post;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.example.demo.User.User;
import com.example.demo.User.UserRepo;

@RestController
@RequestMapping("/api/v1/posts")
public class PostController {


    @Autowired
    public PostService postService;


    @Autowired
    public PostRepo postRepo;

    @Autowired
    public UserRepo userRepository;

    @GetMapping
    public List<Post> getAllPosts() {
        return postService.getAllPosts();
    }

    @PostMapping
    public ResponseEntity<String> addPost(@RequestBody Post post) {
        User user = userRepository.findByEmail(post.getUser().getEmail())
                .orElseGet(() -> userRepository.save(post.getUser())); // Save if new user

        post.setUser(user); // Ensure correct User object
        postService.addPost(post);

        return ResponseEntity.status(HttpStatus.CREATED).body("Post created successfully");
    }

    @GetMapping("/getPosts")
    public ResponseEntity<Page<Post>> getPosts(
            @RequestParam(defaultValue = "0") int page,    // Default page = 0
            @RequestParam(defaultValue = "5") int size     // Default page size = 5
    ) {
        Pageable pageable = PageRequest.of(page, size);
        Page<Post> postPage = postRepo.findAll(pageable);
        return ResponseEntity.ok(postPage);
    }

    @DeleteMapping("/{id}")
    public void deletePost(@PathVariable Long id) {
        postService.deletePost(id);
    }

    @PutMapping
    public void updatePost(@RequestBody Post post) {
        postService.updatePost(post);
    }

    @GetMapping("/{id}")
    public Post getPost(@PathVariable Long id) {
        return postService.getPost(id);
    }

    @GetMapping("/search")
    public ResponseEntity<Page<Post>> searchPosts(
            @RequestParam(name="keyword") String keyword,
            @RequestParam(defaultValue = "0") int page,
            @RequestParam(defaultValue = "5") int size
    ) {
        return ResponseEntity.ok(postService.searchPosts(keyword, page, size));
    }

}
