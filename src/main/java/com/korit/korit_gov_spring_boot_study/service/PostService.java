package com.korit.korit_gov_spring_boot_study.service;

import com.korit.korit_gov_spring_boot_study.dto.request.FindpostReqDto;
import com.korit.korit_gov_spring_boot_study.dto.request.PostingReqDto;
import com.korit.korit_gov_spring_boot_study.dto.response.PostRespDto;
import com.korit.korit_gov_spring_boot_study.entity.Post;
import com.korit.korit_gov_spring_boot_study.repository.PostRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class PostService {
//    private static PostService instance;
    @Autowired
    private PostRepository postRepository;

//    private PostService() {
//        this.postRepository = PostRepository.getInstance();
//    }
//
//    public static PostService getInstance() {
//        if (instance == null) {
//            instance = new PostService();
//        }
//        return instance;
//    }

    public PostRespDto<?> posting(PostingReqDto postingReqDto) {
        System.out.println("service : " + postingReqDto);
        Post foundPost = postRepository.findByTitle(postingReqDto.getTitle());
        if (foundPost != null) {
            return PostRespDto.<String>builder()
                    .status("failed")
                    .message("등록에 실패하였습니다.")
                    .body(foundPost.getTitle() + " : 제목이 중복되었습니다.")
                    .build();
        }
        Post post = postRepository.addPost(postingReqDto.toEntity());
        return PostRespDto.<Post>builder()
                .status("success")
                .message("등록에 성공하였습니다.")
                .body(post)
                .build();
    }

    public PostRespDto<?> getAll() {
        if (postRepository.findAll().isEmpty()) {
            return PostRespDto.<String>builder()
                    .status("failed")
                    .message("조회에 실패하였습니다.")
                    .body("등록된 내용이 없습니다.")
                    .build();
        }
        return PostRespDto.<List<Post>>builder()
                .status("success")
                .message("조회에 성공하였습니다.")
                .body(postRepository.findAll())
                .build();
    }

    public PostRespDto<?> getByPostId(Integer postId) {
        Post foundPost = postRepository.findByPostId(postId);
        if (foundPost == null) {
            return PostRespDto.<String>builder()
                    .status("failed")
                    .message("검색에 실패하였습니다.")
                    .body("ID : " + postId)
                    .build();
        }
        return PostRespDto.<Post>builder()
                .status("success")
                .message("단건 검색에 성공하였습니다.")
                .body(foundPost)
                .build();
    }

    public PostRespDto<?> getByKeyword(String keyword) {
        List<Post> foundPost = postRepository.findByKeyword(keyword);
        if (foundPost.isEmpty()) {
            return PostRespDto.<String>builder()
                    .status("failed")
                    .message("검색에 실패하였습니다.")
                    .body("키워드 : " + keyword)
                    .build();
        }
        return PostRespDto.<List<Post>>builder()
                .status("success")
                .message("키워드 검색에 성공하였습니다.")
                .body(foundPost)
                .build();

    }

    public PostRespDto<?> getByRequest(FindpostReqDto findpostReqDto) {
        if (findpostReqDto.getPostId() == null && findpostReqDto.getTitle() != null) {
            return this.getByKeyword(findpostReqDto.getTitle());
        } else if (findpostReqDto.getPostId() != null && findpostReqDto.getTitle() == null) {
            return this.getByPostId(findpostReqDto.getPostId());
        } else if (findpostReqDto.getPostId() != null && findpostReqDto.getTitle() != null) {
            return PostRespDto.<String>builder()
                    .status("failed")
                    .message("요청이 잘못 되었습니다.")
                    .body("검색은 postId 단건 또는 title 키워드 조회만 가능합니다.")
                    .build();
        } else return this.getAll();
    }

    public PostRespDto<?> editing(PostingReqDto postingReqDto) {
        if (postRepository.editPost(postingReqDto.toEntity())) {
            return this.getAll();
        } else {
            return PostRespDto.<String>builder()
                    .status("failed")
                    .message("일치하는 제목의 post를 찾을 수 없습니다.")
                    .body("제목 : " + postingReqDto.getTitle())
                    .build();
        }
    }

}
