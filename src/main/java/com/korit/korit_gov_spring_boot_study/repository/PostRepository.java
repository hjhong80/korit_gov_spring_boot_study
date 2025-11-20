package com.korit.korit_gov_spring_boot_study.repository;

import com.korit.korit_gov_spring_boot_study.entity.Post;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class PostRepository {
    private static PostRepository instance;
    Map<Integer,Post> bbs2;
    List<Post> bbs;
    private PostRepository() {
        this.bbs = new ArrayList<>();
        this.bbs2 = new HashMap<>();
    }

    public static PostRepository getInstance() {
        if (instance == null) {
            instance = new PostRepository();
        }
        return instance;
    }

    public Post addPost(Post post) {
        if (bbs.isEmpty()) post.setPostId(1);
        else post.setPostId(bbs.get(bbs.size()-1).getPostId()+1);
        bbs.add(post);
        System.out.println("------------------");
        System.out.println("add : " + post);
        System.out.println("--- repository ---");
        System.out.println(bbs);
        System.out.println("------------------");
        return post;
    }

    public Post findByTitle(String title) {
        return bbs.stream()
                .filter(i -> i.getTitle().equals(title))
                .findFirst()
                .orElse(null);
    }

    public Post findByPostId(Integer postId) {
        return bbs.stream()
                .filter(i -> i.getPostId().equals(postId))
                .findFirst()
                .orElse(null);
    }

    public List<Post> findAll() {
        return bbs;
    }

    public List<Post> findByKeyword(String keyword) {
        System.out.println(bbs.get(0).getTitle());
        return bbs.stream()
                .filter(i -> i.getTitle().contains(keyword))
                .toList();
    }

    public boolean editPost(Post post) {
        if (this.findByTitle(post.getTitle()) == null || bbs.isEmpty()) {
            return false;
        } else {
            bbs.get(this.findByTitle(post.getTitle()).getPostId()-1).setContent(post.getContent());
            return true;
        }
    }
}
