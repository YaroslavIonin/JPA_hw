package com.ionin.jpa_homework.repository;

import com.ionin.jpa_homework.entity.Post;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface PostRepository extends JpaRepository<Post, Long> {

    List<Post> findByTitleContainingIgnoreCase(String keyword);

    @Query("SELECT p FROM Post p JOIN p.tags t WHERE t.name = :tagName")
    List<Post> findPostsByTagName(@Param("tagName") String tagName);

    @Query("SELECT p FROM Post p WHERE p.author.email = :email")
    List<Post> findPostsByAuthorEmail(@Param("email") String email);
}