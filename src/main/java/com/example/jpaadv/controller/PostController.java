package com.example.jpaadv.controller;

import com.example.jpaadv.model.dto.PostDTO;
import com.example.jpaadv.service.PostService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

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

    @GetMapping("/new")
    public String createForm(Model model) {
        // new PostDTO.SaveRequest() -> 빈 생성자
        model.addAttribute("post", new PostDTO.SaveRequest());
        return "post/form"; // resources/templates/post/form.html
    }

    @PostMapping("/new")
    public String create(@ModelAttribute("post") PostDTO.SaveRequest dto) {
        // new PostDTO.SaveRequest() -> 빈 생성자
        postService.save(dto);
        // P-R-G
        return "redirect:/posts";
    }
}
