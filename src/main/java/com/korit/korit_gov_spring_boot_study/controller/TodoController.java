package com.korit.korit_gov_spring_boot_study.controller;

import lombok.Builder;
import lombok.Data;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.ArrayList;
import java.util.List;

@Data
@Builder
class TodoDto {
    private Integer todoId;
    private String title;
    private String content;
}

@Controller
public class TodoController {
    private List<TodoDto> todoList = new ArrayList<>();
//    @GetMapping("/main")
//    public String getMain() {
//        return "main.html";
//    }

    @GetMapping("/todomain")
    public String getTodo() {
        return "todomain.html";
    }

    @PostMapping("/todomain")
    public String postTodo(@RequestParam String title, @RequestParam String content, Model model) {
        TodoDto todoDto = new TodoDto(todoList.size()+1,title, content);
        todoList.add(todoDto);
        model.addAttribute("message", title + " 제목의 To-do 내용이 리스트에 추가되었습니다.");
        return "todosuccess.html";
    }

    @GetMapping("/todoshow")
    public String showTodo(Model model) {
        model.addAttribute("todoList",todoList);
        return "todoshow.html";
    }
}
