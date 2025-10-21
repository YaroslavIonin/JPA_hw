package com.ionin.jpa_homework.service;

import com.ionin.jpa_homework.entity.Post;
import com.ionin.jpa_homework.entity.Tag;
import com.ionin.jpa_homework.entity.User;
import com.ionin.jpa_homework.repository.PostRepository;
import com.ionin.jpa_homework.repository.TagRepository;
import com.ionin.jpa_homework.repository.UserRepository;

import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
@RequiredArgsConstructor
public class BlogService {

    private final UserRepository userRepository;
    private final PostRepository postRepository;
    private final TagRepository tagRepository;

    public User createUser(User user) {
        return userRepository.save(user);
    }

    public Optional<User> findUserById(Long id) {
        return userRepository.findById(id);
    }

    public List<User> findAllUsers() {
        return userRepository.findAll();
    }

    public void deleteUser(Long userId) {
        userRepository.deleteById(userId);
    }

    public Post createPost(Post post) {
        return postRepository.save(post);
    }

    public List<Post> findPostsByTitleKeyword(String keyword) {
        return postRepository.findByTitleContainingIgnoreCase(keyword);
    }

    @Transactional
    public Post addTagToPost(Long postId, Long tagId) {
        Post post = postRepository.findById(postId)
                .orElseThrow(() -> new RuntimeException("Post not found"));
        Tag tag = tagRepository.findById(tagId)
                .orElseThrow(() -> new RuntimeException("Tag not found"));

        post.getTags().add(tag);
        return post;
    }

    public List<Post> findPostsByTag(String tagName) {
        return postRepository.findPostsByTagName(tagName);
    }

    public Tag createTag(Tag tag) {
        return tagRepository.save(tag);
    }

    public List<Post> findPostsByUserEmail(String email) {
        return postRepository.findPostsByAuthorEmail(email);
    }
}