package com.ionin.jpa_homework.repository;

import com.ionin.jpa_homework.entity.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface UserRepository extends JpaRepository<User, Long> {

    Optional<User> findByEmail(String email);

    @Query("SELECT u FROM User u JOIN u.posts p WHERE p.title LIKE %:title%")
    List<User> findUsersWithPostsContainingTitle(@Param("title") String title);
}