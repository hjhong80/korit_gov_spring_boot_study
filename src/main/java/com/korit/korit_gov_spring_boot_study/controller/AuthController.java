package com.korit.korit_gov_spring_boot_study.controller;

import com.korit.korit_gov_spring_boot_study.dto.request.SigninReqDto;
import com.korit.korit_gov_spring_boot_study.dto.request.SignupReqDto;
import com.korit.korit_gov_spring_boot_study.dto.response.SigninRespDto;
import com.korit.korit_gov_spring_boot_study.dto.response.SignupRespDto;
import com.korit.korit_gov_spring_boot_study.service.UserService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
public class AuthController {

    /*
    * 파라미터가 없으면 400 error(bad request / client miss) 발생
    * 타입 또는 명칭 불일치, 입력값 누락 등등
    *
    * @RequestParam 은 주소 창에 주고 받을 내용이 그대로 표시되므로
    * 민감 정보를 주고 받을때 사용하기 힘든 한계가 있다.
    * */

//    @GetMapping("/get")
//    public String getUser(@RequestParam String userId) {
//        System.out.println("쿼리 파라미터로 입력된 값 : " + userId);
//        return "쿼리 파라미터로 입력된 값 : " + userId;
//    }

    /*
    * @RequestParam 어노테이션의 파라미터
    * (required = boolean) - true/false로 누락 가능 여부
    * !!주의!! Wrapper class가 아니면 null 값이 될 수 없으므로 false가 의미 없어짐!!
    *
    * (defaultValue = <기본값>) - 누락되면 자료형에 맞는 설정된 <기본값>으로 자동 설정 된다.
    *
    * (value = "새로운이름") - 파라미터 입력 키를 "새로운이름"으로 사용할 수 있다.
    * */

//    @GetMapping("/get/name")
//    public String getUsername(@RequestParam(value = "name", defaultValue = "홍길동") String username, @RequestParam(required = false) Integer age) {
//        System.out.println(username);
//        System.out.println(age);
//        return username + age;
//    }

    /*
    * @RequestBody
    *  - HTTP 요청의 바디에 들어 있는 Json 형태의 데이터를 자바 객체(DTO)로 변환하여 주입해주는 어노테이션
    *  - Client의 Json 형식 데이터 전송을 서버에서 알아서 parsing 하여 DTO 객체에 mapping 주입
    *  - 민감 정보 노출 방지
    *
    * !!주의!! Json의 키값과 DTO객체의 멤버변수 명칭을 일치 시켜야 mapping 가능
    *
    * Get 메소드는 @RequestParam / Post 메소드는 @RequestBody를 사용하는것이 좋다
    *
    * */

//    @PostMapping("/signin")
//    public String signin(@RequestBody SigninReqDto signinReqDto) {
//        return "로그인 성공 : " + signinReqDto.getUsername() + "님 반갑습니다.";
//
//    }

//    @PostMapping("/signup")
//    public String signup(@RequestBody SignupReqDto signupReqDto) {
//        System.out.println(signupReqDto);
//        return signupReqDto.getUsername() + "님 회원 가입이 완료 되었습니다.";
//    }

    /*
     *
     * ResponseEntity
     * HTTP 응답 전체를 커스터마이징해서 응답 할 수 있는 Spring Class
     * 상태 코드, 응답 바디, 응답 헤더 등등 사용자의 의향대로 수정 가능
     * 200 => 성공
     * 400 => 잘못된 요청
     * 401 => 인증 실패
     * 403 => 접근 권한 없음
     * 404 => 접근할 페이지가 없음(주소 오타 등등)
     * 500 => 서버 에러 (자바 코드 에러 등등)
     * */

//    @PostMapping("/signup")
//    public ResponseEntity<SignupRespDto> signup(@RequestBody SignupReqDto signupReqDto) {
//        if (signupReqDto.getUsername() == null || signupReqDto.getUsername().trim().isEmpty()) {
//            SignupRespDto signupRespDto = new SignupRespDto("failed", "username을 입력 하세요");
//            return ResponseEntity.badRequest().body(signupRespDto); // badRequest() = status 400 (bad request)로 설정
//        } else if (signupReqDto.getPassword() == null || signupReqDto.getPassword().trim().isEmpty()) {
//            SignupRespDto signupRespDto = new SignupRespDto("failed", "password를 입력 하세요");
//            return ResponseEntity.badRequest().body(signupRespDto); // badRequest() = status 400 (bad request)로 설정
//        }
//        SignupRespDto signupRespDto = new SignupRespDto("success", "회원 가입 성공");
//        return ResponseEntity.ok(signupRespDto); // ok() = status 200 (ok)로 설정
//    }
    private UserService userService;
//    private PostService postService;

    public AuthController() {
        this.userService = UserService.getInstance();
//        this.postService = PostService.getInstance();
    }

    @PostMapping("/signup")
    public ResponseEntity<SignupRespDto> signup(@RequestBody SignupReqDto signupReqDto) {
        return ResponseEntity.ok(userService.signup(signupReqDto));
    }

    @PostMapping("/signin")
    public ResponseEntity<SigninRespDto> signin(@RequestBody SigninReqDto signinReqDto) {
        return ResponseEntity.ok(userService.signin(signinReqDto));
    }
}
