package com.ionin.jpa_homework;

import com.ionin.jpa_homework.entity.*;
import com.ionin.jpa_homework.service.BlogService;
import lombok.RequiredArgsConstructor;
import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;

@Component
@RequiredArgsConstructor
public class DataLoader implements CommandLineRunner {

    private final BlogService blogService;

    @Override
    public void run(String... args) throws Exception {
        User user1 = new User();
        user1.setEmail("email1@example.com");
        user1.setPassword("pass1");
        user1.setFirstName("Иван");
        user1.setLastName("Иванов");
        blogService.createUser(user1);

        User user2 = new User();
        user2.setEmail("email2@example.com");
        user2.setPassword("pass2");
        user2.setFirstName("Петр");
        user2.setLastName("Петров");
        blogService.createUser(user2);

        Tag javaTag = new Tag();
        javaTag.setName("Java");
        blogService.createTag(javaTag);

        Tag springTag = new Tag();
        springTag.setName("Spring");
        blogService.createTag(springTag);

        Post post = new Post();
        post.setTitle("Это заголовок поста");
        post.setContent("Это контент поста...");
        post.setAuthor(user1);
        blogService.createPost(post);

        blogService.addTagToPost(post.getId(), javaTag.getId());
        blogService.addTagToPost(post.getId(), springTag.getId());

        System.out.println("Посты с тегом 'Java': " + blogService.findPostsByTag("Java").size());

        System.out.println("Посты Ивана: " + blogService.findPostsByUserEmail("email1@example.com").size());
    }
}