package com.example.jpaadv.controller;

import com.example.jpaadv.service.PostService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequiredArgsConstructor // 의존성주입
@RequestMapping("/posts") // posts -> get, post...
public class PostController {
    private final PostService postService;

    @GetMapping // ("/")
    public String list(Model model) {
        model.addAttribute("posts", postService.findAll());
        return "post/list"; // resources/templates/post/list.html
    }
}
