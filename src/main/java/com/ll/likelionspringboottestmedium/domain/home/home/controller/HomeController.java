package com.ll.likelionspringboottestmedium.domain.home.home.controller;

import com.ll.likelionspringboottestmedium.domain.post.post.service.PostService;
import com.ll.likelionspringboottestmedium.global.rq.Rq;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
@RequiredArgsConstructor
public class HomeController {
    private final Rq rq;
    private final PostService postService;

    @GetMapping("/")
    public String showMain() {
        rq.setAttribute("posts", postService.findTop30ByIsPublishedOrderByIdDesc(true));

        return "domain/home/home/main";
    }
}
