package com.korit.korit_gov_spring_boot_study.controller;

import com.korit.korit_gov_spring_boot_study.dto.request.FindpostReqDto;
import com.korit.korit_gov_spring_boot_study.dto.request.PostingReqDto;
import com.korit.korit_gov_spring_boot_study.dto.response.PostRespDto;
import com.korit.korit_gov_spring_boot_study.service.PostService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/post")
public class PostController {
    @Autowired
    PostService postService;
//    public  PostController () {
//        postService = PostService.getInstance();
//    }

    @PostMapping("/add")
    public ResponseEntity<PostRespDto> postadd(@RequestBody PostingReqDto postingReqDto) {
        return ResponseEntity.ok(postService.posting(postingReqDto));
    }

    @GetMapping("/all")
    public ResponseEntity<PostRespDto> postall() {
        return ResponseEntity.ok(postService.getAll());
    }

    @PostMapping("/find")
    public ResponseEntity<PostRespDto<?>> postfind(@RequestBody FindpostReqDto findpostReqDto) {
        return ResponseEntity.ok(postService.getByRequest(findpostReqDto));
    }

    @PostMapping("/edit")
    public ResponseEntity<PostRespDto<?>> postedit(@RequestBody PostingReqDto postingReqDto) {
        return ResponseEntity.ok(postService.editing(postingReqDto));
    }

    @GetMapping("/findid")
    public ResponseEntity<PostRespDto<?>> postfindid(@RequestParam Integer postId) {
        return ResponseEntity.ok(
                postService.getByRequest(FindpostReqDto.builder()
                        .postId(postId)
                        .build()
                ));
    }

    @GetMapping("/findkeyword")
    public ResponseEntity<PostRespDto<?>>postfindkeyword(@RequestParam String keyword) {
        return ResponseEntity.ok(
                postService.getByRequest(FindpostReqDto.builder()
                        .title(keyword)
                        .build()
                ));
    }
}
