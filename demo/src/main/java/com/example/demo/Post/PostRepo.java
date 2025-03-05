package com.example.demo.Post;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;

public interface PostRepo extends JpaRepository<Post, Long>{

    Page<Post> findByTitleContaining(
        String title,  Pageable pageable);

}
