package com.example.demo.User;

import java.util.List;

import com.example.demo.Post.Post;
import com.fasterxml.jackson.annotation.JsonIgnore;

import jakarta.persistence.CascadeType;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.OneToMany;
import jakarta.persistence.Table;
import jakarta.persistence.Version;
import jakarta.validation.constraints.NotNull;
import lombok.NoArgsConstructor;

@Entity
@Table(name = "users")
@NoArgsConstructor
public class User {

    @Id
    @NotNull(message = "Email is mandatory")
    @Column(unique = true)
    private String email;

    @NotNull(message = "Username is mandatory")
    @Column(unique = true)
    private String username;

    @NotNull(message = "Password is mandatory")
    private String password;

    @NotNull(message = "Role is mandatory")
    private String role;

    @Version
    private Long version;

    @OneToMany(mappedBy = "user", cascade = CascadeType.ALL, orphanRemoval = true) // ✅ Corrected OneToMany mapping
    @JsonIgnore
    private List<Post> posts; // ✅ Changed to List<Post> for multiple posts

    public User(String email, String username, String password, String role) {
        this.email = email;
        this.username = username;
        this.password = password;
        this.role = role;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getRole() {
        return role;
    }

    public void setRole(String role) {
        this.role = role;
    }

    public Long getVersion() {
        return version;
    }

    public void setVersion(Long version) {
        this.version = version;
    }

    public List<Post> getPosts() {
        return posts;
    }

    public void setPosts(List<Post> posts) {
        this.posts = posts;
    }
}