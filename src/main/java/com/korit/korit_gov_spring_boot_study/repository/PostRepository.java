package com.korit.korit_gov_spring_boot_study.repository;

import com.korit.korit_gov_spring_boot_study.entity.Post;
import org.springframework.stereotype.Repository;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/*

Iversion of Control => 제어의 역전 (IoC)
객체 생성과 제어의 주도권을 개발자가 아닌 스프링부트가 갖는것
IoC Container => 스프링부트가 만든 객체들을 담아두고 관리하는 창고
필요한 곳이 있으면 IoC Container에서 해당 객체를 찾아서 넣어주므로 new를 사용하여 객체를 생성할 필요 없음.
서버가 실행될때 IoC Container에 객체들이 생성되어 보관되어 있다.

Dependency Injection => 의존성 주입
필요한 객체(의존성)를 직접 만들지 않고 외부에서 대신 주입하는 행위

사용 예)
Repository 클래스를 만들때 @Repository
Service 클래스를 만들때 @Service


@Autowired 어노테이션이 멤버 변수 위에 존재하면 IoC Container에서 객체를 찾아서 주입 해줌

사용 예)
1) 컨트롤러에서 서비스 객체 인스턴스 선언이 필요할때
@Autowired
private PostService postService

2) 서비스에서 레포지토리 객체 인스턴스 선언이 필요할때
@Autowired
private PostRepository postRepository


// -> 굳이 싱글턴 구조를 일일히 구현할 필요 없음. //

*/

@Repository
public class PostRepository {
//    private static PostRepository instance;

    List<Post> bbs;
    private PostRepository() {
        this.bbs = new ArrayList<>();
    }
//
//    public static PostRepository getInstance() {
//        if (instance == null) {
//            instance = new PostRepository();
//        }
//        return instance;
//    }

    public Post addPost(Post post) {
        if (bbs.isEmpty()) post.setPostId(1);
        else post.setPostId(bbs.get(bbs.size()-1).getPostId()+1);
        bbs.add(post);
        System.out.println("------------------");
        System.out.println("add : " + post);
        System.out.println("--- repository ---");
        bbs.forEach(System.out::println);
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
        System.out.println("keyword");
        bbs.stream()
                .filter(i -> i.getTitle().contains(keyword))
                .forEach(System.out::println);
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
