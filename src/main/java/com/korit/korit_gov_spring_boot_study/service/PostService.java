package com.korit.korit_gov_spring_boot_study.service;

import com.korit.korit_gov_spring_boot_study.dto.request.FindpostReqDto;
import com.korit.korit_gov_spring_boot_study.dto.request.PostingReqDto;
import com.korit.korit_gov_spring_boot_study.dto.response.PostingRespDto;
import com.korit.korit_gov_spring_boot_study.entity.Post;
import com.korit.korit_gov_spring_boot_study.repository.PostRepository;

import java.util.List;

public class PostService {
    private static PostService instance;
    private PostRepository postRepository;

    private PostService() {
        this.postRepository = PostRepository.getInstance();
    }

    public static PostService getInstance() {
        if (instance == null) {
            instance = new PostService();
        }
        return instance;
    }

    public PostingRespDto posting(PostingReqDto postingReqDto) {
        System.out.println("service : " + postingReqDto);
        Post foundPost = postRepository.findByTitle(postingReqDto.getTitle());
        if (foundPost != null) {
            return PostingRespDto.<String>builder()
                    .status("failed")
                    .body(foundPost.getTitle() + " : 제목이 중복되었습니다.")
                    .build();
        }
        Post post = postRepository.addPost(postingReqDto.toEntity());
        return PostingRespDto.<Post>builder()
                .status("success")
                .body(post)
                .build();
    }

    public PostingRespDto getAll() {
        if (postRepository.findAll().size() == 0) {
            return PostingRespDto.<String>builder()
                    .status("failed")
                    .body("등록된 내용이 없습니다.")
                    .build();
        }
        return PostingRespDto.<List<Post>>builder()
                .status("success")
                .body(postRepository.findAll())
                .build();
    }

    public PostingRespDto getByPostId(Integer postId) {
        Post foundPost = postRepository.findByPostId(postId);
        if (foundPost == null) {
            return PostingRespDto.<String>builder()
                    .status("failed")
                    .body(postId + " : 검색에 실패하였습니다.")
                    .build();
        }
        return PostingRespDto.<Post>builder()
                .status("success")
                .body(foundPost)
                .build();
    }

    public PostingRespDto getByKeyword(String keyword) {
        List<Post> foundPost = postRepository.findByKeyword(keyword);
        if (foundPost.size() == 0) {
            return PostingRespDto.<String>builder()
                    .status("failed")
                    .body(keyword + " : 검색에 실패하였습니다.")
                    .build();
        }
        return PostingRespDto.<List<Post>>builder()
                .status("success")
                .body(foundPost)
                .build();

    }

    public PostingRespDto getByRequest(FindpostReqDto findpostReqDto) {
        if (findpostReqDto.getPostId() == null && findpostReqDto.getTitle() != null) {
            return this.getByKeyword(findpostReqDto.getTitle());
        } else if (findpostReqDto.getPostId() != null && findpostReqDto.getTitle() == null) {
            return this.getByPostId(findpostReqDto.getPostId());
        } else return this.getAll();
    }

    public PostingRespDto editing(PostingReqDto postingReqDto) {
        if (postRepository.editPost(postingReqDto.toEntity())) {
            return this.getAll();
        } else {
            return PostingRespDto.<String>builder()
                    .status("failed")
                    .body(postingReqDto.getTitle() + " : 일치하는 제목의 post를 찾을 수 없습니다.")
                    .build();
        }
    }

}
