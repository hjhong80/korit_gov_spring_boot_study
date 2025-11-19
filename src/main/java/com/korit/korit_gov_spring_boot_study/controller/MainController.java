package com.korit.korit_gov_spring_boot_study.controller;

import lombok.AllArgsConstructor;
import lombok.Data;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Data
@AllArgsConstructor
class UserDto {
    private int userId;
    private String username;
    private int age;
}

// SSR => 서버에서 웹페이지를 렌더링후 반환하는 방식
@Controller
public class MainController {
    private List<UserDto> userList = new ArrayList<>();

//     동적인 요소가 없는 정적인 웹페이지 반환
    @GetMapping("/main")
    public String getMain() {
        return "main.html";
    }

//    SSR에 동적 기능을 추가하려면 Thymeleaf를 적용
//    서버에서 html을 렌더링 할때, 자바 데이터를 끼워 넣을 수 있게 해주는 템플릿 엔진
    @GetMapping("/profile")
    public String getProfile(Model model) {
        model.addAttribute("username", "<b>홍길동</b>");
//        model.addAttribute("isAdult", true);
        model.addAttribute("isAdult", false);
        model.addAttribute("age", 25);

        Map<String, String> keyMap = new HashMap<>();
        keyMap.put("age", "50");
        keyMap.put("username", "홍길동");
        keyMap.put("email","ghdrlfehd@gmail.com");
        System.out.println(keyMap);
        model.addAttribute("userList", keyMap);
        return "profile.html";
    }

    @GetMapping("/search")
    public String getSearch(@RequestParam String keyword, @RequestParam String keyword2, Model model) {
        model.addAttribute("keyword",keyword);
        model.addAttribute("keyword2",keyword2);
        return "search.html";
    }

    @GetMapping("/signup")
    public String signup() {
        return "signup.html";
    }

    @PostMapping("/signup")
    public String signupSubmit(@RequestParam String name, @RequestParam Integer age,Model model) {
        UserDto userDto = new UserDto(userList.size()+1, name, age);
        userList.add(userDto);
        model.addAttribute("message", name + "님, 가입을 환영합니다.");
        return "result_page.html";
    }

    @GetMapping("/users")
    public String userList(Model model) {
        model.addAttribute("users",userList);
        return "users.html";
    }
}
